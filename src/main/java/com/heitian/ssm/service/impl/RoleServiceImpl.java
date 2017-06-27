package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.TbDepartmentMapper;
import com.heitian.ssm.dao.TbEmployeeRoleMapper;
import com.heitian.ssm.dao.TbRoleMapper;
import com.heitian.ssm.model.TbDepartmentDO;
import com.heitian.ssm.model.TbEmployeeRoleDO;
import com.heitian.ssm.model.TbRoleDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.RoleService;
import com.heitian.ssm.utils.PageValidate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxb on 2017/5/6.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private TbEmployeeRoleMapper employeeRoleMapper;
    @Autowired
    private TbRoleMapper roleMapper;
    @Autowired
    private TbDepartmentMapper departmentMapper;


    public List<TbRoleDO> getEmpRoleByEmp(TbEmployeeRoleDO employeeRoleDO) {
        List<TbEmployeeRoleDO> employeeRoleDOS=employeeRoleMapper.query(employeeRoleDO);
        //用户角色
        List<TbRoleDO> roleDOList=null;
        List idList=new ArrayList();
        if(employeeRoleDOS!=null && employeeRoleDOS.size()>0){
            for(TbEmployeeRoleDO er:employeeRoleDOS){
                idList.add(er.getRid());
            }
            roleDOList=roleMapper.findByIdIn(idList);
        }
        System.out.println(roleDOList);
        return roleDOList;
    }

    public BaseInfo addRole(TbRoleDO roleDO){
        if(roleDO.getDid()==null){
            TbDepartmentDO parent=new TbDepartmentDO();
            parent.setName(roleDO.getDname());
            List<TbDepartmentDO> list=departmentMapper.query(parent,0,1);
            if(CollectionUtils.isNotEmpty(list)){
                roleDO.setDid(list.get(0).getId());
            }else{
                roleDO.setDid(0L);
            }
        }
        BaseInfo info=new BaseInfo();
        roleMapper.insert(roleDO);
        info.setSuccess();
        return info;
    }

    public BaseInfo deleteRole(TbRoleDO roleDO){
        BaseInfo info=new BaseInfo();
        roleMapper.delete(roleDO.getId());
        info.setSuccess();
        return info;
    }

    public BaseInfo updateRole(TbRoleDO roleDO){
        BaseInfo info=new BaseInfo();
        roleMapper.update(roleDO);
        info.setSuccess();
        return info;
    }

    public BaseInfo<List<TbRoleDO>> queryRole(TbRoleDO roleDO,Integer pageNo,Integer pageSize){
        BaseInfo<List<TbRoleDO>> info=new BaseInfo<>();
        if(!PageValidate.validate(pageNo,pageSize)){
            info.setFail();
            return info;
        }
        List<TbRoleDO> list=roleMapper.query(roleDO,(pageNo-1)*pageSize,pageSize);
        info.setSuccess();
        info.setT(list);
        return info;
    }


    public Long count(){
        TbRoleDO roleDO=new TbRoleDO();
        return roleMapper.queryCount(roleDO);
    }

}
