package com.heitian.ssm.service;

import com.heitian.ssm.model.TbTaskDO;
import com.heitian.ssm.model.info.BaseInfo;

import java.util.List;

/**
 * Created by lxb on 2017/5/6.
 */
public interface TaskService {
    public BaseInfo<List<TbTaskDO>> queryTask(String phone,TbTaskDO taskDO, Integer pageNo, Integer pageSize);

    public BaseInfo updateTask(TbTaskDO taskDO);

    public BaseInfo deleteTask(TbTaskDO taskDO);

    public BaseInfo addTask(TbTaskDO taskDO);

}
