package com.heitian.ssm.service;

import com.heitian.ssm.model.TbLoginDO;
import com.heitian.ssm.model.info.BaseInfo;

/**
 * Created by lxb on 2017/5/11.
 */
public interface LoginService {
     BaseInfo updateLogin(TbLoginDO loginDO);

     BaseInfo updatePassword(TbLoginDO loginDO);

}
