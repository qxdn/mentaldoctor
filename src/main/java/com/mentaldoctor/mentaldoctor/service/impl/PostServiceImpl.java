package com.mentaldoctor.mentaldoctor.service.impl;

import com.mentaldoctor.mentaldoctor.dao.PostDao;
import com.mentaldoctor.mentaldoctor.dao.ReplyDao;
import com.mentaldoctor.mentaldoctor.dao.UserDao;
import com.mentaldoctor.mentaldoctor.model.dto.PostBack;
import com.mentaldoctor.mentaldoctor.model.dto.PostBefore;
import com.mentaldoctor.mentaldoctor.model.entity.Post;
import com.mentaldoctor.mentaldoctor.model.entity.Reply;
import com.mentaldoctor.mentaldoctor.model.entity.User;
import com.mentaldoctor.mentaldoctor.service.api.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReplyDao replyDao;

    @Override
    public Post insertPost(PostBefore postBefore) {
        Post post=postBefore2Post(postBefore);
        post=postDao.save(post);
        return post;
    }

    @Override
    public Page<Post> findPostByPage(int page, int size) {
        Page<Post> posts=postDao.findAll(PageRequest.of(page,size));
        return posts;
    }

    @Override
    public Page<Post> findPostByPageOrderByUpdateTimeDesc(int page, int size) {
        Page<Post> posts=postDao.findAllByOrderByUpdateTimeDesc(PageRequest.of(page,size));
        return posts;
    }

    @Override
    public PostBack findPostAndReplyByPostId(int postId,int page,int size) {
        Post post = postDao.findPostById(postId);
        post.setBrowses(post.getBrowses()+1);
        post = postDao.save(post);
        Page<Reply> replyPage=replyDao.findAllByPostId(postId,PageRequest.of(page,size));
        PostBack postBack=new PostBack();
        postBack.setPost(post);
        postBack.setReplies(replyPage.getContent());
        postBack.setSize(replyPage.getSize());
        postBack.setTotalElements(replyPage.getTotalElements());
        return postBack;
    }

    private Post postBefore2Post(PostBefore postBefore){
        Post post=new Post();
        post.setTitle(postBefore.getTitle());
        post.setContent(postBefore.getContent());
        post.setCreateTime(new Date());
        post.setUpdateTime(post.getCreateTime());
        User user=userDao.findByUuid(postBefore.getUuid());
        post.setUser(user);
        return post;
    }
}
