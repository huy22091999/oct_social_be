package vn.oceantech.mita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.oceantech.mita.domain.Tracking;
import vn.oceantech.mita.dto.TrackingDto;

import java.util.List;
@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {

    @Query("select new vn.oceantech.mita.dto.TrackingDto(entity) from Tracking entity")
    List<TrackingDto> getAll();

    @Query("select new vn.oceantech.mita.dto.TrackingDto(entity) from Tracking entity where entity.user.id = ?1")
    List<TrackingDto> getAllByUser(Long userId);


}
