package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.TbEmployeeMapper;
import com.heitian.ssm.dao.TbEmployeeRoleMapper;
import com.heitian.ssm.dao.TbLookRepositoryMapper;
import com.heitian.ssm.dao.TbRepositoryMapper;
import com.heitian.ssm.model.TbEmployeeDO;
import com.heitian.ssm.model.TbEmployeeRoleDO;
import com.heitian.ssm.model.TbLookRepositoryDO;
import com.heitian.ssm.model.TbRepositoryDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.ShareService;
import com.heitian.ssm.utils.PageValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by lxb on 2017/5/6.
 */
@Controller
@Transactional
public class ShareServiceImpl implements ShareService {
    @Autowired
    private TbRepositoryMapper shareMapper;
    @Autowired
    private TbEmployeeMapper employeeMapper;

    @Autowired
    private TbLookRepositoryMapper lookRepositoryMapper;
    /**
     * 添加分享
     * @param shareDO
     * @return
     */
    public BaseInfo addShare(TbRepositoryDO shareDO){
        BaseInfo info=new BaseInfo();
        shareDO.setLookNumber(0L);
        shareMapper.insert(shareDO);
        info.setSuccess();
        return info;
    }

    public BaseInfo deleteshare(TbRepositoryDO shareDO){
        BaseInfo info=new BaseInfo();
        shareMapper.delete(shareDO.getId());
        info.setSuccess();
        return info;
    }

    public BaseInfo updateshare(TbRepositoryDO shareDO){
        BaseInfo info=new BaseInfo();
        shareMapper.update(shareDO);
        info.setSuccess();
        return info;
    }

/**
 * 添加分享
 * @param phone
 * @param shareDO
 * @param pageNo
 * @param pageSize
 * @return
 */
public BaseInfo<List<TbRepositoryDO>> queryshare(String phone,TbRepositoryDO shareDO, Integer pageNo, Integer pageSize){
    BaseInfo<List<TbRepositoryDO>> info=new BaseInfo<>();
    if(!PageValidate.validate(pageNo,pageSize)){
        info.setFail();
        return info;
    }
    TbEmployeeDO employeeDO=new TbEmployeeDO();
    employeeDO.setPhone(phone);
    List<TbEmployeeDO> employeeRoleDOList=employeeMapper.query(employeeDO,0,1);
    TbEmployeeDO resultEmployee=employeeRoleDOList.get(0);

    List<TbRepositoryDO> list=shareMapper.query(shareDO,(pageNo-1)*pageSize,pageSize);

    try{
        for(TbRepositoryDO repositoryDO:list){
            repositoryDO.setLookNumber(repositoryDO.getLookNumber()+1);
            shareMapper.update(repositoryDO);
            TbLookRepositoryDO lookRepositoryDO=new TbLookRepositoryDO();
            lookRepositoryDO.setRid(repositoryDO.getId());
            lookRepositoryDO.setEid(resultEmployee.getId());
            lookRepositoryDO.setName(resultEmployee.getName());
            lookRepositoryMapper.insert(lookRepositoryDO);
        }

    }catch (Exception e){
        System.out.println("更新信息失败");
    }



    info.setSuccess();
    info.setT(list);
    return info;
}

    @Override
    public Long count() {
        TbRepositoryDO repositoryDO=new TbRepositoryDO();
        return shareMapper.queryCount(repositoryDO);
    }
}
