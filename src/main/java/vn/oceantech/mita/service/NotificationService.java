package vn.oceantech.mita.service;

import vn.oceantech.mita.dto.NotificationDto;
import vn.oceantech.mita.dto.UserDto;

import java.util.Date;
import java.util.List;


public interface NotificationService {
    List<NotificationDto> getAllByUser();

    NotificationDto create(UserDto userDto, String title, String body, Date dateCreate,String type);

}
