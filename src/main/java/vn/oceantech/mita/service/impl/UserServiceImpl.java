package vn.oceantech.mita.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.oceantech.mita.domain.Role;
import vn.oceantech.mita.domain.User;
import vn.oceantech.mita.dto.AndroidResponseDto;
import vn.oceantech.mita.dto.RoleDto;
import vn.oceantech.mita.dto.UserDto;
import vn.oceantech.mita.dto.search.SearchDto;
import vn.oceantech.mita.repository.RoleRepository;
import vn.oceantech.mita.repository.TimeSheetRepository;
import vn.oceantech.mita.repository.TrackingRepository;
import vn.oceantech.mita.repository.UserRepository;
import vn.oceantech.mita.service.UserService;
import vn.oceantech.mita.utils.CommonUtils;
import vn.oceantech.mita.utils.Constants;
import vn.oceantech.mita.utils.SecurityUtils;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepos;

    @Autowired
    private TrackingRepository trackingRepository;

    @Autowired
    private TimeSheetRepository timeSheetRepository;

    public UserServiceImpl() {
    }

    @Transactional(
            readOnly = true
    )
    public UserDto findByUserId(Long userId) {
        User user = (User) this.userRepository.getOne(userId);
        return user != null ? new UserDto(user) : null;
    }

    public UserDto deleteById(Long userId) {
        User user = (User) this.userRepository.getOne(userId);
        if (user != null) {
            this.userRepository.delete(user);
            return new UserDto(user);
        } else {
            return null;
        }
    }

    @Transactional(
            readOnly = true
    )
    public UserDto findByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        return user != null ? new UserDto(user) : null;
    }

    @Transactional(
            readOnly = true
    )
    public UserDto findByEmail(String email) {
        User retUser = this.userRepository.findByEmail(email);
        if (retUser != null) {
            UserDto dto = new UserDto(retUser);
            dto.setPassword((String) null);
            return dto;
        } else {
            return null;
        }
    }

    @Transactional(
            readOnly = true
    )
    public Page<UserDto> findByPage(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<User> page = this.userRepository.findAll(pageable);
        List<User> _content = page.getContent();
        List<UserDto> content = new ArrayList();
        Iterator var7 = _content.iterator();

        while (var7.hasNext()) {
            User entity = (User) var7.next();
            content.add(new UserDto(entity));
        }

        return new PageImpl(content, pageable, page.getTotalElements());
    }

    @Transactional(
            readOnly = true
    )
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String userName = null;
        if (principal instanceof UserDetails) {
            UserDetails userDetail = (UserDetails) principal;
            userName = userDetail.getUsername();
        } else {
            userName = principal.toString();
        }

        if (userName != null) {
            User entity = this.userRepository.findByUsername(userName);
            if (entity != null) {
                return new UserDto(entity);
            }
        }

        return null;
    }

    @Transactional(
            readOnly = true
    )
    public User getCurrentEntityUser() {
        Object principal = SecurityUtils.getPrincipal();
        String userName = null;
        if (principal instanceof UserDetails) {
            UserDetails userDetail = (UserDetails) principal;
            userName = userDetail.getUsername();
        } else {
            userName = principal.toString();
        }

        if (userName != null) {
            User entity = this.userRepository.findByUsername(userName);
            return entity;
        } else {
            return null;
        }
    }


    @Transactional(
            rollbackFor = {Exception.class}
    )
    public User saveUser(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException();
        } else {
            User user = null;
            if (CommonUtils.isPositive(userDto.getId(), true)) {
                user = (User) this.userRepository.getOne(userDto.getId());
            }

            if (user == null) {
                user = userDto.toEntity();
                user.setJustCreated(true);
                if (userDto.getPassword() != null && userDto.getPassword().length() > 0) {
                    user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
                }
            } else {
                user.setUsername(userDto.getUsername());
                user.setEmail(userDto.getEmail());
                if (userDto.getPassword() != null && userDto.getPassword().length() > 0) {
                    user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
                }
            }

            ArrayList gs;
            Iterator var4;
            if (userDto.getRoles() != null) {
                gs = new ArrayList();
                var4 = userDto.getRoles().iterator();

                while (var4.hasNext()) {
                    RoleDto d = (RoleDto) var4.next();
                    Role r = (Role) this.roleRepos.getOne(d.getId());
                    if (r != null) {
                        gs.add(r);
                    }
                }

                user.getRoles().clear();
                user.getRoles().addAll(gs);
            }


            if (userDto.getActive() != null) {
                user.setActive(userDto.getActive());
            } else {
                user.setActive(false);
            }
            user = (User) this.userRepository.save(user);
            return user;
        }
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public UserDto save(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException();
        } else {
            User user = null;
            if (userDto.getId() != null) {
                user = (User) this.userRepository.getOne(userDto.getId());
            }

            if (user == null) {
                user = userDto.toEntity();
                user.setJustCreated(true);
                if (userDto.getPassword() != null && userDto.getPassword().length() > 0) {
                    user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
                }
            } else {
                user.setYear(userDto.getYear());
                user.setUniversity(userDto.getUniversity());
                user.setUsername(userDto.getUsername());
                user.setDisplayName(userDto.getDisplayName());
                user.setEmail(userDto.getEmail());
                if (userDto.getPassword() != null && userDto.getPassword().length() > 0 && userDto.getChangePass()) {
                    user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
                }
            }


            Iterator var4;
            ArrayList gs;
            if (userDto.getRoles() != null) {
                gs = new ArrayList();
                var4 = userDto.getRoles().iterator();

                while (var4.hasNext()) {
                    RoleDto d = (RoleDto) var4.next();
                    Role r = (Role) this.roleRepos.getOne(d.getId());
                    if (r != null) {
                        gs.add(r);
                    }
                }

                user.getRoles().clear();
                user.getRoles().addAll(gs);
            }
            user.setActive(userDto.getActive());

            user = (User) this.userRepository.save(user);
            return user != null ? new UserDto(user) : null;
        }
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public AndroidResponseDto<UserDto> createOrUpdate(UserDto userDto) {
        AndroidResponseDto<UserDto> response = validate(userDto);
        if (response.getData() != null) {
            Role role = roleRepos.findByName(Constants.ROLE_USER);
            userDto.setActive(true);
            userDto.getRoles().add(new RoleDto(role));
            userDto = save(userDto);
            response.setData(userDto);
        }
        return response;

    }

    private AndroidResponseDto<UserDto> validate(UserDto userDto) {
        if (userDto.getUsername() == null) {
            return new AndroidResponseDto<>("thiếu user name", 501);
        } else {
            User userExist = userRepository.findByUsername(userDto.getUsername(), userDto.getId());
            if (userExist != null)
                return new AndroidResponseDto<>("user name đã tồn tại", 501);
        }
        return new AndroidResponseDto<>(userDto);
    }

    @Transactional(
            readOnly = true
    )
    public boolean passwordMatch(UserDto dto) {
        if (dto != null && CommonUtils.isPositive(dto.getId(), true)) {
            User user = (User) this.userRepository.getOne(dto.getId());
            return user != null ? SecurityUtils.passwordsMatch(user.getPassword(), dto.getPassword()) : false;
        } else {
            return false;
        }
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public UserDto changePassword(UserDto dto) {
        if (dto != null && CommonUtils.isPositive(dto.getId(), true) && !CommonUtils.isEmpty(dto.getPassword())) {
            User user = (User) this.userRepository.getOne(dto.getId());
            if (user == null) {
                return null;
            } else {
                user.setPassword(SecurityUtils.getHashPassword(dto.getPassword()));
                user = (User) this.userRepository.save(user);
                return user == null ? null : new UserDto(user);
            }
        } else {
            return null;
        }
    }

    @Transactional(
            readOnly = true
    )
    public boolean emailAlreadyUsed(UserDto dto) {
        if (dto != null && !CommonUtils.isEmpty(dto.getEmail())) {
            User user = this.userRepository.findByEmail(dto.getEmail());
            return user != null;
        } else {
            return false;
        }
    }

    @Transactional
    public User updateUserLastLogin(Long userId) {
        User user = (User) this.userRepository.getOne(userId);
        user.setLastLoginTime(new Date());
        return (User) this.userRepository.save(user);
    }

    public User findEntityByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public List<UserDto> getAllUser() {
        return this.userRepository.getAll();
    }

    @Override
    public UserDto blockUser(long id) {
        User user = this.userRepository.getOne(id);
        if (user == null)
            return null;
        else {
            user.setActive(false);
            user = userRepository.save(user);
            return new UserDto(user);
        }
    }

    @Override
    public UserDto setTokenDevice(String tokenDevice) {
        User user = getCurrentEntityUser();
        if (user == null)
            return null;
        else {
            user.setTokenDevice(tokenDevice);
            user = userRepository.save(user);
            return new UserDto(user);
        }
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.getById(id);
        UserDto dto = new UserDto(user);
        dto.setCountDayCheckin(timeSheetRepository.getAllByUser(id).size());
        dto.setCountDayTracking(trackingRepository.getAllByUser(id).size());
        return dto;
    }

    @Override
    public Page<UserDto> searchByPage(SearchDto dto){
        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getSize();

        if (pageIndex > 0) {
            pageIndex--;
        } else {
            pageIndex = 0;
        }
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return userRepository.getPage(pageable);
    }

}
