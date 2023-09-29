package vn.oceantech.mita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.oceantech.mita.domain.Notification;
import vn.oceantech.mita.domain.TimeSheet;
import vn.oceantech.mita.dto.NotificationDto;
import vn.oceantech.mita.dto.TimeSheetDto;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select new vn.oceantech.mita.dto.NotificationDto(entity) from Notification entity where entity.user.id = ?1")
    List<NotificationDto> getAllByUser(Long userId);

    @Query("select count(entity) from TimeSheet entity where entity.user.id = ?2 and DATE(entity.dateAttendance)=?1 ")
    int countCheckInSameDate(Date date, Long userId);

}
