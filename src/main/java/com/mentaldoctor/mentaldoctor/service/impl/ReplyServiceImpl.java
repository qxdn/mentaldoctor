package com.mentaldoctor.mentaldoctor.service.impl;

import com.mentaldoctor.mentaldoctor.dao.PostDao;
import com.mentaldoctor.mentaldoctor.dao.ReplyDao;
import com.mentaldoctor.mentaldoctor.dao.UserDao;
import com.mentaldoctor.mentaldoctor.model.dto.ReplyBefore;
import com.mentaldoctor.mentaldoctor.model.entity.Post;
import com.mentaldoctor.mentaldoctor.model.entity.Reply;
import com.mentaldoctor.mentaldoctor.model.entity.User;
import com.mentaldoctor.mentaldoctor.service.api.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Slf4j
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;


    @Override
    public Reply insertReply(ReplyBefore replyBefore) {
        Reply reply=replyBefore2Reply(replyBefore);
        Post post=reply.getPost();
        post.setReplyCounts(post.getReplyCounts()+1);
        reply.setPost(post);
        reply=replyDao.save(reply);
        return reply;
    }

    private Reply replyBefore2Reply(ReplyBefore replyBefore){
        Reply reply=new Reply();
        reply.setContent(replyBefore.getContent());
        User user=userDao.findByUuid(replyBefore.getUuid());
        Post post=postDao.findPostById(replyBefore.getPostId());
        reply.setPost(post);
        reply.setUser(user);
        reply.setCreateTime(new Date());
        return reply;
    }
}
