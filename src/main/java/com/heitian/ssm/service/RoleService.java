package com.heitian.ssm.service;

import com.heitian.ssm.model.TbEmployeeRoleDO;
import com.heitian.ssm.model.TbRoleDO;
import com.heitian.ssm.model.info.BaseInfo;

import java.util.List;

/**
 * Created by lxb on 2017/5/6.
 */
public interface RoleService {
    public List<TbRoleDO> getEmpRoleByEmp(TbEmployeeRoleDO employeeRoleDO);

    public BaseInfo addRole(TbRoleDO roleDO);

    public BaseInfo deleteRole(TbRoleDO roleDO);

    public BaseInfo updateRole(TbRoleDO roleDO);

    public BaseInfo<List<TbRoleDO>> queryRole(TbRoleDO roleDO,Integer pageNo,Integer pageSize);

    public Long count();
}
