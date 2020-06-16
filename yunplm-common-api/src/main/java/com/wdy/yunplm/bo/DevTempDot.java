package com.wdy.yunplm.bo;

import lombok.Data;

@Data
public class DevTempDot {
	public DevTempDot(Float devTemp, Long time) {
		this.devTemp = devTemp;
		this.time = time;
	}

	protected Float devTemp;
	protected Long time;
}
