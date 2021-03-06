/*
 *
 * www.jinvovo.com Inc
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
package com.heitian.ssm.dao;

//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.heitian.ssm.model.TbEmployeeDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A dao interface provides methods to access database table <tt>tb_employee</tt>.
 *
 * This file is generated by <tt>iwallet-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>iwallet</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/tb_employee.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>iwallet-dalgen</tt> 
 * to generate this file.
 *
 * @author Cheng Li
 * @author Turalyon
 */
 @SuppressWarnings("rawtypes")
 @Repository
public interface TbEmployeeMapper {
	/**
	 *  Insert one <tt>TbEmployeeDO</tt> object to DB table <tt>tb_employee</tt>, return primary key
	 *
	 * 插入数据
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into tb_employee(id,name,phone,address,eid,ename,did,dname,remark,creator,updater,create_time,update_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param tbEmployee
	 *	@return int
	 */	 

    public int insert(@Param("tbEmployee") TbEmployeeDO tbEmployee);

	/**
	 *  Query DB table <tt>tb_employee</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from tb_employee</tt>
	 *
	 *	@param tbEmployee
	 *	@param start
	 *	@param pageSize
	 *	@return List<TbEmployeeDO>
	 */	 

    public List<TbEmployeeDO> query(@Param("tbEmployee") TbEmployeeDO tbEmployee, @Param("start") long start,
                                    @Param("pageSize") int pageSize);

	/**
	 *  Query DB table <tt>tb_employee</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select COUNT(*) from tb_employee</tt>
	 *
	 *	@param tbEmployee
	 *	@return long
	 */	 

    public long queryCount(@Param("tbEmployee") TbEmployeeDO tbEmployee);

	/**
	 *  Update DB table <tt>tb_employee</tt>.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update tb_employee set name=? where (id = ?)</tt>
	 *
	 *	@param tbEmployee
	 *	@return int
	 */	 

    public int update(@Param("tbEmployee") TbEmployeeDO tbEmployee);

	/**
	 *  Delete records from DB table <tt>tb_employee</tt>.
	 *
	 * 根据主键删除数据
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from tb_employee where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 */	 

    public int delete(@Param("id") Long id);

	/**
	 *  Query DB table <tt>tb_employee</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from tb_employee tbEmployee where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return TbEmployeeDO
	 */	 

    public TbEmployeeDO findById(@Param("id") Long id);

	/**
	 *  Query DB table <tt>tb_employee</tt> for records.
	 *
	 * 
     * <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from tb_employee</tt>
	 *
	 *	@param idList
	 *	@return List<TbEmployeeDO>
	 */	 

    public List<TbEmployeeDO> findByIdIn(@Param("idList") List idList);

}
