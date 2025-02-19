package com.forum.service;

import com.forum.model.Post;
import com.forum.model.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;
    
    @Test
    void testHotPostLogic() {
        // Create a new post
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("Test Content");
        Post savedPost = postService.createPost(post);
        
        // Add 3 replies
        for (int i = 0; i < 3; i++) {
            Reply reply = new Reply();
            reply.setContent("Reply " + (i + 1));
            postService.addReply(savedPost.getId(), reply);
        }
        
        // Verify post is now hot
        Post hotPost = postService.getPost(savedPost.getId());
        assertTrue(hotPost.isHot());
        assertEquals(3, hotPost.getReplies().size());
        
        // Verify hot post appears in hot posts list
        List<Post> hotPosts = postService.getHotPosts();
        assertTrue(hotPosts.contains(hotPost));
    }
}