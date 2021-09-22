package com.larus.itiszuccante.service.impl;

import com.larus.itiszuccante.domain.Group;
import com.larus.itiszuccante.domain.Post;
import com.larus.itiszuccante.domain.User;
import com.larus.itiszuccante.repository.GroupRepository;
import com.larus.itiszuccante.repository.PostRepository;
import com.larus.itiszuccante.repository.UserRepository;
import com.larus.itiszuccante.security.AuthoritiesConstants;
import com.larus.itiszuccante.service.PostService;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class DefaultPostService implements PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post create(Post p) {
        return repository.save(p);
    }

    @Override
    public Optional<Post> read(String id) {
        return repository.findById(id);
    }

    @Override
    public Post update(Post p) {
        return repository.save(p);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Post> findAllByGroup(String groupid) {
        return repository.findAllByGroup(groupid);
    }

    @Override
    public boolean isAuthorOrMod(Post p, String groupid, String postid) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();

        if (((Authentication) principal).getAuthorities().contains(AuthoritiesConstants.ADMIN)) return true;

        User currentUser = userRepository.findOneByLogin(principal.getName()).get();

        Group currentGroup = groupRepository.findById(groupid).get();

        Post currentPost = repository.findById(postid).get();

        if (currentGroup.getModerators().contains(currentUser.getId())) return true;

        if (currentPost.getCreatedBy().equals(currentUser.getId())) return true;

        return false;
    }
}
