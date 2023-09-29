package vn.oceantech.mita.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.mita.domain.Comments;
import vn.oceantech.mita.dto.*;
import vn.oceantech.mita.dto.search.SearchDto;
import vn.oceantech.mita.service.PostsService;
import vn.oceantech.mita.service.TimeSheetService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsRestController {
    @Autowired
    PostsService service;

    @PostMapping("/create")
    public AndroidResponseDto<PostsDto> create(@RequestBody PostsDto dto) {
        return service.createOrUpdatePosts(dto,null);
    }
    @PostMapping("/update/{id}")
    public AndroidResponseDto<PostsDto> update(@RequestBody PostsDto dto,@PathVariable(name = "id") Long id) {
        return service.createOrUpdatePosts(dto,id);
    }

    @PostMapping("/get-news")
    public Page<PostsDto> GetNewPosts(@RequestBody SearchDto searchDto) {
        return service.getNewPosts(searchDto);
    }
    @PostMapping("/get-news-by-user")
    public Page<PostsDto> GetNewPostsByUser(@RequestBody SearchDto searchDto) {
        return service.getPostsByUser(searchDto);
    }

    @PostMapping("/comments/{id}")
    public CommentsDto commentPosts(@RequestBody CommentsDto comments, @PathVariable(name = "id") Long id) {
        return service.commentPost(comments,id);
    }

    @PostMapping("/likes/{id}")
    public LikesDto likePosts(@RequestBody LikesDto likes, @PathVariable(name = "id") Long id) {
        return service.likePost(likes,id);
    }



}
