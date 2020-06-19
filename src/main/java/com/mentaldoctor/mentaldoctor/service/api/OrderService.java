package com.mentaldoctor.mentaldoctor.service.api;

import com.mentaldoctor.mentaldoctor.exception.OrderInvalidException;
import com.mentaldoctor.mentaldoctor.model.entity.Medicine;
import com.mentaldoctor.mentaldoctor.model.entity.Order;

import java.util.List;

public interface OrderService {

    public Medicine saveMedicine(Medicine medicine);

    public List<Medicine> getAllMedicine();

    public List<Order> saveOrder(List<Order> orders) throws OrderInvalidException;
}
