package vn.oceantech.mita.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.oceantech.mita.domain.User;
import vn.oceantech.mita.dto.TrackingDto;
import vn.oceantech.mita.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u left join fetch u.roles where u.username = ?1")
    User findByUsername(String username);
    @Query("select u from User u where u.email=:email")
    User findByEmail(@Param("email") String email);

    @Query("select u from User u left join fetch u.roles where u.id = ?1")
    Optional<User> findById(Long id);
    @Query("select u from User u left join fetch u.roles where u.username = ?1 and (?2 is null or ?2 <> u.id)")
    User findByUsername(String username,Long id);
    @Query("select new vn.oceantech.mita.dto.UserDto(entity) from User entity")
    List<UserDto> getAll();
    @Query("select new vn.oceantech.mita.dto.UserDto(entity) from User entity")
    Page<UserDto> getPage(Pageable pageable);
}
