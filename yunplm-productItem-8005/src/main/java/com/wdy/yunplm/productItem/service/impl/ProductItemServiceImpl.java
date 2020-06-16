package com.wdy.yunplm.productItem.service.impl;

import com.wdy.yunplm.po.ProductItem;
import com.wdy.yunplm.po.ProductItem2PresentStep;
import com.wdy.yunplm.po.ProductItem2TargetStep;
import com.wdy.yunplm.productItem.dao.IProductItem2PresentStepDao;
import com.wdy.yunplm.productItem.dao.IProductItem2TargetStepDao;
import com.wdy.yunplm.productItem.dao.IProductItemDao;
import com.wdy.yunplm.productItem.dao.ITargetStepDao;
import com.wdy.yunplm.productItem.service.IOrderService;
import com.wdy.yunplm.productItem.service.IProductItemService;
import com.wdy.yunplm.vo.OrderVO;
import com.wdy.yunplm.vo.ProductItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ProductItemServiceImpl implements IProductItemService {
	@Resource
	private IProductItem2TargetStepDao iProductItem2TargetStepDao;
	@Resource
	private IProductItem2PresentStepDao iProductItem2PresentStepDao;
	@Resource
	private IProductItemDao iProductItemDao;
	@Resource
	private ITargetStepDao iTargetStepDao;
	@Resource
	private IOrderService iOrderService;

	@Override
	public ProductItemVO getProductItemVOById(Integer id) {
		ProductItem productItem=iProductItemDao.findById(id).orElse(null);
		if(productItem==null) return null;
		ProductItemVO productItemVO =new ProductItemVO();
		BeanUtils.copyProperties(productItem,productItemVO);
		productItemVO.setStepList(iTargetStepDao.findTargetStepByProductItemId(productItem.getId()));
		productItemVO.setPresentStepList(iTargetStepDao.findPresentStepByProductItemId(productItem.getId()));
		return productItemVO;
	}

	/**
	 * 根据订单id返回产品信息
	 * @param orderId，
	 * @return 订单中产品列表信息，包括目标步骤和当前步骤
	 */
	@Override
	public List<ProductItemVO> getProductItemVOByOrderId(Integer orderId) {
		List<ProductItem> productItemList = iProductItemDao.findByOrderId(orderId);

		if (productItemList == null) return new ArrayList<>();
		List<ProductItemVO> productItemVOList = new ArrayList<>();
		productItemList.forEach(productItem -> {
			ProductItemVO productItemVO = new ProductItemVO();
			BeanUtils.copyProperties(productItem, productItemVO);
			productItemVO.setStepList(iTargetStepDao.findTargetStepByProductItemId(productItem.getId()));
			productItemVO.setPresentStepList(iTargetStepDao.findPresentStepByProductItemId(productItem.getId()));
			productItemVOList.add(productItemVO);
		});
		return productItemVOList;
	}

	/**
	 * 添加产品项。在创建订单的时候，必然调用该方法。
	 * @param orderVO，只需要其中的productItemVOList和id
	 * @return 0-ok，
	 */
	@Override
	public Integer addProductItem(OrderVO orderVO) {
		List<ProductItemVO> productItemVOList = orderVO.getProductItemVOList();
		Long now = new Date().getTime();
		productItemVOList.forEach(productItemVO -> {
			productItemVO.setId(0);
			productItemVO.setOrderId(orderVO.getId());
			productItemVO.setCreateTime(now);
			productItemVO.setUpdateTime(now);
			ProductItem productItem = iProductItemDao.saveAndFlush(new ProductItem(productItemVO));
			Integer productItemId = productItem.getId();
			productItemVO.getStepList().forEach(step ->
					iProductItem2TargetStepDao.save(new ProductItem2TargetStep(productItemId, step.getId())));
			iProductItem2TargetStepDao.flush();
		});
//		log.info("productItemVOList ==> " + productItemVOList.toString());
		return 0;
	}

	/**
	 * 添加当前步骤到产品项中
	 * @param pi2ps，
	 * @return ，
	 */
	@Override
	public ProductItem2PresentStep addPresentStep(ProductItem2PresentStep pi2ps) {
		log.info("==> here is addPresentStep!");
		Integer productItemId = pi2ps.getProductItemId();
		if(productItemId==null) return null;
		if (iProductItemDao.findById(productItemId).isPresent()) {
			//修改产品项对应的订单的状态
			Integer orderId = iProductItemDao.findById(productItemId).get().getOrderId();
			iOrderService.modifyOrderStatusToProduceByOrderId_Feign(orderId);

			//更新产品项的进度信息
			List<Integer> presentStepIdList = iProductItem2PresentStepDao.findByProductItemId(pi2ps.getProductItemId())
					.stream().map(ProductItem2PresentStep::getPresentStepId).collect(Collectors.toList());
			if (presentStepIdList.contains(pi2ps.getPresentStepId()))   //该进度已存在
				return null;
			List<Integer> targetStepIdList = iProductItem2TargetStepDao.findByProductItemId(pi2ps.getProductItemId())
					.stream().map(ProductItem2TargetStep::getTargetStepId).collect(Collectors.toList());
			if (targetStepIdList.contains(pi2ps.getPresentStepId())) {  //符合各种条件
				Long now=new Date().getTime();
				pi2ps.setCreateTime(now);
				pi2ps.setUpdateTime(now);
				return iProductItem2PresentStepDao.saveAndFlush(pi2ps);
			} else {    //该进度不是本产品项的进度
				return null;
			}
		} else {
			return null;
		}
	}
}
