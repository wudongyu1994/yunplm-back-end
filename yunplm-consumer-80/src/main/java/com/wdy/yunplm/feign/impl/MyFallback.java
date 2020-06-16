package com.wdy.yunplm.feign.impl;

import com.wdy.yunplm.base.Result;
import com.wdy.yunplm.base.ResultCode;
import com.wdy.yunplm.bo.DevTempDot;
import com.wdy.yunplm.bo.EventDot;
import com.wdy.yunplm.bo.LedDot;
import com.wdy.yunplm.bo.Property;
import com.wdy.yunplm.po.*;
import com.wdy.yunplm.feign.*;
import com.wdy.yunplm.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MyFallback implements IUserService, IRoleService, IPermissionService, IOrderService,
		IProductService, IProductItemService, IDeviceService, IMaterialService, ILogisticsService,IAfterSaleService {
	@Override
	public Result<List<AfterSale>> getAfterSaleByProductItemId_Feign(Integer productItemId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<AfterSale> addAfterSale_Feign(AfterSale afterSale) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<ProductItemVO> getProductItemVOById_Feign(Integer id) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Order> getOrderById_Feign(Integer orderId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<String> hello_Feign() {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<Long>> getOderIncome_Feign() {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<OrderStatus>> getAllOderStatus_Feign() {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<OrderStatus>> getOderStatusByUserId_Feign(Integer userId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<Device>> getAllDevice_Feign() {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<Device>> getDeviceByUserId_Feign(Integer userId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Property> getDevicePropertyById_Feign(Integer deviceId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<LedDot>> getDeviceLedHistoryById_Feign(Integer deviceId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<DevTempDot>> getDeviceDevTempHistoryById_Feign(Integer deviceId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<EventDot>> getDeviceEventById_Feign(Integer deviceId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<String> clearDeviceEventById_Feign(Integer deviceId, Long clearTime) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<EventDot>> getDeviceEventHistoryById_Feign(Integer deviceId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<String> setDeviceLedById_Feign(Integer deviceId, Boolean isTurnOn) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Device> addDevice_Feign(Device device) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Device> deleteDeviceById_Feign(Integer deviceId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<Logistics>> getLogisticsByExpressNo_Feign(String expressNo) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Logistics> addLogistics_Feign(Logistics logistics) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Logistics> modifyLogisticsById_Feign(Integer id, Logistics logistics) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Logistics> deleteLogisticsById_Feign(Integer id) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<Material>> getAllMaterial_Feign() {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<Material>> getMaterialByProductItemId_Feign(Integer productItemId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Material> addMaterial_Feign(Material material) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Material> modifyMaterialById_Feign(Integer id, Material material) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Material> deleteMaterialById_Feign(Integer id) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<Order>> getOrderByPage_Feign(Integer page, Integer size) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<Order>> getOrderByUserIdByPage_Feign(Integer userId, Integer page, Integer size) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Order> addOrder_Feign(OrderVO orderVO) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Order> modifyOrderById_Feign(Integer id, Order order) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Order> deleteOrderById_Feign(Integer id) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<Permission>> getPermissionByUserId_Feign(Integer userId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<PermissionTree>> getAllPermissionTree_Feign() {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Permission> addPermission_Feign(Permission permission) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Permission> modifyPermissionById_Feign(Integer id, Permission permission) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Permission> deletePermissionById_Feign(Integer id) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<ProductItemVO>> getProductItemByOrderId_Feign(Integer orderId) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<ProductItem2PresentStep> addPresentStep_Feign(ProductItem2PresentStep pi2ps) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<ProductVO>> getProductVO_Feign() {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<ProductVO> addProductVO_Feign(ProductVO productVO) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<ProductVO> modifyProductVOById_Feign(Integer id, ProductVO product) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Product> deleteProductById_Feign(Integer id) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<Step>> getStep_Feign() {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Step> addStep_Feign(Step step) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Step> modifyStepById_Feign(Integer id, Step step) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Step> deleteStepById_Feign(Integer id) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<Role>> getAllRole_Feign() {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<RoleVO>> getRoleByPage_Feign(Integer page, Integer size) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<RoleVO> addRoleVO_Feign(RoleVO roleVO) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<RoleVO> modifyRoleVOById_Feign(Integer id, RoleVO roleVO) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<Role> deleteRoleById_Feign(Integer id) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<List<UserVO>> getUserVOByPage_Feign(Integer page, Integer size) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<User> getUserByUsername_Feign(String username) {
		log.info("==> here is feign fallback!");
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<UserVO> addUserVO_Feign(UserVO userVO) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<UserVO> modifyUserVOById_Feign(Integer id, UserVO userVO) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<User> changeUserPasswordById_Feign(Integer id, String oldPassword, String newPassword) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}

	@Override
	public Result<User> deleteUserById_Feign(Integer id) {
		return Result.failure(ResultCode.FEIGN_ERROR);
	}
}
