package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.TbActionMapper;
import com.heitian.ssm.dao.TbDepartmentMapper;
import com.heitian.ssm.dao.TbEmployeeRoleMapper;
import com.heitian.ssm.dao.TbRoleActionMapper;
import com.heitian.ssm.model.TbActionDO;
import com.heitian.ssm.model.TbDepartmentDO;
import com.heitian.ssm.model.TbEmployeeRoleDO;
import com.heitian.ssm.model.TbRoleActionDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.ActionService;
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
public class ActionServiceImpl implements ActionService {
    @Autowired
    private TbEmployeeRoleMapper employeeRoleMapper;
    @Autowired
    private TbRoleActionMapper roleActionMapper;
    @Autowired
    private TbActionMapper actionMapper;
    @Autowired
    private TbDepartmentMapper departmentMapper;
    @Override
    public List<TbActionDO> getUserRoleAction(TbEmployeeRoleDO employeeRoleDO, Integer level) {
        //System.out.println(1);
        List<TbEmployeeRoleDO> employeeRoleDOS=employeeRoleMapper.query(employeeRoleDO);
        //3.角色功能
        List roleActionDOS=new ArrayList();
        if(employeeRoleDOS!=null){
            for(TbEmployeeRoleDO em:employeeRoleDOS){
                roleActionDOS.add(em.getRid());
            }
        }
        //System.out.println(1);
        List<TbRoleActionDO> lists=null;
        if(roleActionDOS.size()>0){
            lists=roleActionMapper.findByRidIn(roleActionDOS);
        }
        //System.out.println("角色功能："+lists.toString());
        //4.功能信息；查询出来只需要导航信息，其他排除
        List actionDOS=new ArrayList<>();
        if(lists!=null){
            TbActionDO actionDO=new TbActionDO();
            for(TbRoleActionDO acn:lists){
                actionDOS.add(acn.getAid());
            }
        }
        //System.out.println(1);
        List<TbActionDO> list=null;
        if(actionDOS.size()>0){
            list=actionMapper.findByIdIn(actionDOS,level);
        }
        //System.out.println("功能："+list.toString());
        return list;
    }





    /**
     * 简单--增、删、改、查
     * */
    public BaseInfo addAction(TbActionDO actionDO){
        if(actionDO.getAid()==null){
           TbActionDO parent=new TbActionDO();
           parent.setName(actionDO.getAname());
           List<TbActionDO> list=actionMapper.query(parent,0,1);
           if(CollectionUtils.isNotEmpty(list)){
               actionDO.setAid(list.get(0).getId());
               actionDO.setRemark((Integer.valueOf(list.get(0).getRemark())+1)+"");
           }else{
               actionDO.setAid(19L);
               actionDO.setRemark("3");
           }
        }
        BaseInfo info=new BaseInfo();
        actionMapper.insert(actionDO);
        info.setSuccess();
        return info;
    }

    public BaseInfo deleteAction(TbActionDO actionDO){
        BaseInfo info=new BaseInfo();
        actionMapper.delete(actionDO.getId());
        info.setSuccess();
        return info;
    }

    public BaseInfo updateAction(TbActionDO actionDO){
        BaseInfo info=new BaseInfo();
        actionMapper.update(actionDO);//更新一定要有ID
        info.setSuccess();
        return info;
    }

    /**
     * 页码：1,2,3,4......
     * @param actionDO
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<TbActionDO> queryAction(TbActionDO actionDO,Integer pageNo,Integer pageSize){
        if(!PageValidate.validate(pageNo,pageSize)){
            return null;
        }
        List<TbActionDO> list=actionMapper.query(actionDO,(pageNo-1)*pageSize,pageSize);
        return list;
    }

    @Override
    public Long count() {
        TbActionDO actionDO=new TbActionDO();
        return actionMapper.queryCount(actionDO);
    }
}
