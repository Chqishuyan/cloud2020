package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author shuyan.qi
 * @date 2020-04-05 19:23
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public int create(Payment payment) {
        return paymentMapper.insert(payment);
    }

    @Override
    public Payment findPaymentById(Long id) {
        return paymentMapper.selectByPrimaryKey(id);
    }
}
