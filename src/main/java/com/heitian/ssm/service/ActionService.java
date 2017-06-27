package com.heitian.ssm.service;

import com.heitian.ssm.model.TbActionDO;
import com.heitian.ssm.model.TbEmployeeRoleDO;
import com.heitian.ssm.model.info.BaseInfo;

import java.util.List;

/**
 * Created by lxb on 2017/5/6.
 */
public interface ActionService {
    List<TbActionDO> getUserRoleAction(TbEmployeeRoleDO employeeRoleDO, Integer level);
    public List<TbActionDO> queryAction(TbActionDO actionDO,Integer pageNo,Integer pageSize);
    public BaseInfo updateAction(TbActionDO actionDO);
    public BaseInfo deleteAction(TbActionDO actionDO);
    public BaseInfo addAction(TbActionDO actionDO);

    Long count();
}
