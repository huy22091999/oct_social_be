package vn.oceantech.mita.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.oceantech.mita.domain.Document;
import vn.oceantech.mita.domain.Posts;
import vn.oceantech.mita.domain.User;
import vn.oceantech.mita.dto.PostsDto;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("select new vn.oceantech.mita.dto.PostsDto(entity) from Posts entity where entity.user = ?1 order by entity.date ASC ")
    Page<PostsDto> getPageByUser(User user, Pageable pageable);

    @Query("select new vn.oceantech.mita.dto.PostsDto(entity) from Posts entity order by entity.date ASC")
    Page<PostsDto> getPage(Pageable pageable);

}
