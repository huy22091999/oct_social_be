package vn.oceantech.mita.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.oceantech.mita.domain.Posts;
import vn.oceantech.mita.dto.*;
import vn.oceantech.mita.dto.search.SearchDto;

import java.util.List;


public interface PostsService {

    AndroidResponseDto<PostsDto> createOrUpdatePosts(PostsDto dto, Long id);

    Page<PostsDto> getNewPosts(SearchDto dto);

    Page<PostsDto> getPostsByUser(SearchDto dto);

    CommentsDto commentPost(CommentsDto comment, Long idPost);

    LikesDto likePost(LikesDto like, Long idPost);
}
