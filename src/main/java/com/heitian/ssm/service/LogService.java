package com.heitian.ssm.service;

import com.heitian.ssm.model.TbLogDO;
import com.heitian.ssm.model.info.BaseInfo;

import java.util.List;

/**
 * Created by lxb on 2017/5/6.
 */
public interface LogService {
    public BaseInfo<List<TbLogDO>> queryLog(String phone,TbLogDO logDO, Integer pageNo, Integer pageSize);

    public BaseInfo updateLog(TbLogDO logDO);

    public BaseInfo deleteLog(TbLogDO logDO);

    public BaseInfo addLog(String phone,TbLogDO logDO);


    Long count();
}
