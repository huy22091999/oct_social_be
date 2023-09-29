package vn.oceantech.mita.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.oceantech.mita.domain.TimeSheet;
import vn.oceantech.mita.dto.TimeSheetDto;
import vn.oceantech.mita.dto.UserDto;
import vn.oceantech.mita.repository.TimeSheetRepository;
import vn.oceantech.mita.service.TimeSheetService;
import vn.oceantech.mita.service.UserService;
import vn.oceantech.mita.utils.Constants;

import java.util.Date;
import java.util.List;

@Service
public class TimeSheetServiceImpl implements TimeSheetService {
    @Autowired
    TimeSheetRepository repository;
    @Autowired
    UserService userService;

    @Override
    public List<TimeSheetDto> getAllByUser() {
        UserDto userDto = userService.getCurrentUser();
        return repository.getAllByUser(userDto.getId());
    }

    @Override
    public TimeSheetDto checkIn(String ip) {
        TimeSheet entity = new TimeSheet();
        UserDto userDto = userService.getCurrentUser();
        int countCheckInSameData = repository.countCheckInSameDate(new Date(), userDto.getId());
        if (countCheckInSameData > 0)
            return new TimeSheetDto(Constants.MESSAGE_CHECK_IN_EXIST);
        entity.setUser(userDto.toEntity());
        entity.setDateAttendance(new Date());
        entity.setIp(ip);
        entity = repository.save(entity);
        TimeSheetDto result = new TimeSheetDto(entity);
        if (result.isOffline())
            result.setMessage(Constants.MESSAGE_OFFLINE);
        else result.setMessage(Constants.MESSAGE_ONLINE);
        return result;
    }
}
