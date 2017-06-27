package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.TbEmployeeMapper;
import com.heitian.ssm.dao.TbEmployeeRoleMapper;
import com.heitian.ssm.dao.TbRoleMapper;
import com.heitian.ssm.model.TbEmployeeDO;
import com.heitian.ssm.model.TbEmployeeRoleDO;
import com.heitian.ssm.model.TbRoleDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.EmployeeRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by lxb on 2017/5/21.
 */
@Service
@Transactional
public class EmployeeRoleServiceImpl implements EmployeeRoleService {

    @Autowired
    private TbEmployeeMapper employeeMapper;
    @Autowired
    private TbEmployeeRoleMapper employeeRoleMapper;
    @Autowired
    private TbRoleMapper tbRoleMapper;
@Override
public BaseInfo addEmployeeRole(TbEmployeeRoleDO employeeRoleDO) {
    TbEmployeeDO employeeDO=new TbEmployeeDO();
    employeeDO.setId(employeeRoleDO.getEid());
    List<TbEmployeeDO> employeeDOList=employeeMapper.query(employeeDO,0,1);
    TbEmployeeDO resultEmployee=employeeDOList.get(0);

    TbRoleDO roleDO=new TbRoleDO();
    roleDO.setId(employeeRoleDO.getRid());
    List<TbRoleDO> roleDOList=tbRoleMapper.query(roleDO,0,1);
    TbRoleDO resultRole=roleDOList.get(0);

    employeeRoleDO.setRname(resultEmployee.getName());
    employeeRoleDO.setRname(resultRole.getName());
    employeeRoleMapper.insert(employeeRoleDO);
    BaseInfo info=new BaseInfo();
    info.setSuccess();
    return info;
}

    @Override
    public BaseInfo updateEmployeeRole(TbEmployeeRoleDO employeeRoleDO) {
        return null;
    }
}
