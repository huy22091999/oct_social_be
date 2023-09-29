package vn.oceantech.mita.service;

import vn.oceantech.mita.dto.TrackingDto;

import java.util.List;



public interface TrackingService {
    TrackingDto saveOrUpdate(TrackingDto dto, Long id);
    List<TrackingDto> getAllByUser();
    void delete(Long id);
}
