package com.mentaldoctor.mentaldoctor.servicetest;

import com.mentaldoctor.mentaldoctor.model.dto.PostBack;
import com.mentaldoctor.mentaldoctor.model.dto.PostBefore;
import com.mentaldoctor.mentaldoctor.model.entity.Post;
import com.mentaldoctor.mentaldoctor.service.api.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @ParameterizedTest
    @CsvSource({"测试1,about,1","测试2,cat,1","测试3,stein;gate,1"})
    public void saveTest(String title,String content,long uuid){
        PostBefore postBefore=new PostBefore(title,content,uuid);
        Post post=postService.insertPost(postBefore);
        System.out.println(post);
    }

    @ParameterizedTest
    @CsvSource({"0,10","1,2"})
    public void getAllTest(int page,int size){
        Page<Post> posts=postService.findPostByPageOrderByUpdateTimeDesc(page,size);
        System.out.println("totalElements:"+posts.getTotalElements());
        System.out.println("totalPage:"+posts.getTotalPages());
        List<Post> postList=posts.getContent();
        for(Post post:postList){
            System.out.println(post);
        }
    }

    @ParameterizedTest
    @CsvSource({"测,0,10"})
    public void LikeTest(String content,int page,int size){
        Page<Post> posts=postService.findPostWhereTitleLike(content,page,size);
        System.out.println("totalElements:"+posts.getTotalElements());
        System.out.println("totalPage:"+posts.getTotalPages());
        List<Post> postList=posts.getContent();
        for(Post post:postList){
            System.out.println(post);
        }
    }

    @ParameterizedTest
    @CsvSource({"1,0,10"})
    public void postReplyTest(int id,int page, int size){
       PostBack  postBack= postService.findPostAndReplyByPostId(id,page,size);
       System.out.println(postBack);
    }
}
