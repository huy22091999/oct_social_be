package vn.oceantech.mita.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.oceantech.mita.domain.Document;
import vn.oceantech.mita.domain.Posts;
import vn.oceantech.mita.domain.TimeSheet;
import vn.oceantech.mita.dto.*;
import vn.oceantech.mita.dto.search.SearchDto;
import vn.oceantech.mita.repository.PostsRepository;
import vn.oceantech.mita.repository.TimeSheetRepository;
import vn.oceantech.mita.service.PostsService;
import vn.oceantech.mita.service.TimeSheetService;
import vn.oceantech.mita.service.UserService;
import vn.oceantech.mita.utils.Constants;

import java.util.*;

@Service
public class PostsServiceImpl implements PostsService {
    @Autowired
    PostsRepository repository;

//    @Autowired
//    CommentR repository;
//
//    @Autowired
//    PostsRepository repository;
    @Autowired
    UserService userService;

    @Override
    public AndroidResponseDto<PostsDto> createOrUpdatePosts(PostsDto dto, Long id) {
        if(id==null){
            return createPosts(dto);
        }
        else
            return updatePosts(dto,id);
    }

    @Override
    public Page<PostsDto> getNewPosts(SearchDto dto) {
        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getSize();

        if (pageIndex > 0) {
            pageIndex--;
        } else {
            pageIndex = 0;
        }
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        return repository.getPage(pageable);
    }

    @Override
    public Page<PostsDto> getPostsByUser(SearchDto dto) {
        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getSize();

        if (pageIndex > 0) {
            pageIndex--;
        } else {
            pageIndex = 0;
        }
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return repository.getPageByUser(userService.getCurrentEntityUser(),pageable);
    }

    @Override
    public CommentsDto commentPost(CommentsDto comment, Long idPost) {
        return null;
    }

    @Override
    public LikesDto likePost(LikesDto like, Long idPost) {
        return null;
    }

    private AndroidResponseDto<PostsDto> updatePosts(PostsDto dto, Long id) {
        Optional<Posts> optionalPosts = repository.findById(id);
        if (optionalPosts.isPresent()){
            Posts posts = optionalPosts.get();
            if(dto.getMedia() !=null && dto.getMedia().size()>0){
                Set<Document> postsSet = new HashSet<>();
                dto.getMedia().forEach(documentDto -> postsSet.add(documentDto.toEntity()));
                posts.setMedia(postsSet);
            }
            posts.setContent(dto.getContent());
            return new AndroidResponseDto<>(new PostsDto(repository.save(posts)));
        }
        return new AndroidResponseDto<>("Không tìm thấy bài post",404);
    }

    private AndroidResponseDto<PostsDto> createPosts(PostsDto dto) {
        Posts entity = new Posts();
        entity.setContent(dto.getContent());
        entity.setUser(userService.getCurrentEntityUser());
        entity.setDate(dto.getDate());
        if(dto.getMedia()!=null && dto.getMedia().size()>0){
            Set<Document> documents = new HashSet<>();
            dto.getMedia().forEach(documentDto -> documents.add(documentDto.toEntity()));
            entity.setMedia(documents);
        }
        return new AndroidResponseDto<>(new PostsDto(repository.save(entity)));
    }
}
