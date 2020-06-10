package com.mentaldoctor.mentaldoctor.dao;

import com.mentaldoctor.mentaldoctor.model.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyDao extends JpaRepository<Reply,Integer> {

    Page<Reply> findAllByPostId(Integer postId,Pageable pageable);
}
