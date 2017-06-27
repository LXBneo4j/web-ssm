package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.TbActionMapper;
import com.heitian.ssm.dao.TbRoleActionMapper;
import com.heitian.ssm.dao.TbRoleMapper;
import com.heitian.ssm.model.TbActionDO;
import com.heitian.ssm.model.TbRoleActionDO;
import com.heitian.ssm.model.TbRoleDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.RoleActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by lxb on 2017/5/21.
 */
@Service
@Transactional
public class RoleActionServiceImpl implements RoleActionService {
    @Autowired
    private TbRoleActionMapper roleActionMapper;
    @Autowired
    private TbRoleMapper roleMapper;
    @Autowired
    private TbActionMapper actionMapper;

@Override
public BaseInfo addRoleAction(TbRoleActionDO roleActionDO) {
    TbRoleDO roleDO =new TbRoleDO();
    roleDO.setId(roleActionDO.getRid());
    List<TbRoleDO> roleDOList=roleMapper.query(roleDO,0,1);
    TbRoleDO resultRole=roleDOList.get(0);

    TbActionDO actionDO=new TbActionDO();
    actionDO.setId(roleActionDO.getAid());
    List<TbActionDO> actionDOList=actionMapper.query(actionDO,0,1);
    TbActionDO resultAction=actionDOList.get(0);

    roleActionDO.setRname(resultRole.getName());
    roleActionDO.setAname(resultAction.getName());

    roleActionMapper.insert(roleActionDO);
    BaseInfo info=new BaseInfo();
    info.setSuccess();
    return info;
}

    @Override
    public BaseInfo updateRoleAction(TbRoleActionDO roleActionDO) {
        return null;
    }
}
