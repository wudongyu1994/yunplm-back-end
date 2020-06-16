package com.wdy.yunplm.po;

import com.wdy.yunplm.base.BaseModel;
import com.wdy.yunplm.vo.OrderVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "orders") //order is mysql system table
@ToString(callSuper = true)
public class Order extends BaseModel {
	@Column(nullable = false)
	protected String orderNo;
	@NotNull
	@Column(nullable = false)
	protected Integer userId;
	@Column(nullable = false)
	protected Integer orderStatus;
	protected Long payTime;
	protected Long deliveryTime;
	protected Long receiveTime;
	@NotNull
	@Column(nullable = false)
	protected Integer money;
	@NotBlank
	@Column(nullable = false)
	protected String address;
	@NotBlank
	@Column(nullable = false)
	protected String phone;
	protected String note;
	protected String expressNo;

	public interface Status {
		int CREATED=0;
		int PAID=1;
		int PRODUCE=2;
		int DELIVERY=3;
		int RECEIVED=4;
	}

	public Order(OrderVO orderVO){
		BeanUtils.copyProperties(orderVO,this);
	}

	public void setOrderStatus(Integer status){
		if(this.orderStatus ==null || status > this.orderStatus){
			this.orderStatus=status;
		}
	}
}
