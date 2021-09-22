package com.larus.itiszuccante.web.rest;

import com.larus.itiszuccante.domain.Post;
import com.larus.itiszuccante.service.GroupService;
import com.larus.itiszuccante.service.PostService;
import com.larus.itiszuccante.service.UserService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/groups/{groupid}/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/")
    public List<Post> getPosts(@PathVariable String groupid) {
        return postService.findAllByGroup(groupid);
    }

    @GetMapping("/{postid}")
    public ResponseEntity<Post> getPost(@PathVariable String groupid, @PathVariable String postid) {
        return ResponseUtil.wrapOrNotFound(postService.read(postid));
    }

    @PostMapping("/")
    public Post createPost(@RequestBody Post p, @PathVariable String groupid) {
        p.setGroup(groupid);
        return postService.update(p);
    }

    @PutMapping("/{postid}")
    @PreAuthorize("@defaultPostService.isAuthorOrMod(#p, #groupid, #postid)")
    public Post updatePost(@RequestBody Post p, @PathVariable String groupid, @PathVariable String postid) {
        p.setId(groupid);
        p.setGroup(postid);
        return postService.update(p);
    }

    @DeleteMapping("/{postid}")
    public void deletePost(@PathVariable String groupid, @PathVariable String postid) {
        postService.delete(postid);
    }
}
