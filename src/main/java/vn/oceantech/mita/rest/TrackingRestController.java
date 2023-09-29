package vn.oceantech.mita.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.mita.dto.TrackingDto;
import vn.oceantech.mita.service.TrackingService;

import java.util.List;

@RestController
@RequestMapping("/tracking")
public class TrackingRestController {
    @Autowired
    TrackingService trackingService;

    @GetMapping("")
    public List<TrackingDto> getAllByUser(){
        return trackingService.getAllByUser();
    }
    @PostMapping("")
    public TrackingDto save(@RequestBody TrackingDto dto){
        return trackingService.saveOrUpdate(dto,null);
    }
    @PostMapping("/{id}")
    public TrackingDto update(@RequestBody TrackingDto dto, @PathVariable Long id){
        return trackingService.saveOrUpdate(dto,id);
    }
    @DeleteMapping("/{id}")
    public TrackingDto Delete(@PathVariable Long id){
        trackingService.delete(id);
        return new TrackingDto();
    }

}
