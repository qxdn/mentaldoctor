package com.mentaldoctor.mentaldoctor.dao;

import com.mentaldoctor.mentaldoctor.model.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineDao extends JpaRepository<Medicine,Integer> {
    Medicine findByName(String name);

    @Modifying
    @Query(value="update medicine set price =:price where name =:name",nativeQuery = true)
    int updatePrice(String name,double price);

    boolean existsByName(String name);
}
