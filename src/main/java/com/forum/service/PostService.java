package com.forum.service;

import com.forum.model.Post;
import com.forum.model.Reply;
import com.forum.repository.PostRepository;
import com.forum.repository.ReplyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public PostService(PostRepository postRepository, ReplyRepository replyRepository) {
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Post> getHotPosts() {
        return postRepository.findByHotTrueOrderByCreatedAtDesc();
    }

    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Reply addReply(Long postId, Reply reply) {
        Post post = getPost(postId);
        reply.setPost(post);
        Reply savedReply = replyRepository.save(reply);
        
        // Check if post should be marked as hot
        if (post.getReplies().size() >= 2) { // Will be 3 after this reply
            post.setHot(true);
            postRepository.save(post);
        }
        
        return savedReply;
    }
}