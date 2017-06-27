package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.TbDepartmentMapper;
import com.heitian.ssm.model.TbDepartmentDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.DepartmentService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by lxb on 2017/5/13.
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private TbDepartmentMapper departmentMapper;
    @Override
    public BaseInfo addDepartment(TbDepartmentDO departmentDO) {
        BaseInfo info=new BaseInfo();
        if(departmentDO.getDid()==null){
            TbDepartmentDO parent=new TbDepartmentDO();
            parent.setName(departmentDO.getDname());
            List<TbDepartmentDO> list=departmentMapper.query(parent,0,1);
            if(CollectionUtils.isNotEmpty(list)){
                departmentDO.setDid(list.get(0).getId());
            }else{
                departmentDO.setDid(0L);
            }
        }
        departmentMapper.insert(departmentDO);
        info.setSuccess();
        return info;
    }

    @Override
    public BaseInfo deleteDepartment(TbDepartmentDO departmentDO) {
        departmentMapper.delete(departmentDO.getId());
        BaseInfo info=new BaseInfo();
        info.setSuccess();
        return info;
    }

    @Override
    public BaseInfo updateDepartment(TbDepartmentDO departmentDO) {
        BaseInfo info=new BaseInfo();
        departmentMapper.update(departmentDO);
        info.setSuccess();
        return info;
    }

    @Override
    public BaseInfo<List<TbDepartmentDO>> queryDepartment(TbDepartmentDO departmentDO, Integer pageNo, Integer pageSize) {
        List<TbDepartmentDO> list=departmentMapper.query(departmentDO,(pageNo-1)*pageSize,pageSize);
        BaseInfo<List<TbDepartmentDO>> info=new BaseInfo<>();
        info.setT(list);
        info.setSuccess();
        return info;
    }

    public Long count(){
        TbDepartmentDO departmentDO=new TbDepartmentDO();
        return departmentMapper.queryCount(departmentDO);
    }
}
