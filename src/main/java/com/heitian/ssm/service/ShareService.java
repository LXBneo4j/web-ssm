package com.heitian.ssm.service;

import com.heitian.ssm.model.TbRepositoryDO;
import com.heitian.ssm.model.info.BaseInfo;

import java.util.List;

/**
 * Created by lxb on 2017/5/6.
 */
public interface ShareService {

    public BaseInfo addShare(TbRepositoryDO shareDO);

    public BaseInfo deleteshare(TbRepositoryDO shareDO);

    public BaseInfo updateshare(TbRepositoryDO shareDO);

    public BaseInfo<List<TbRepositoryDO>> queryshare(String phone,TbRepositoryDO shareDO, Integer pageNo, Integer pageSize);

    Long count();
}
