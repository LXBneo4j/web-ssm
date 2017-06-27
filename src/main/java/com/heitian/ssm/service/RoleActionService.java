package com.heitian.ssm.service;

import com.alibaba.fastjson.util.DeserializeBeanInfo;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.heitian.ssm.model.TbRoleActionDO;
import com.heitian.ssm.model.info.BaseInfo;

/**
 * Created by lxb on 2017/5/21.
 */
public interface RoleActionService {
    public BaseInfo addRoleAction(TbRoleActionDO roleActionDO);
    public BaseInfo updateRoleAction(TbRoleActionDO roleActionDO);
}
