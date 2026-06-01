package com.company.quangan.service.Impl;

import com.company.quangan.payload.request.create.CreatePostCategoryRequest;
import com.company.quangan.payload.request.create.CreatePostRequest;
import com.company.quangan.payload.request.update.UpdatePostRequest;
import com.company.quangan.payload.response.PostCategoryResponse;
import com.company.quangan.payload.response.PostResponse;
import com.company.quangan.service.PostService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class PostServiceImpl implements PostService {
    @Override
    public String createPost(CreatePostRequest createPostRequest) {
        return "";
    }

    @Override
    public String createCategory(CreatePostCategoryRequest createPostCategoryRequest) {
        return "";
    }

    @Override
    public PostResponse getPostById(int postId) {
        return null;
    }

    @Override
    public String updatePost(UpdatePostRequest updatePostRequest) {
        return "";
    }

    @Override
    public String deletePost(int postId) {
        return "";
    }

    @Override
    public List<PostCategoryResponse> getPostCategory() {
        return List.of();
    }

    @Override
    public List<PostResponse> getPostByCategory(int cId) {
        return List.of();
    }

    @Override
    public List<PostResponse> getPostResponses() {
        return List.of();
    }
}
