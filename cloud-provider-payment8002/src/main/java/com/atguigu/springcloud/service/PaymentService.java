package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.Payment;

/**
 * @author shuyan.qi
 * @date 2020-04-05 19:23
 */
public interface PaymentService {
    int create(Payment payment);

    Payment findPaymentById(Long id);
}
