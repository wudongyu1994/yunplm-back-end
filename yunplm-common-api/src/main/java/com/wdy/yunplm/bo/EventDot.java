package com.wdy.yunplm.bo;

import lombok.Data;

@Data
public class EventDot {
	public EventDot(){
		this.isError=0;
		this.time=0L;
	}
	public EventDot(Integer isError, Long time) {
		this.isError = isError;
		this.time = time;
	}

	protected Integer isError;
	protected Long time;
}
