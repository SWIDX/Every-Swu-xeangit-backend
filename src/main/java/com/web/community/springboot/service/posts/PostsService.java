package com.web.community.springboot.service.posts;

import com.web.community.springboot.domain.posts.Posts;
import com.web.community.springboot.domain.posts.PostsRepository;
import com.web.community.springboot.web.dto.PostsResponseDto;
import com.web.community.springboot.web.dto.PostsSaveRequestDto;
import com.web.community.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;


    //create
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //read
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }


    //update
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    //delete
    @Transactional
    public void delete(Long id){
        postsRepository.deleteById(id);
    }
}
