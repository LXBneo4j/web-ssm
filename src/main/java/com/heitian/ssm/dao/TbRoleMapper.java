/*
 *
 * www.jinvovo.com Inc
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
package com.heitian.ssm.dao;

//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;


import com.heitian.ssm.model.TbRoleDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A dao interface provides methods to access database table <tt>tb_role</tt>.
 *
 * This file is generated by <tt>iwallet-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>iwallet</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/tb_role.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>iwallet-dalgen</tt> 
 * to generate this file.
 *
 * @author Cheng Li
 * @author Turalyon
 */
 @SuppressWarnings("rawtypes")
 @Repository
public interface TbRoleMapper {
	/**
	 *  Insert one <tt>TbRoleDO</tt> object to DB table <tt>tb_role</tt>, return primary key
	 *
	 * 插入数据
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into tb_role(id,name,func,level,eid,ename,did,dname,remark,creator,updater,create_time,update_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param tbRole
	 *	@return int
	 */	 

    public int insert(@Param("tbRole") TbRoleDO tbRole);

	/**
	 *  Query DB table <tt>tb_role</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from tb_role</tt>
	 *
	 *	@param tbRole
	 *	@param start
	 *	@param pageSize
	 *	@return List<TbRoleDO>
	 */	 

    public List<TbRoleDO> query(@Param("tbRole") TbRoleDO tbRole, @Param("start") long start,
                                @Param("pageSize") int pageSize);

	/**
	 *  Query DB table <tt>tb_role</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select COUNT(*) from tb_role</tt>
	 *
	 *	@param tbRole
	 *	@return long
	 */	 

    public long queryCount(@Param("tbRole") TbRoleDO tbRole);

	/**
	 *  Update DB table <tt>tb_role</tt>.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update tb_role set name=? where (id = ?)</tt>
	 *
	 *	@param tbRole
	 *	@return int
	 */	 

    public int update(@Param("tbRole") TbRoleDO tbRole);

	/**
	 *  Delete records from DB table <tt>tb_role</tt>.
	 *
	 * 根据主键删除数据
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from tb_role where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 */	 

    public int delete(@Param("id") Long id);

	/**
	 *  Query DB table <tt>tb_role</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from tb_role tbRole where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return TbRoleDO
	 */	 

    public TbRoleDO findById(@Param("id") Long id);

	/**
	 *  Query DB table <tt>tb_role</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from tb_role</tt>
	 *
	 *	@param idList
	 *	@return List<TbRoleDO>
	 */	 

    public List<TbRoleDO> findByIdIn(@Param("idList") List idList);

}
