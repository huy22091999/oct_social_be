package vn.oceantech.mita.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.oceantech.mita.domain.Notification;
import vn.oceantech.mita.domain.User;
import vn.oceantech.mita.dto.NotificationDto;
import vn.oceantech.mita.dto.UserDto;
import vn.oceantech.mita.repository.NotificationRepository;
import vn.oceantech.mita.service.NotificationService;
import vn.oceantech.mita.service.UserService;

import java.util.Date;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepository repository;
    @Autowired
    UserService userService;

    @Override
    public List<NotificationDto> getAllByUser() {
        UserDto userDto = userService.getCurrentUser();
        return repository.getAllByUser(userDto.getId());
    }

    @Override
    public NotificationDto create(UserDto userDto, String title, String body, Date dateCreate, String type) {
        Notification entity = new Notification();
        entity.setBody(body);
        entity.setTitle(title);
        entity.setDate(dateCreate);
        entity.setType(type);
        User user = new User();
        user.setId(userDto.getId());
        entity.setUser(user);
        return new NotificationDto(repository.save(entity));
    }

}
