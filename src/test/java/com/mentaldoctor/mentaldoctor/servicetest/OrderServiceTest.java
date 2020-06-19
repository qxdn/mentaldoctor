package com.mentaldoctor.mentaldoctor.servicetest;

import com.mentaldoctor.mentaldoctor.exception.OrderInvalidException;
import com.mentaldoctor.mentaldoctor.model.entity.Medicine;
import com.mentaldoctor.mentaldoctor.model.entity.Order;
import com.mentaldoctor.mentaldoctor.service.api.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @ParameterizedTest
    @CsvSource(value = {"阿司匹林,50","安眠药,22"})
    public void saveMedicineTest(String name,double price){
        Medicine medicine=new Medicine();
        medicine.setName(name);
        medicine.setPrice(price);
        orderService.saveMedicine(medicine);
    }

    @Test
    public void getAllMedicineTest(){
        List<Medicine> medicines = orderService.getAllMedicine();
        for(Medicine medicine : medicines){
            System.out.println(medicine);
        }
    }

    @ParameterizedTest
    @CsvSource({"1,3,1,5"})
    public void saveOrder(long doctorId,long buyId,int medicineId,int num) throws OrderInvalidException {
        Order order=new Order();
        order.setDoctorId(doctorId);
        order.setBuyId(buyId);
        order.setMedicineId(medicineId);
        order.setNumber(num);
        List<Order> orders=new ArrayList<>();
        orders.add(order);
        orderService.saveOrder(orders);
    }
}
