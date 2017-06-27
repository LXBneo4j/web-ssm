package com.heitian.ssm.service;


import com.heitian.ssm.model.TbActionDO;
import com.heitian.ssm.model.TbEmployeeDO;
import com.heitian.ssm.model.TbEmployeeRoleDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.model.info.LoginResultInfo;
import com.heitian.ssm.model.info.UserDataResultInfo;

import java.util.List;


/**
 * Created by Zhangxq on 2016/7/15.
 */
public interface EmployeeService {
    LoginResultInfo userLogin(String phone, String password);

    UserDataResultInfo getUserData(Long id);

    public TbEmployeeDO getEmployee(TbEmployeeDO employeeDO);

    public BaseInfo deleteEmp(TbEmployeeDO employeeDO);

    public BaseInfo updateSelf(TbEmployeeDO employeeDO);

    public BaseInfo addEmp(TbEmployeeDO employeeDO);

    public List<TbEmployeeDO> getAllUserData(Integer pageNo,Integer pageSize);

    public Long count();

    BaseInfo updateUser(TbEmployeeDO employeeDO);
}
