package vn.oceantech.mita.dto;
import lombok.Getter;
import lombok.Setter;
import vn.oceantech.mita.domain.Document;
import vn.oceantech.mita.domain.Posts;

@Getter
@Setter
public class DocumentDto  {

	public DocumentDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	private Long id;
	private String contentType;
	private Long contentSize;
	private String name;
	private String extension;
	private String filePath;
	private Boolean isVideo;
	PostsDto posts;

	public DocumentDto(Document file) {
		if (file != null) {
			this.id = file.getId();
			this.setContentSize(file.getContentSize());
			this.setContentType(file.getContentType());
			this.setFilePath(file.getFilePath());
			this.setName(file.getName());
			this.setExtension(file.getExtension());
			this.setIsVideo(file.getIsVideo());
			this.setPosts(new PostsDto(file.getPost()));
		}
	}
	public Document toEntity() {
		Document entity = new Document();
		entity.setId(this.getId());
		entity.setContentSize(this.getContentSize());
		entity.setContentType(this.getContentType());
		entity.setFilePath(this.getFilePath());
		entity.setName(this.getName());
		entity.setExtension(this.getExtension());
		entity.setIsVideo(this.getIsVideo());
		return  entity;
	}

}
