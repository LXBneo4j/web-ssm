package com.heitian.ssm.service;

import com.heitian.ssm.model.TbDepartmentDO;
import com.heitian.ssm.model.info.BaseInfo;

import java.util.List;

/**
 * Created by lxb on 2017/5/13.
 */
public interface DepartmentService {
    public BaseInfo addDepartment(TbDepartmentDO departmentDO);
    public BaseInfo deleteDepartment(TbDepartmentDO departmentDO);
    public BaseInfo updateDepartment(TbDepartmentDO departmentDO);
    public BaseInfo<List<TbDepartmentDO>> queryDepartment(TbDepartmentDO departmentDO,Integer pageNo,Integer pageSize);
    public Long count();
}
