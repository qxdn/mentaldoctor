package com.mentaldoctor.mentaldoctor.dao;

import com.mentaldoctor.mentaldoctor.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {
}
