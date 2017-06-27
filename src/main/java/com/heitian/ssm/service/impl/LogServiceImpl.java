package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.TbEmployeeMapper;
import com.heitian.ssm.dao.TbLogMapper;
import com.heitian.ssm.model.TbEmployeeDO;
import com.heitian.ssm.model.TbLogDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.LogService;
import com.heitian.ssm.utils.PageValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by lxb on 2017/5/6.
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private TbLogMapper logMapper;
    @Autowired
    private TbEmployeeMapper employeeMapper;

/**
 * 添加日志
 * @param phone
 * @param logDO
 * @return
 */
public BaseInfo addLog(String phone,TbLogDO logDO){

    TbEmployeeDO employeeDO=new TbEmployeeDO();
    employeeDO.setPhone(phone);
    List<TbEmployeeDO> employeeRoleDOList=employeeMapper.query(employeeDO,0,1);
    TbEmployeeDO resultEmployee=employeeRoleDOList.get(0);

    logDO.setState(0L);//提交设置未通过审核
    logDO.setCreator(resultEmployee.getId());

    BaseInfo info=new BaseInfo();
    logMapper.insert(logDO);
    info.setSuccess();
    return info;
}

    public BaseInfo deleteLog(TbLogDO logDO){
        BaseInfo info=new BaseInfo();
        logMapper.delete(logDO.getId());
        info.setSuccess();
        return info;
    }

    /**
     * 修改日志
     * @param logDO
     * @return
     */
    public BaseInfo updateLog(TbLogDO logDO){
        BaseInfo info=new BaseInfo();
        logMapper.update(logDO);
        info.setSuccess();
        return info;
    }

    /**
     * 查看日志
     * @param logDO
     * @param pageNo
     * @param pageSize
     * @return
     */
    public BaseInfo<List<TbLogDO>>  queryLog(String phone,TbLogDO logDO,Integer pageNo,Integer pageSize){
        TbEmployeeDO employeeDO=new TbEmployeeDO();
        employeeDO.setPhone(phone);
        List<TbEmployeeDO> employeeRoleDOList=employeeMapper.query(employeeDO,0,1);
        TbEmployeeDO resultEmployee=employeeRoleDOList.get(0);
        //由自己审核的才能查看
        logDO.setCheckUser(resultEmployee.getId());
        BaseInfo<List<TbLogDO>> info=new BaseInfo<>();
        if(!PageValidate.validate(pageNo,pageSize)){
            info.setFail();
            return info;
        }
        List<TbLogDO> list=logMapper.query(logDO,(pageNo-1)*pageSize,pageSize);
        info.setSuccess();
        info.setT(list);
        return info;
    }

    @Override
    public Long count() {
        TbLogDO logDO=new TbLogDO();
        return logMapper.queryCount(logDO);
    }
}
