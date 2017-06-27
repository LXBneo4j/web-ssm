package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.*;
import com.heitian.ssm.model.*;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.model.info.LoginResultInfo;
import com.heitian.ssm.model.info.UserDataResultInfo;
import com.heitian.ssm.service.*;
import com.heitian.ssm.utils.enums.ResultEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxb on 2016/7/15.
 */

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private Logger log=Logger.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private TbEmployeeMapper employeeMapper;
    @Autowired
    private TbLoginMapper loginMapper;
    @Autowired
    private ActionService actionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private TbDepartmentMapper departmentMapper;
    /**
     * 用户登录需要返回用户的角色对应的功能，用户界面显示（1,2级功能）
     * @param phone
     * @param password
     * @return
     */
    @Override
    public LoginResultInfo userLogin(String phone, String password) {
        LoginResultInfo info=new LoginResultInfo();
        //1.查询用户
        TbLoginDO loginDO=new TbLoginDO();
        loginDO.setPhone(phone);
        loginDO.setPassword(password);
        Long count=loginMapper.queryCount(loginDO);
        if(count<=0){
            info.setCode(ResultEnum.LOGIN_FAIL.getCode());
            info.setMessage(ResultEnum.LOGIN_FAIL.getMessage());
            return info;
        }
        TbEmployeeDO employeeDO=new TbEmployeeDO();
        employeeDO.setPhone(phone);
        List<TbEmployeeDO> resultEmployeeDos=employeeMapper.query(employeeDO,0,1);
        Long id=null;
        if (resultEmployeeDos!=null && resultEmployeeDos.size()>0){
            id=resultEmployeeDos.get(0).getId();
        }
        if(id==null){
            info.setCode(ResultEnum.SYSTEM_EXCEPTION.getCode());
            info.setMessage(ResultEnum.SYSTEM_EXCEPTION.getMessage());
            return info;
        }
        info.setUser(resultEmployeeDos.get(0));

        //2.用户角色功能
        TbEmployeeRoleDO employeeRoleDO=new TbEmployeeRoleDO();
        employeeRoleDO.setEid(id);
        List<TbActionDO> list=actionService.getUserRoleAction(employeeRoleDO,3);

        //3.修改用户的登录信息
        try{
            log.info("login:更新登录信息");
            loginService.updateLogin(loginDO);
        }catch (Exception e){
            log.info("更新登录信息失败");
        }
        info.setCode(ResultEnum.LOGIN_SUCCESS.getCode());
        info.setMessage(ResultEnum.LOGIN_SUCCESS.getMessage());
        info.setLists(list);
        return info;
    }





    /**
     * 返回用户数据
     * @param id
     * @return
     */
    @Override
    public UserDataResultInfo getUserData(Long id) {
        //获得用户信息
        UserDataResultInfo info=new UserDataResultInfo();
        TbEmployeeDO employeeDO=new TbEmployeeDO();
        employeeDO.setId(id);
        TbEmployeeDO resultEmployee=getEmployee(employeeDO);
        BeanUtils.copyProperties(resultEmployee,info);

        //获得角色列表
        TbEmployeeRoleDO employeeRoleDO=new TbEmployeeRoleDO();
        employeeRoleDO.setEid(id);
        List<TbRoleDO> roleDOList=roleService.getEmpRoleByEmp(employeeRoleDO);
        info.setRoles(roleDOList);
        //获得所有功能信息
        List<TbActionDO> actionDOList=actionService.getUserRoleAction(employeeRoleDO,3);
        info.setAcions(actionDOList);
        info.setCode(ResultEnum.SUCCESS.getCode());
        info.setMessage(ResultEnum.SUCCESS.getMessage());
        return info;
    }

    /**
     * 查询单个用户信息
     * @param employeeDO
     * @return
     */
    public TbEmployeeDO getEmployee(TbEmployeeDO employeeDO){
        List<TbEmployeeDO> resultEmployeeDos=employeeMapper.query(employeeDO,0,1);
        if(resultEmployeeDos!=null && resultEmployeeDos.size()>0){
            return resultEmployeeDos.get(0);
        }
        return  null;
    }

    public BaseInfo updateSelf(TbEmployeeDO employeeDO){
        BaseInfo info=new BaseInfo();
        employeeMapper.update(employeeDO);
        info.setSuccess();
        return info;
    }

    public BaseInfo deleteEmp(TbEmployeeDO employeeDO){
        BaseInfo info=new BaseInfo();
        employeeMapper.delete(employeeDO.getId());
        info.setSuccess();
        return info;
    }


    /**
     * 增加用户
     * @param employeeDO
     * @return
     */
    public BaseInfo addEmp(TbEmployeeDO employeeDO){
        BaseInfo info=new BaseInfo();
        TbEmployeeDO employeeDO1=new TbEmployeeDO();

        if(employeeDO.getDid()==null){
            TbDepartmentDO departmentDO=new TbDepartmentDO();
            departmentDO.setDname(employeeDO.getDname());
            List<TbDepartmentDO> list=departmentMapper.query(departmentDO,0,1);
            if(CollectionUtils.isNotEmpty(list)){
                employeeDO.setDid(list.get(0).getDid());
            }else{
                employeeDO.setDid(10010L);
            }
        }


        employeeDO1.setPhone(employeeDO.getPhone());
        List<TbEmployeeDO> list=employeeMapper.query(employeeDO1,0,1);
        if(list!=null && list.size()>0){
            employeeMapper.update(employeeDO);
        }else{
            employeeMapper.insert(employeeDO);
        }
        info.setSuccess();
        return info;
    }

    @Override
    public List<TbEmployeeDO> getAllUserData(Integer pageNo,Integer pageSize) {
        TbEmployeeDO employeeDO=new TbEmployeeDO();
        List<TbEmployeeDO> resultEmployeeDos=employeeMapper.query(employeeDO,pageNo,pageSize);
        if(resultEmployeeDos!=null && resultEmployeeDos.size()>0){
            return resultEmployeeDos;
        }
        return  null;
    }

    public Long count(){
        TbEmployeeDO employeeDO=new TbEmployeeDO();
        Long total=employeeMapper.queryCount(employeeDO);
        return total;
    }

    /**
     * 修改用户信息
     * @param employeeDO
     * @return
     */
    @Override
    public BaseInfo<List<TbActionDO>> updateUser(TbEmployeeDO employeeDO) {
        BaseInfo info=new BaseInfo();
        employeeMapper.update(employeeDO);
//        TbActionDO actionDO=new TbActionDO();
//        List<TbActionDO> list=new ArrayList<>();
//        list.add(actionDO);
//
//        info.setT(list);
//
//        try {
//            employeeMapper.insert(null);
//        }catch (Exception e){
////            info.setCode(ResultEnum.DB_EXCEPTION.getCode());
////            info.setMessage(ResultEnum.DB_EXCEPTION.getMessage());
//            info.setDbException();
//            return info;
//        }
        info.setSuccess();

        return info;
    }




}
