package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.TbEmployeeMapper;
import com.heitian.ssm.dao.TbLookNoticeMapper;
import com.heitian.ssm.dao.TbNoticeMapper;
import com.heitian.ssm.model.TbEmployeeDO;
import com.heitian.ssm.model.TbLookNoticeDO;
import com.heitian.ssm.model.TbNoticeDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.NoticeService;
import com.heitian.ssm.utils.PageValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by lxb on 2017/5/6.
 */
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    private TbNoticeMapper noticeMapper;
    @Autowired
    private TbEmployeeMapper employeeMapper;
    @Autowired
    private TbLookNoticeMapper lookNoticeMapper;
    /**
     * 发布公告
     * @param noticeDO
     * @return
     */
    public BaseInfo addNotice(TbNoticeDO noticeDO){
        noticeDO.setLookNumber(0L);
        if(noticeDO.getName()==null){
            noticeDO.setName("公告");
        }
        if(noticeDO.getCategory()==null){
            noticeDO.setCategory(0L);
        }
        noticeDO.setCreateTime(new Date());
        noticeDO.setUpdateTime(new Date());
        if(noticeDO.getCategory()==null){
            noticeDO.setCategory(0L);
        }
        BaseInfo info=new BaseInfo();
        noticeMapper.insert(noticeDO);
        info.setSuccess();
        return info;
    }


    public BaseInfo deleteNotice(TbNoticeDO noticeDo){
        BaseInfo info=new BaseInfo();
        noticeMapper.delete(noticeDo.getId());
        info.setSuccess();
        return info;
    }

    public BaseInfo updateNotice(TbNoticeDO noticeDO){
        BaseInfo info=new BaseInfo();
        noticeMapper.update(noticeDO);
        info.setSuccess();
        return info;
    }

    /**
     *
     * @param phone
     * @param noticeDO
     * @param pageNo
     * @param pageSize
     * @return
     */
    public BaseInfo<List<TbNoticeDO>> queryNotice(String phone,TbNoticeDO noticeDO, Integer pageNo, Integer pageSize){
        TbEmployeeDO employeeDO=new TbEmployeeDO();
        employeeDO.setPhone(phone);
        List<TbEmployeeDO> employeeRoleDOList=employeeMapper.query(employeeDO,0,1);
        TbEmployeeDO resultEmployee=employeeRoleDOList.get(0);


        BaseInfo<List<TbNoticeDO>> info=new BaseInfo<>();
        if(!PageValidate.validate(pageNo,pageSize)){
            info.setFail();
            return info;
        }

        List<TbNoticeDO> list=noticeMapper.query(noticeDO,(pageNo-1)*pageSize,pageSize);

        TbNoticeDO noticeDO1=new TbNoticeDO();
        for(TbNoticeDO notice:list){
            noticeDO1.setId(notice.getId());
            noticeDO1.setLookNumber(notice.getLookNumber()+1);
            updateNotice(noticeDO1);

            //查看记录
            TbLookNoticeDO lookNoticeDO=new TbLookNoticeDO();
            lookNoticeDO.setRid(notice.getId());
            lookNoticeDO.setCreator(resultEmployee.getId());
            lookNoticeDO.setCreateTime(new Date());
            lookNoticeDO.setUpdateTime(new Date());
            lookNoticeMapper.insert(lookNoticeDO);
        }
        info.setSuccess();
        info.setT(list);
        return info;
    }

    @Override
    public Long count() {
        TbNoticeDO noticeDO=new TbNoticeDO();
        return noticeMapper.queryCount(noticeDO);
    }
}
