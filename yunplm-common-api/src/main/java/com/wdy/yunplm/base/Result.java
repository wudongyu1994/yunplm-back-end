package com.wdy.yunplm.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
	private Integer code;
	private String msg;
	private Integer count;
	private T data;

	private Result(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/* 无数据传输的 成功返回 */
	public static <T> Result<T> success() {
		return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
	}

	public static <T> Result<T> success(ResultCode resultCode) {
		return new Result<>(resultCode.getCode(), resultCode.getMsg());
	}

	/* 单个数据传输的 成功返回 */
	public static <T> Result<T> success(T data) {
		return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data == null ? 0 : 1, data);
	}

	public static <T> Result<T> success(String msg, T data) {
		return new Result<>(ResultCode.SUCCESS.getCode(), msg, data == null ? 0 : 1, data);
	}

	public static <T> Result<T> success(ResultCode resultCode, T data) {
		return new Result<>(resultCode.getCode(), resultCode.getMsg(), data == null ? 0 : 1, data);
	}

	//多个数据成功
	public static <T> Result<T> success(T data, int count) {
		return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), count, data);
	}

	public static <T> Result<T> success(String msg, T data, int count) {
		return new Result<>(ResultCode.SUCCESS.getCode(), msg, count, data);
	}

	public static <T> Result<T> success(ResultCode resultCode, T data, int count) {
		return new Result<>(resultCode.getCode(), resultCode.getMsg(), count, data);
	}

	/* 无数据传输的 失败返回 */
	public static <T> Result<T> failure() {
		return new Result<>(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMsg());
	}

	public static <T> Result<T> failure(String msg) {
		return new Result<>(ResultCode.FAIL.getCode(), msg);
	}

	public static <T> Result<T> failure(Integer code, String msg) {
		return new Result<>(code, msg);
	}

	public static <T> Result<T> failure(ResultCode resultCode) {
		return new Result<>(resultCode.getCode(), resultCode.getMsg());
	}
}
