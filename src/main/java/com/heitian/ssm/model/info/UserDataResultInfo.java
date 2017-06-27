package com.heitian.ssm.model.info;

import com.heitian.ssm.model.TbActionDO;
import com.heitian.ssm.model.TbEmployeeRoleDO;
import com.heitian.ssm.model.TbRoleDO;

import java.util.Date;
import java.util.List;

/**
 * Created by lxb on 2017/5/5.
 */
public class UserDataResultInfo extends BaseInfo{
    /**
     * id
     */
    protected Long id;

    /**
     * 名称
     */
    protected  String name;

    /**
     * 描述
     */
    protected  String remark;
    /**
     * 创建人
     */
    protected  Long creator;

    /**
     * 修改人
     */
    protected  Long updater;
    /**
     * 数据新增时间
     */
    protected Date createTime;
    /**
     * 数据修改时间
     */
    protected Date updateTime;
    /**
     * 电话
     */
    protected String phone;
    /**
     * 地址
     */
    protected String address;
    /**
     * 所属部门id
     */
    protected Long did;
    /**
     * 所属部门名称
     */
    protected String dname;

    /**
     * 我的角色列表
     */
    protected  List<TbRoleDO> roles;

    /**
     * 我的功能列表
     */
    protected  List<TbActionDO> acions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public List<TbRoleDO> getRoles() {
        return roles;
    }

    public void setRoles(List<TbRoleDO> roles) {
        this.roles = roles;
    }

    public List<TbActionDO> getAcions() {
        return acions;
    }

    public void setAcions(List<TbActionDO> acions) {
        this.acions = acions;
    }
}
