package com.atguigu.springcloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atguigu.springcloud.entities.Payment;

@Mapper
public interface PaymentDao {
	
	public int create(Payment payment);
	
	public Payment getPaymentById(@Param("id") long id);

	@Select("select * from payment")
	List<Payment> paymentList();

}
