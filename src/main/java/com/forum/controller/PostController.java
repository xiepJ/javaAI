package com.forum.controller;

import com.forum.model.Post;
import com.forum.model.Reply;
import com.forum.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts/list";
    }

    @GetMapping("/hot")
    public String listHotPosts(Model model) {
        model.addAttribute("posts", postService.getHotPosts());
        return "posts/hot";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        model.addAttribute("newReply", new Reply());
        return "posts/view";
    }

    @GetMapping("/posts/new")
    public String newPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/form";
    }

    @PostMapping("/posts")
    public String createPost(@Valid Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "posts/form";
        }
        Post savedPost = postService.createPost(post);
        return "redirect:/posts/" + savedPost.getId();
    }

    @PostMapping("/posts/{postId}/replies")
    public String addReply(@PathVariable Long postId, @Valid Reply reply, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/posts/" + postId;
        }
        postService.addReply(postId, reply);
        return "redirect:/posts/" + postId;
    }
}