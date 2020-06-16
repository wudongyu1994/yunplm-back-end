package com.wdy.yunplm.product.service.impl;

import com.wdy.yunplm.base.BaseModel;
import com.wdy.yunplm.product.dao.IProduct2StepDao;
import com.wdy.yunplm.product.dao.IProductDao;
import com.wdy.yunplm.product.dao.IStepDao;
import com.wdy.yunplm.po.Product;
import com.wdy.yunplm.po.Product2Step;
import com.wdy.yunplm.po.Step;
import com.wdy.yunplm.product.service.IProductService;
import com.wdy.yunplm.utils.BeanUtilsMy;
import com.wdy.yunplm.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements IProductService {
	@Resource
	private IProductDao iProductDao;
	@Resource
	private IProduct2StepDao iProduct2StepDao;
	@Resource
	private IStepDao iStepDao;

	@Override
	public List<ProductVO> getProduct() {
		List<Product> productList = iProductDao.findAll();
		List<ProductVO> productVOList = new ArrayList<>();
		productList.forEach(product -> {
			ProductVO productVO = new ProductVO();
			BeanUtils.copyProperties(product, productVO);
			List<Step> stepList = iStepDao.findStepByProductId(product.getId());
			productVO.setStepList(stepList);
			productVOList.add(productVO);
		});
		return productVOList;
	}

	@Override
	public ProductVO addProductVO(ProductVO productVO) {
		Product product = new Product();
		BeanUtils.copyProperties(productVO, product);
		product = iProductDao.saveAndFlush(product);
		BeanUtils.copyProperties(product, productVO);

		productVO.getListStepId().forEach(stepId -> iProduct2StepDao.save(new Product2Step(productVO.getId(), stepId)));
		iProduct2StepDao.flush();
		List<Step> stepList = iStepDao.findStepByProductId(productVO.getId());
		log.info(stepList.toString());
		productVO.setStepList(stepList);
		return productVO;
	}

	@Override
	public ProductVO modifyProductVOById(Integer id, ProductVO productVO) {
		if (iProductDao.findById(id).isPresent()) {
			Product oldProduct = iProductDao.findById(id).get();
			Integer oldId = oldProduct.getId();
			BeanUtilsMy.copyPropertiesIgnoreNull(productVO, oldProduct);
			oldProduct.setId(oldId);
			Product productRet = iProductDao.saveAndFlush(oldProduct);
			BeanUtils.copyProperties(productRet, productVO);
			log.info("productVO ==> " + productVO.toString());
			// modify steps
			List<Integer> listTemp = productVO.getListStepId();
			List<Integer> newStepIdList = listTemp == null ? new ArrayList<>() : listTemp;
			log.info("newStepIdList ==> " + newStepIdList.toString());
			List<Product2Step> oldP2SList = iProduct2StepDao.findByProductId(id);
			List<Integer> addStepIdList = newStepIdList.isEmpty() ? new ArrayList<>()
					: newStepIdList
					.stream()
					.filter(stepId -> oldP2SList.stream().noneMatch(p2s -> p2s.getStepId().equals(stepId)))
					.collect(Collectors.toList());
			List<Integer> deleteP2SIdList = newStepIdList.isEmpty() ? oldP2SList.stream()
					.map(BaseModel::getId)
					.collect(Collectors.toList())
					: oldP2SList.stream()
					.filter(p2s -> !newStepIdList.contains(p2s.getStepId()))
					.map(BaseModel::getId)
					.collect(Collectors.toList());
			log.info("add list ==> " + addStepIdList.toString());
			log.info("delete list ==> " + deleteP2SIdList.toString());
			if (!addStepIdList.isEmpty()) {
				addStepIdList.forEach(stepId -> iProduct2StepDao.save(new Product2Step(id, stepId)));
			}
			if (!deleteP2SIdList.isEmpty()) {
				deleteP2SIdList.forEach(iProduct2StepDao::deleteById);
			}
			iProduct2StepDao.flush();
			//get new steps
			List<Step> stepList = iStepDao.findStepByProductId(id);
			log.info("stepList ==> " + stepList.toString());
			productVO.setStepList(stepList);
			return productVO;
		} else
			return null;
	}

	@Override
	public Product deleteProductById(Integer id) {
		if (iProductDao.findById(id).isPresent()) {
			Product product = iProductDao.findById(id).get();
			iProductDao.deleteById(id);
			iProductDao.flush();
			return product;
		} else return null;
	}
}
