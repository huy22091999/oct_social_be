package vn.oceantech.mita.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.oceantech.mita.dto.TimeSheetDto;
import vn.oceantech.mita.service.TimeSheetService;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/time-sheets")
public class TimeSheetRestController {
    @Autowired
    TimeSheetService service;

    @GetMapping("")
    public List<TimeSheetDto> getAllByUser() {
        return service.getAllByUser();
    }

    @GetMapping("/check-in")
    public TimeSheetDto checkIn(@RequestParam(name = "ip") String ip) {
        return service.checkIn(ip);
    }



}
