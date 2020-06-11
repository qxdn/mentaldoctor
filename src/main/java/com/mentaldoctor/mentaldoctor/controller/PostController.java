package com.mentaldoctor.mentaldoctor.controller;

import com.mentaldoctor.mentaldoctor.model.dto.PostBefore;
import com.mentaldoctor.mentaldoctor.model.entity.Post;
import com.mentaldoctor.mentaldoctor.service.api.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@Api(tags = "帖子相关操作")
public class PostController {

    @Autowired
    private PostService postService;

    @ApiOperation(value = "获取帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "查询的页数",defaultValue = "0"),
            @ApiImplicitParam(name = "size",value = "一页的元素个数",defaultValue = "10"),
            @ApiImplicitParam(name = "isSortByTime",value = "是否按照时间倒叙查找",defaultValue = "true")
    })
    @GetMapping("/")
    public Page<Post> getAllPost(@RequestParam(name = "page",defaultValue = "0")int page,@RequestParam(name = "size",defaultValue = "10")int size,@RequestParam(name = "sortByTime",defaultValue = "true")boolean isSortByTime){
        Page<Post> posts;
        if(isSortByTime){
            posts=postService.findPostByPageOrderByCreateTimeDesc(page,size);
        }else {
            posts=postService.findPostByPage(page,size);
        }
        return posts;
    }

    @ApiOperation(value = "发布一个帖子")
    @ApiImplicitParam(paramType = "body")
    @PostMapping("/")
    public Post publishPost(@RequestBody @Validated PostBefore postBefore){
        return postService.insertPost(postBefore);
    }
}
