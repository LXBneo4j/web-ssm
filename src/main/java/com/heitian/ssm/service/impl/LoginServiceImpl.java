package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.TbLoginMapper;
import com.heitian.ssm.model.TbLoginDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by lxb on 2017/5/11.
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TbLoginMapper loginMapper;

    @Override
    public BaseInfo updateLogin(TbLoginDO loginDO) {
        BaseInfo info=new BaseInfo();
        List<TbLoginDO> loginDOList=loginMapper.query(loginDO,0,1);
        TbLoginDO resultLoginDO=null;
        if(loginDOList!=null && loginDOList.size()>0){
            resultLoginDO=loginDOList.get(0);
        }
        System.out.println("=="+loginDO.toString());
        System.out.println("=="+resultLoginDO.toString());
        loginDO.setLastLoginTime(new Date());
        loginDO.setId(resultLoginDO.getId());
        if(resultLoginDO==null){
            loginDO.setLoginNumber(1L);
            loginMapper.insert(loginDO);
        }else{
            loginDO.setLoginNumber(resultLoginDO.getLoginNumber()+1);
            loginMapper.update(loginDO);
        }
        info.setSuccess();
        return info;
    }

    @Override
    public BaseInfo updatePassword(TbLoginDO loginDO) {
        BaseInfo info=new BaseInfo();
        String password=loginDO.getPassword();
        loginDO.setPassword(null);
        List<TbLoginDO> loginDOList=loginMapper.query(loginDO,0,1);
        TbLoginDO resultLoginDO=null;
        if(loginDOList!=null && loginDOList.size()>0){
            resultLoginDO=loginDOList.get(0);
        }
        loginDO.setId(resultLoginDO.getId());
        loginDO.setPassword(password);
        loginMapper.update(loginDO);
        info.setSuccess();
        return info;
    }
}
