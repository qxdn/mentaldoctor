package com.mentaldoctor.mentaldoctor.service.api;

import com.mentaldoctor.mentaldoctor.model.dto.PostBefore;
import com.mentaldoctor.mentaldoctor.model.entity.Post;
import org.springframework.data.domain.Page;

public interface PostService {

    public Post insertPost(PostBefore postBefore);

    public Page<Post> findPostByPage(int page, int size);

    public Page<Post> findPostByPageOrderByCreateTimeDesc(int page, int size);
}
