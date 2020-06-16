package com.wdy.yunplm.bo;

import lombok.Data;

@Data
public class Property {
	public Property() {
		this.status = "";
		this.isLedOn = false;
		this.devTemp = 0f;
	}

	protected String status;
	protected Boolean isLedOn;
	protected Float devTemp;

	public static String PROPERTY_LED ="is_led_on";
	public static String PROPERTY_TEMP ="dev_temp";
}
