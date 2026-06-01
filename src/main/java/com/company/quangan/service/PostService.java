package com.company.quangan.service;

import com.company.quangan.payload.request.create.CreatePostCategoryRequest;
import com.company.quangan.payload.request.create.CreatePostRequest;
import com.company.quangan.payload.request.update.UpdatePostRequest;
import com.company.quangan.payload.response.PostCategoryResponse;
import com.company.quangan.payload.response.PostResponse;

import java.util.List;

public interface PostService {
    String createPost(CreatePostRequest createPostRequest);
    String createCategory(CreatePostCategoryRequest createPostCategoryRequest);
    PostResponse getPostById(int postId);
    String updatePost(UpdatePostRequest updatePostRequest);
    String deletePost(int postId);
    List<PostCategoryResponse> getPostCategory();
    List<PostResponse> getPostByCategory(int cId);
    List<PostResponse> getPostResponses();
}
