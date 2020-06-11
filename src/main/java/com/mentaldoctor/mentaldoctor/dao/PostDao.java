package com.mentaldoctor.mentaldoctor.dao;

import com.mentaldoctor.mentaldoctor.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends JpaRepository<Post,Integer> {

    Page<Post> findAllByOrderByCreateTimeDesc(Pageable pageable);

    Post findPostById(Integer id);


}
