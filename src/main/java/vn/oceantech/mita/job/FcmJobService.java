package vn.oceantech.mita.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.oceantech.mita.dto.NotificationDto;
import vn.oceantech.mita.dto.UserDto;
import vn.oceantech.mita.service.FirebaseService;
import vn.oceantech.mita.service.NotificationService;
import vn.oceantech.mita.service.UserService;

import java.util.Date;
import java.util.List;

@Service
public class FcmJobService {
    @Autowired
    FirebaseService fcm;

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    @Scheduled(cron = "0 0 7 * * *")
    void pushNotificationInMorning() {
        List<UserDto> userDtos = userService.getAllUser();
        for (UserDto dto : userDtos) {
            NotificationDto notificationDto = notificationService.create(dto, "Chào buổi sáng", "Chúc bạn một ngày mới tốt làng!", new Date(), "N01");
            if (dto.getTokenDevice() != null) {
                fcm.sendNotification(notificationDto, dto.getTokenDevice());
            }
        }
    }

    @Scheduled(cron = "0 0 18 * * *")
    void pushNotificationRemindCheckAndTrace() {
        List<UserDto> userDtos = userService.getAllUser();
        for (UserDto dto : userDtos) {
            NotificationDto notificationDto = notificationService.create(dto, "Nhắc nhở", "Đừng quên checkin và tracking hôm nay nhé!", new Date(), "N02");
            if (dto.getTokenDevice() != null) {
                fcm.sendNotification(notificationDto, dto.getTokenDevice());
            }
        }
    }

    @Scheduled(cron = "0 0 21 * * *")
    void pushNotificationInEvening() {
        List<UserDto> userDtos = userService.getAllUser();
        for (UserDto dto : userDtos) {
            NotificationDto notificationDto = notificationService.create(dto, "Chào buổi tối", "Chúc bạn buổi tối an lành!", new Date(), "N03");
            if (dto.getTokenDevice() != null) {
                fcm.sendNotification(notificationDto, dto.getTokenDevice());
            }
        }
    }

}
