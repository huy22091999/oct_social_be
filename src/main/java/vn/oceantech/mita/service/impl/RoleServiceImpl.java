package vn.oceantech.mita.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.oceantech.mita.domain.Role;
import vn.oceantech.mita.domain.User;
import vn.oceantech.mita.dto.RoleDto;
import vn.oceantech.mita.repository.RoleRepository;
import vn.oceantech.mita.service.RoleService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    public RoleServiceImpl() {
    }

    public Page<Role> findRoles(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        return this.roleRepository.findAll(pageable);
    }

    public Page<Role> findByRoleStudent(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        return this.roleRepository.findByRoleStudent(pageable);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role findByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public Role findById(Long roleId) {
        return (Role)this.roleRepository.getOne(roleId);
    }

    public RoleDto createRole(RoleDto roleDto) {
        String currentUserName = "Unknown User";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime currentDate = LocalDateTime.now();
        User modifiedUser = null;
        if (authentication != null) {
            modifiedUser = (User)authentication.getPrincipal();
            currentUserName = modifiedUser.getUsername();
        }

        Role role = null;
        if (roleDto.getId() != null && roleDto.getId() > 0L) {
            role = (Role)this.roleRepository.getOne(roleDto.getId());
        }

        if (role == null) {
            role = new Role();
        }

        if (roleDto.getName() != null && roleDto.getName().length() > 0) {
            role.setName(roleDto.getName());
        }

        if (roleDto.getDescription() != null && roleDto.getDescription().length() > 0) {
            role.setDescription(roleDto.getDescription());
        } else if (roleDto.getDescription() == null || roleDto.getDescription().length() <= 0) {
            role.setDescription((String)null);
        }

        role = (Role)this.roleRepository.save(role);
        return new RoleDto(role);
    }

    public RoleDto updateRole(RoleDto roleDto, Long roleId) {
        String currentUserName = "Unknown User";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime currentDate = LocalDateTime.now();
        User modifiedUser = null;
        if (authentication != null) {
            modifiedUser = (User)authentication.getPrincipal();
            currentUserName = modifiedUser.getUsername();
        }

        Role updateRole = (Role)this.roleRepository.getOne(roleId);
        if (updateRole != null) {
            if (roleDto.getName() != null && roleDto.getName().length() > 0) {
                updateRole.setName(roleDto.getName());
            }

            if (roleDto.getDescription() != null && roleDto.getDescription().length() > 0) {
                updateRole.setDescription(roleDto.getDescription());
            } else if (roleDto.getDescription() == null || roleDto.getDescription().length() <= 0) {
                updateRole.setDescription((String)null);
            }
        }

        updateRole = (Role)this.roleRepository.save(updateRole);
        return new RoleDto(updateRole);
    }

    public Page<RoleDto> findByRoleStaff(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<Role> domains = this.roleRepository.findByRoleStaff(pageable);
        List<RoleDto> dtos = new ArrayList();
        Iterator var6 = domains.iterator();

        while(var6.hasNext()) {
            Role role = (Role)var6.next();
            dtos.add(new RoleDto(role));
        }

        Page<RoleDto> finalPage = new PageImpl(dtos, pageable, domains.getTotalElements());
        return finalPage;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public List<RoleDto> findAll() {
        List<Role> list = this.roleRepository.findAll();
        List<RoleDto> ret = new ArrayList();
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            Role r = (Role)var3.next();
            ret.add(new RoleDto(r));
        }

        return ret;
    }
}
