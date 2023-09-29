package vn.oceantech.mita.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import vn.oceantech.mita.domain.Role;
import vn.oceantech.mita.dto.RoleDto;
import vn.oceantech.mita.dto.UserDto;
import vn.oceantech.mita.dto.search.SearchDto;
import vn.oceantech.mita.service.RoleService;
import vn.oceantech.mita.service.UserService;
import vn.oceantech.mita.utils.Constants;

@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users/get-user-current")
    public ResponseEntity<UserDto> getUserCurrent() {
        return new ResponseEntity<UserDto>(userService.getCurrentUser(), HttpStatus.OK);
    }

    @PostMapping("/public/sign")
    public ResponseEntity<UserDto> createOrUpdate(@RequestBody UserDto dto) {
        Role role = roleService.findByName(Constants.ROLE_USER);
        dto.getRoles().clear();
        dto.getRoles().add(new RoleDto(role));
        UserDto result = userService.save(dto);
        return new ResponseEntity<UserDto>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/users/searchByPage")
    public ResponseEntity<Page<UserDto>> searchByPage(@RequestBody SearchDto dto) {
        Page<UserDto> result = userService.searchByPage(dto);
        return new ResponseEntity<Page<UserDto>>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> geUserById(@PathVariable(name = "id") Long id) {
        UserDto result = userService.getUserById(id);
        return new ResponseEntity<UserDto>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/lock/{id}")
    @Secured({Constants.ROLE_ADMIN})
    public ResponseEntity<UserDto> blockUser(@PathVariable(name = "id") long id) {
        UserDto result = userService.blockUser(id);
        return new ResponseEntity<UserDto>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/users/update/{id}")
    @Secured({Constants.ROLE_ADMIN})
    public ResponseEntity<UserDto> edit(@PathVariable(name = "id") long id, @RequestBody UserDto dto) {
        dto.setId(id);
        UserDto result = userService.save(dto);
        return new ResponseEntity<UserDto>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/token-device")
    public ResponseEntity<UserDto> edit(@RequestParam(name = "tokenDevice") String tokenDevice) {
        UserDto result = userService.setTokenDevice(tokenDevice);
        return new ResponseEntity<UserDto>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/users/update-myself")
    public ResponseEntity<UserDto> updateMyself(@RequestBody UserDto dto) {
        UserDto current = userService.getCurrentUser();
        dto.setId(current.getId());
        UserDto result = userService.save(dto);
        return new ResponseEntity<UserDto>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


}
