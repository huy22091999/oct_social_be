package vn.oceantech.mita.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.oceantech.mita.dto.VersionDto;
import vn.oceantech.mita.utils.Constants;

@RestController
public class PublicController {

    @GetMapping("/public/config-app")
    public VersionDto getConfigApp() {
        return new VersionDto(Constants.VERSION_NAME);
    }

}
