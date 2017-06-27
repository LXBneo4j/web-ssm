package com.heitian.ssm.service;

import com.heitian.ssm.model.TbEmployeeRoleDO;
import com.heitian.ssm.model.info.BaseInfo;

/**
 * Created by lxb on 2017/5/21.
 */
public interface EmployeeRoleService {

    public BaseInfo addEmployeeRole(TbEmployeeRoleDO employeeRoleDO);
    BaseInfo updateEmployeeRole(TbEmployeeRoleDO employeeRoleDO);
}
