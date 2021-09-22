package com.larus.itiszuccante.web.rest;

import com.larus.itiszuccante.domain.Comment;
import com.larus.itiszuccante.domain.Post;
import com.larus.itiszuccante.service.CommentService;
import com.larus.itiszuccante.service.GroupService;
import com.larus.itiszuccante.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/groups/{groupid}/posts/{postid}/comments")
public class CommentResource {

    @Autowired
    public GroupService groupService;

    @Autowired
    public PostService postService;

    @Autowired
    public CommentService commentService;

    @GetMapping("/")
    public List<Comment> getComments(@PathVariable String groupid, @PathVariable String postid) {
        return commentService.findAllByPost(postid);
    }

    @GetMapping("/{commentid}")
    public ResponseEntity<Comment> getComment(@PathVariable String groupid, @PathVariable String postid, @PathVariable String commentid) {
        return ResponseUtil.wrapOrNotFound(commentService.read(commentid));
    }

    @PostMapping("/")
    public Comment getComments(@RequestBody Comment c, @PathVariable String groupid, @PathVariable String postid) {
        return commentService.create(c);
    }

    @PutMapping("/{commentid}")
    public Comment updatePost(
        @RequestBody Comment c,
        @PathVariable String groupid,
        @PathVariable String postid,
        @PathVariable String commentid
    ) {
        c.setPost(postid);
        return commentService.update(c);
    }

    @DeleteMapping("/{commentid}")
    public void deletePost(@PathVariable String groupid, @PathVariable String postid, @PathVariable String commentid) {
        commentService.delete(postid);
    }
}
