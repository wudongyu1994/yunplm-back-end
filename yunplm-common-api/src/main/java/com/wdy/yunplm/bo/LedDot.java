package com.wdy.yunplm.bo;

import lombok.Data;

@Data
public class LedDot {
	public LedDot(Boolean isLedOn, Long time) {
		this.isLedOn = isLedOn;
		this.time = time;
	}

	protected Boolean isLedOn;
	protected Long time;
}
