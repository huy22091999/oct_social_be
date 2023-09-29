package vn.oceantech.mita.domain;

import javafx.geometry.Pos;
import lombok.Getter;
import lombok.Setter;
import vn.oceantech.mita.dto.DocumentDto;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(
        name = "tbl_document"
)
@Getter
@Setter
public class Document {
    private static final long serialVersionUID = 1L;
    @Column(
            name = "content_type"
    )
    private String contentType;
    @Column(
            name = "content_size"
    )
    private Long contentSize;
    @Column(
            name = "name"
    )
    private String name;
    @Column(
            name = "extension"
    )
    private String extension;
    @Column(
            name = "file_path"
    )
    private String filePath;
    @Id
    private Long id;

    private Boolean isVideo;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @ManyToOne
    @JoinColumn(name = "postId")
    Posts post;


    public Document() {
    }

    public Document(String contentType, Long contentSize, String name, String extension, String filePath, Long id, Boolean isVideo, User user) {
        this.contentType = contentType;
        this.contentSize = contentSize;
        this.name = name;
        this.extension = extension;
        this.filePath = filePath;
        this.id = id;
        this.isVideo = isVideo;
        this.user = user;
    }
    public static Document toEntity(DocumentDto file) {
        Document entity = new Document();
        if (file != null) {
            entity.id = file.getId();
            entity.setContentSize(file.getContentSize());
            entity.setContentType(file.getContentType());
            entity.setFilePath(file.getFilePath());
            entity.setName(file.getName());
            entity.setExtension(file.getExtension());
            entity.setIsVideo(file.getIsVideo());
//            entity.setPosts(file.getPosts().t);
        }
        return  entity;
    }
}
