package com.heitian.ssm.service;

import com.heitian.ssm.model.TbNoticeDO;
import com.heitian.ssm.model.info.BaseInfo;

import java.util.List;

/**
 * Created by lxb on 2017/5/6.
 */
public interface NoticeService {

    public BaseInfo<List<TbNoticeDO>> queryNotice(String phone,TbNoticeDO noticeDO, Integer pageNo, Integer pageSize);

    public BaseInfo updateNotice(TbNoticeDO noticeDO);

    public BaseInfo deleteNotice(TbNoticeDO noticeDo);

    public BaseInfo addNotice(TbNoticeDO noticeDO);

    Long count();
}
