package vn.oceantech.mita.service;


import org.springframework.data.domain.Page;
import vn.oceantech.mita.domain.User;
import vn.oceantech.mita.dto.AndroidResponseDto;
import vn.oceantech.mita.dto.UserDto;
import vn.oceantech.mita.dto.search.SearchDto;

import java.util.List;

public interface UserService {
    User findEntityByUsername(String username);

    UserDto findByUsername(String username);

    UserDto findByEmail(String email);

    UserDto save(UserDto user);

    AndroidResponseDto<UserDto> createOrUpdate(UserDto userDto);

    Page<UserDto> findByPage(int pageIndex, int pageSize);

    UserDto getCurrentUser();

    boolean passwordMatch(UserDto dto);

    UserDto changePassword(UserDto dto);

    boolean emailAlreadyUsed(UserDto dto);

    UserDto findByUserId(Long userId);

    UserDto deleteById(Long userId);

    User updateUserLastLogin(Long userId);

    User saveUser(UserDto userDto);

    User getCurrentEntityUser();

    List<UserDto> getAllUser();

    UserDto blockUser(long id);

    UserDto setTokenDevice(String tokenDevice);

    UserDto getUserById(Long id);

    Page<UserDto> searchByPage(SearchDto dto);

}
