package com.mentaldoctor.mentaldoctor.service.impl;

import com.mentaldoctor.mentaldoctor.dao.MedicineDao;
import com.mentaldoctor.mentaldoctor.dao.OrderDao;
import com.mentaldoctor.mentaldoctor.dao.UserDao;
import com.mentaldoctor.mentaldoctor.exception.OrderInvalidException;
import com.mentaldoctor.mentaldoctor.model.entity.Medicine;
import com.mentaldoctor.mentaldoctor.model.entity.Order;
import com.mentaldoctor.mentaldoctor.service.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MedicineDao medicineDao;


    @Override
    public Medicine saveMedicine(Medicine medicine) {
        boolean isExisted= medicineDao.existsByName(medicine.getName());
        if(isExisted){
            medicineDao.updatePrice(medicine.getName(),medicine.getPrice());
            medicine = medicineDao.findByName(medicine.getName());
        }else {
            medicine = medicineDao.save(medicine);
        }
        return medicine;
    }

    @Override
    public List<Medicine> getAllMedicine() {
        return medicineDao.findAll();
    }

    @Override
    public List<Order> saveOrder(List<Order> orders) throws OrderInvalidException {
        Date date = new Date();
        for(Order order :orders){
            if(validOrder(order)){
                order.setCreateTime(date);
            }else {
                throw new OrderInvalidException();
            }
        }
        return orderDao.saveAll(orders);
    }

    private boolean validOrder(Order order){
        return (userDao.existsById(order.getDoctorId())&&userDao.existsById(order.getBuyId())&&medicineDao.existsById(order.getMedicineId()));
    }
}
