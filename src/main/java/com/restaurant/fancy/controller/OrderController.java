package com.restaurant.fancy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.fancy.exception.ResourceNotFoundException;
import com.restaurant.fancy.model.OrderDetail;
import com.restaurant.fancy.repository.OrderDetailRepository;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
    OrderDetailRepository orderDetailRepository;
	
	@GetMapping("/order")
	public List<OrderDetail> getAllOrders() {
		return orderDetailRepository.findAll();
	}
	
	@GetMapping("/order/{orderId}")
	public Optional<OrderDetail> getOrder(@PathVariable(value="orderId") Long orderId) {
		return orderDetailRepository.findById(orderId);
	}
	
	@PostMapping("/order")
	public OrderDetail createOrder(@Valid @RequestBody OrderDetail orderDetail) {
		return orderDetailRepository.save(orderDetail);
	}
	
	@PutMapping("/order/{orderId}")
	public OrderDetail updateOrder(@PathVariable(value="orderId") Long orderId, @Valid @RequestBody OrderDetail orderDetail) {
		OrderDetail tempOrderDetail = orderDetailRepository.findById(orderId)
				 .orElseThrow(() -> new ResourceNotFoundException("OrderDetail", "orderId", orderId));
		
		tempOrderDetail.setOrderStatus(orderDetail.getOrderStatus());
		
		OrderDetail updatedOrder = orderDetailRepository.save(tempOrderDetail);
		
		return updatedOrder;
	}
	
	@PostMapping("/order-filter")
	public List<OrderDetail> createOrderFilter(@Valid @RequestBody OrderDetail orderDetail) {
		
		return orderDetailRepository.findAll(new Specification<OrderDetail>() {
			@Override
			public Predicate toPredicate(Root<OrderDetail> root, CriteriaQuery< ?> query, CriteriaBuilder cb) {
				

				List<Predicate> predicates = new ArrayList<>();
				List<String> customerIds = new ArrayList<>();
				customerIds.add("CUST_101");
				customerIds.add("CUST_102");
				
				
				//Expression<String> parentExpression = root.get(Employee_.Parent);

				// If designation is specified in filter, add equal where clause
				if (orderDetail.getCustomerId() != null) {
					predicates.add(root.get("customerId").in(customerIds));
				}

				// If firstName is specified in filter, add contains (lile)
				// filter to where clause with ignore case
				if (orderDetail.getOrderStatus() != null) {
					predicates.add(cb.like(cb.lower(root.get("orderStatus")), 
                                                    "%" + orderDetail.getOrderStatus().toLowerCase() + "%"));
					//cb.in(expression)
				}

				// If lastName is specified in filter, add contains (lile)
				// filter to where clause with ignore case
				if (orderDetail.getEmployeeId() != null) {
					predicates.add(cb.like(cb.lower(root.get("employeeId")), 
                                                    "%" + orderDetail.getEmployeeId().toLowerCase() + "%"));
				}

				return cb.and(predicates.toArray(new Predicate[0]));
				
			}
		});
		
		
		
		
	//	return orderDetailRepository.save(orderDetail);
	}

}
