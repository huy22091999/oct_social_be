package vn.oceantech.mita.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import vn.oceantech.mita.domain.Likes;
import vn.oceantech.mita.domain.Posts;
import vn.oceantech.mita.domain.User;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class LikesDto implements Serializable {

    Long id;
    Integer type;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    Date date;

    UserDto user;

    PostsDto post;


    public LikesDto() {
    }

    public LikesDto(Likes entity) {
        id = entity.getId();
        type = entity.getType();
        date = entity.getDate();
        user = new UserDto(entity.getUser());
        post = new PostsDto(entity.getPost());
    }

    public Likes toEntity() {
        Likes entity = new Likes();
        type = entity.getType();
        date = entity.getDate();
        user = new UserDto(entity.getUser());
        post = new PostsDto(entity.getPost());
        return entity;
    }

}
