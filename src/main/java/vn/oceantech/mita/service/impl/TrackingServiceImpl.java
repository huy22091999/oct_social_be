package vn.oceantech.mita.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.oceantech.mita.domain.Tracking;
import vn.oceantech.mita.dto.TrackingDto;
import vn.oceantech.mita.dto.UserDto;
import vn.oceantech.mita.repository.TrackingRepository;
import vn.oceantech.mita.service.TrackingService;
import vn.oceantech.mita.service.UserService;

import java.util.List;
@Service
public class TrackingServiceImpl implements TrackingService {
    @Autowired
    TrackingRepository trackingRepository;
    @Autowired
    UserService userService;
    @Override
    public TrackingDto saveOrUpdate(TrackingDto dto, Long id){
        Tracking entity=null;
        if (id!=null){
            entity=trackingRepository.getById(id);
        }
        if (entity==null)
            entity=new Tracking();
        entity.setDate(dto.getDate());
        entity.setContent(dto.getContent());
        UserDto userDto= userService.getCurrentUser();
        entity.setUser(userDto.toEntity());
        trackingRepository.save(entity);
        return new TrackingDto(entity);
    }

    @Override
    public List<TrackingDto> getAllByUser() {
        UserDto userDto= userService.getCurrentUser();
        return trackingRepository.getAllByUser(userDto.getId());
    }

    @Override
    public void delete(Long id) {
        trackingRepository.deleteById(id);
    }



}
