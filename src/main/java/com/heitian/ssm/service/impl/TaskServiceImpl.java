package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.TbEmployeeMapper;
import com.heitian.ssm.dao.TbTaskMapper;
import com.heitian.ssm.model.TbActionDO;
import com.heitian.ssm.model.TbEmployeeDO;
import com.heitian.ssm.model.TbTaskDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.TaskService;
import com.heitian.ssm.utils.PageValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by lxb on 2017/5/6.
 */
@Controller
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TbTaskMapper taskMapper;
    @Autowired
    private TbEmployeeMapper employeeMapper;
    /**
     *发布任务
     */
    public BaseInfo addTask(TbTaskDO taskDO){
        taskDO.setState(0L);
        BaseInfo info=new BaseInfo();
        taskMapper.insert(taskDO);
        info.setSuccess();
        return info;
    }
    public BaseInfo deleteTask(TbTaskDO taskDO){
        BaseInfo info=new BaseInfo();
        taskMapper.delete(taskDO.getId());
        info.setSuccess();
        return info;
    }

/**
 * 更新任务信息
 * @param taskDO
 * @return
 */
public BaseInfo updateTask(TbTaskDO taskDO){
    BaseInfo info=new BaseInfo();
    taskMapper.update(taskDO);
    info.setSuccess();
    return info;
}

/**
 * 分页查询
 * @param taskDO
 * @param pageNo
 * @param pageSize
 * @return
 */
public BaseInfo<List<TbTaskDO>> queryTask(String phone,TbTaskDO taskDO, Integer pageNo, Integer pageSize){

    TbEmployeeDO employeeDO=new TbEmployeeDO();
    employeeDO.setPhone(phone);
    List<TbEmployeeDO> employeeRoleDOList=employeeMapper.query(employeeDO,0,1);
    TbEmployeeDO resultEmployee=employeeRoleDOList.get(0);

    taskDO.setCreator(resultEmployee.getId());
    taskDO.setCreateTime(new Date());
    taskDO.setUpdateTime(new Date());
    BaseInfo<List<TbTaskDO>> info=new BaseInfo<>();
    if(!PageValidate.validate(pageNo,pageSize)){
        info.setFail();
        return info;
    }
    List<TbTaskDO> list=taskMapper.query(taskDO,(pageNo-1)*pageSize,pageSize);
    info.setSuccess();
    info.setT(list);
    return info;
}


}
