package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.TimeCheck;

import org.example.dto.post.PostDto;
import org.example.dto.wish_list.EmailDto;
import org.example.entity.Post;
import org.example.entity.WishList;
import org.example.repository.PostRepository;
import org.example.repository.WishListRepository;
import org.example.service.member.MemberFeign;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {
    private final PostRepository postRepository;
    private final MemberFeign memberFeign;
    private final WishListRepository wishListRepository;

    @TimeCheck
    public List<String> autoComplete(String word) {
        log.info(word);
        return postRepository.findByPostName(word).stream()
                .map(Post::getPostName)
                .toList();
    }
    @TimeCheck
    public Page<PostDto> searchPost(String postName, int page, List<Integer> category_id, List<String> location,String nickName) {
        page = (page == 0) ? 0 : page+1;
        int pageSize = (page == 0) ? 16 : 8;
        log.info("nickname="+nickName);
        log.info("category_id="+category_id);
        log.info("location="+location);
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "postId"));
        Page<Post> postPage;

        if(category_id == null && location == null)
        {
            postPage = postRepository.findByPostNamePage(postName,pageable);
        }else {
            postPage = postRepository.findPostsByCategoryAndLocation(postName,category_id,location,pageable);
        }


        Page<PostDto> posts= postPage.map(PostDto::ToDto);
        if (nickName!=null) {
            Optional<EmailDto> email = memberFeign.getEmail(nickName);
            List<PostDto> wishs = wishListRepository.findAllByEmail(email.get().getEmail()).get().stream().map(WishList::getPost).toList()
                    .stream().map(PostDto::ToDto).toList();
            posts.forEach(p -> p.setLike(wishs.contains(p)));
        }
        return posts;

    }


}