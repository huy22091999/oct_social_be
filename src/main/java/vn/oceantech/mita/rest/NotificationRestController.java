package vn.oceantech.mita.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.oceantech.mita.dto.NotificationDto;
import vn.oceantech.mita.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationRestController {
    @Autowired
    NotificationService service;

    @GetMapping("")
    public List<NotificationDto> getAllByUser() {
        return service.getAllByUser();
    }

}
