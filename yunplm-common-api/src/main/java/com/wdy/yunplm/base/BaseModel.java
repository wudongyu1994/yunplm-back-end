package com.wdy.yunplm.base;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
// BaseModel not map to mysql,
// and use joined strategy, share p-key
@MappedSuperclass
//@Inheritance(strategy=InheritanceType.JOINED)
public class BaseModel implements Serializable {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	protected Integer id;

	@Column(nullable = false)
	protected Long createTime;

	@Column(nullable = false)
	protected Long updateTime;
}
