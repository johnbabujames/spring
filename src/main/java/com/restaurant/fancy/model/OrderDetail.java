package com.restaurant.fancy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "order_detail")
@EntityListeners(AuditingEntityListener.class)
public class OrderDetail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "order_id", insertable=false, updatable=false)
    private Long orderId;
	 
	@Column(name= "customer_id")
	private String customerId;
	
	@Column(name= "employee_id")
	private String employeeId;
	
	@Column(name= "order_type")
	private String orderType;
	
	@Column(name= "order_status")
	private String orderStatus;
	
	@Column(name= "order_time", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date orderTime;
	
	@Column(name="est_delivery_time", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date estimatedDeliveryTime;
	
	@Column(name= "description")
	private String description;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}

	public void setEstimatedDeliveryTime(Date estimatedDeliveryTime) {
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
