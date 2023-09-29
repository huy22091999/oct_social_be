package vn.oceantech.mita.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import vn.oceantech.mita.domain.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

@Setter
@Getter
public class PostsDto implements Serializable {
    Long id;
    String content;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    Date date;

    UserDto user;

    List<DocumentDto> media;

    List<LikesDto> likes;

    List<CommentsDto> comments;

    public PostsDto() {
    }

    public PostsDto(Posts entity) {
        id = entity.getId();
        content = entity.getContent();
        date = entity.getDate();
        user = new UserDto(entity.getUser());
        if (entity.getMedia() != null && entity.getMedia().size() > 0){
            media = new ArrayList<>();
            entity.getMedia().forEach(document -> media.add(new DocumentDto(document)));
        }
        if (entity.getLikes() != null && entity.getLikes().size() > 0){
            likes = new ArrayList<>();
            entity.getLikes().forEach(document -> likes.add(new LikesDto(document)));
        }
        if (entity.getComments() != null && entity.getComments().size() > 0){
            comments = new ArrayList<>();
            entity.getComments().forEach(document -> comments.add(new CommentsDto(document)));
        }
    }
    public Posts toEntity(){
        Posts entity = new Posts();
        entity.setId(id);
        entity.setUser(user.toEntity());
        entity.setContent(content);
        entity.setDate(date);
        if (media != null && media.size() > 0){
            Set<Document> mediaSet = new HashSet<>();
            media.forEach(document -> mediaSet.add(document.toEntity()));
        }
        if (comments != null && comments.size() > 0){
            Set<Comments> commentsSet = new HashSet<>();
            comments.forEach(document -> commentsSet.add(document.toEntity()));
        }
        if (likes != null && likes.size() > 0){
            Set<Likes> likesSet = new HashSet<>();
            likes.forEach(document -> likesSet.add(document.toEntity()));
        }

        id = entity.getId();
        content = entity.getContent();
        date = entity.getDate();
        user = new UserDto(entity.getUser());

        return entity;
    }

}
