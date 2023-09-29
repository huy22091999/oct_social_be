package vn.oceantech.mita.service;


import org.springframework.data.domain.Page;
import vn.oceantech.mita.domain.Role;
import vn.oceantech.mita.dto.RoleDto;

import java.util.List;

public interface RoleService{
    Role save (Role role);
    Role findByName(String name);

    Role findById(Long roleId);

    Page<Role> findByRoleStudent(int pageIndex, int pageSize);

    RoleDto createRole(RoleDto roleDto);

    RoleDto updateRole(RoleDto roleDto, Long roleId);

    Page<RoleDto> findByRoleStaff(int pageIndex, int pageSize);

    List<RoleDto> findAll();
}