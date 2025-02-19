package com.forum;

import com.forum.model.Post;
import com.forum.model.Reply;
import com.forum.repository.PostRepository;
import com.forum.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostTest {
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private ReplyRepository replyRepository;
    
    @Test
    void testPostAndReplyCreation() {
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("Test Content");
        postRepository.save(post);
        
        Reply reply = new Reply();
        reply.setContent("Test Reply");
        reply.setPost(post);
        replyRepository.save(reply);
        
        Post savedPost = postRepository.findById(post.getId()).orElseThrow();
        assertEquals(1, savedPost.getReplies().size());
        assertFalse(savedPost.isHot());
    }
}