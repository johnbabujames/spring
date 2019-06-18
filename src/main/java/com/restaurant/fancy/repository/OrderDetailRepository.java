package com.restaurant.fancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.restaurant.fancy.model.OrderDetail;

@Repository
public interface OrderDetailRepository  extends JpaRepository<OrderDetail, Long>, JpaSpecificationExecutor<OrderDetail>{

}
