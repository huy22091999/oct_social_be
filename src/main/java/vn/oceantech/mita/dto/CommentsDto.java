package vn.oceantech.mita.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import vn.oceantech.mita.domain.Comments;
import vn.oceantech.mita.domain.Posts;
import vn.oceantech.mita.domain.User;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class CommentsDto implements Serializable {
    Long id;
    String content;
    PostsDto post;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    Date date;

    UserDto user;

    public CommentsDto() {
    }

    public CommentsDto(Comments entity) {
        id = entity.getId();
        content = entity.getContent();
        post = new PostsDto(entity.getPost());
        date = entity.getDate();
        user = new UserDto(entity.getUser());
    }

    public Comments toEntity() {
        Comments entity = new Comments();
        entity.setId(id);
        entity.setUser(user.toEntity());
        entity.setContent(content);
        entity.setDate(date);
//        entity.setPost(post.toEntity());
        return entity;
    }
}
