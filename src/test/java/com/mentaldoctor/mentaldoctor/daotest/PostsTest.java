package com.mentaldoctor.mentaldoctor.daotest;

import com.mentaldoctor.mentaldoctor.dao.PostDao;
import com.mentaldoctor.mentaldoctor.model.entity.Post;
import com.mentaldoctor.mentaldoctor.model.entity.User;
import com.mentaldoctor.mentaldoctor.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class PostsTest {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void insertPosts(){
        Post posts=new Post();
        User user=(User) userService.loadUserByUsername("qxdn");
        posts.setTitle("测试主题");
        posts.setContent("测试内容");
        posts.setCreateTime(new Date());
        posts.setUpdateTime(posts.getCreateTime());
        posts.setUser(user);
        postDao.save(posts);
    }

    @Test
    public void findPosts(){
        List<Post> posts=postDao.findAll();
        for(Post post:posts){
            System.out.println(post);
        }
    }

    @Test
    public void findPostsByPage(){
        Pageable pageable=PageRequest.of(0,4);
        Page<Post> posts=postDao.findAllByOrderByUpdateTimeDesc(pageable);
        System.out.println("totalElements:"+posts.getTotalElements());
        System.out.println("totalPage:"+posts.getTotalPages());
        List<Post> postList=posts.getContent();
        for(Post post:postList){
            System.out.println(post);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    public void findByIdTest(Integer id){
        System.out.println(postDao.findPostById(id));
    }
}
