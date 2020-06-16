package com.wdy.yunplm.base;

public enum ResultCode {

	// 公共请求信息
	SUCCESS(20000,"请求成功"),
	FAIL(50000,"请求失败"),
	BINDING_ERROR(60000,"参数绑定失败"),
	PARAMETER_MISSING(60001,"参数缺失"),
	UNAUTHORIZED(40001,"未授权"),

	//用户信息
	//50001 - 50009
	USER_NOT_EXIST(50001,"该角色不存在，无法操作"),
	USERNAME_REPEAT(50002,"用户名已存在"),
	PHONE_REPEAT(50003,"手机号已存在"),

	//用户-角色
	//50011 - 50019
	ROLE_NOT_EXIST(50011,"该角色不存在，无法操作"),
	USER_ROLE_NO_CLEAR(50013,"该角色存在用户关联，无法操作"),

	//权限 50021-50029
	PERMISSION_NOT_EXIST(50021,"该权限不存在，无法操作"),

	//订单 50031-50039
	ORDER_NOT_EXIST(50031,"该订单不存在，无法操作"),


	//权限 50091-50099
	NOT_EXIST(50091,"该对象不存在，无法操作"),
	REPEAT(50092,"该对象已存在，无法操作"),

	// feign 51001 - 51009
	FEIGN_ERROR(51001,"openfeign调用失败"),

	// sentinel 52001 -52009
	SENTINEL_LIMIT_FLOW(52001,"sentinel限流"),
	SENTINEL_FALLBACK(52002,"sentinel降级");


	private Integer code;
	private String msg;

	ResultCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static String getMsg(String name) {
		for (ResultCode item : ResultCode.values()) {
			if (item.name().equals(name)) {
				return item.msg;
			}
		}
		return null;
	}

	public static Integer getCode(String name) {
		for (ResultCode item : ResultCode.values()) {
			if (item.name().equals(name)) {
				return item.code;
			}
		}
		return null;
	}
}
