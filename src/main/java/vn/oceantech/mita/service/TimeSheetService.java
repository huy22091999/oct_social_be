package vn.oceantech.mita.service;

import vn.oceantech.mita.dto.TimeSheetDto;

import java.util.List;


public interface TimeSheetService {

    List<TimeSheetDto> getAllByUser();

    TimeSheetDto checkIn(String ip);
}
