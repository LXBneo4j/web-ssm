package com.heitian.ssm.model.info;

import com.heitian.ssm.model.TbActionDO;
import com.heitian.ssm.model.TbEmployeeDO;
import com.heitian.ssm.model.TbLoginDO;


import java.util.List;

/**
 * Created by lxb on 2017/5/5.
 */
public class LoginResultInfo extends BaseInfo {
    protected TbEmployeeDO user;

//    protected TbLoginDO login;

    protected  List<TbActionDO> lists;

    public List<TbActionDO> getLists() {
        return lists;
    }

    public void setLists(List<TbActionDO> lists) {
        this.lists = lists;
    }

    public TbEmployeeDO getUser() {
        return user;
    }

    public void setUser(TbEmployeeDO user) {
        this.user = user;
    }


}
