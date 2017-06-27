/*
 *
 * www.jinvovo.com Inc
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
package com.heitian.ssm.model;


/**
 * A data object class directly models database table <tt>tb_look_repository</tt>.
 * 
 * This file is generated by <tt>iwallet-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>iwallet</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/tb_look_repository.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>iwallet-dalgen</tt> 
 * to generate this file.
 *
 * @author Cheng Li
 * @author Turalyon
 */
public class TbLookRepositoryDO  extends Entity {
    private static final long serialVersionUID = -4282603875229233564L;

    //========== properties ==========
	/**
	 * 查看的知识库链接id
	*/
	protected Long rid;
	/**
	 * 查看人id
	*/
	protected Long eid;
	/**
	 * 查看人名字
	*/
	protected String ename;

	//========== getters and setters ==========
    /**
     * 返回 查看的知识库链接id
     */
	public Long getRid() {
		return rid;
	}

	/**
	 * 设置 查看的知识库链接id
     */
	public void setRid(Long rid) {
		this.rid = rid;
    }
    /**
     * 返回 查看人id
     */
	public Long getEid() {
		return eid;
	}

	/**
	 * 设置 查看人id
     */
	public void setEid(Long eid) {
		this.eid = eid;
    }
    /**
     * 返回 查看人名字
     */
	public String getEname() {
		return ename;
	}

	/**
	 * 设置 查看人名字
     */
	public void setEname(String ename) {
		this.ename = ename;
    }

}
