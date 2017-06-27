/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-02-28 00:44 创建
 *
 */
package com.heitian.ssm.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @author turalyon@jinvovo.com
 * 修改了实体类，不完全是自动生成的参数；所以demoUserDO与数据库表不完全对应
 */
public abstract class Entity {
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
	 * 返回 id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置 id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getRemark () {
		return remark;
	}
	
	public void setRemark (String remark) {
		this.remark = remark;
	}
	
	public Long getCreator () {
		return creator;
	}
	
	public void setCreator (Long creator) {
		this.creator = creator;
	}
	
	public Long getUpdater () {
		return updater;
	}
	
	public void setUpdater (Long updater) {
		this.updater = updater;
	}
	
	public Date getCreateTime () {
		return createTime;
	}
	
	public void setCreateTime (Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime () {
		return updateTime;
	}
	
	public void setUpdateTime (Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
