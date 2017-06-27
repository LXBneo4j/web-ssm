/*
 *
 * www.jinvovo.com Inc
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
package com.heitian.ssm.model;


/**
 * A data object class directly models database table <tt>tb_action</tt>.
 * 
 * This file is generated by <tt>iwallet-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>iwallet</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/tb_action.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>iwallet-dalgen</tt> 
 * to generate this file.
 *
 * @author Cheng Li
 * @author Turalyon
 */
public class TbActionDO  extends Entity {
    private static final long serialVersionUID = -4282603875229233564L;

    //========== properties ==========
	/**
	 * 链接
	*/
	protected String url;
	/**
	 * 所属
	*/
	protected Long aid;
	/**
	 * 所属功能名称
	*/
	protected String aname;

	//========== getters and setters ==========
    /**
     * 返回 链接
     */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置 链接
     */
	public void setUrl(String url) {
		this.url = url;
    }
    /**
     * 返回 所属
     */
	public Long getAid() {
		return aid;
	}

	/**
	 * 设置 所属
     */
	public void setAid(Long aid) {
		this.aid = aid;
    }
    /**
     * 返回 所属功能名称
     */
	public String getAname() {
		return aname;
	}

	/**
	 * 设置 所属功能名称
     */
	public void setAname(String aname) {
		this.aname = aname;
    }

}
