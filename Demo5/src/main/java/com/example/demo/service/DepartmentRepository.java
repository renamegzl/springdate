package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.Department;




public interface DepartmentRepository extends JpaRepository<Department, Integer> ,JpaSpecificationExecutor {

    
    Department findByName(String name);
    
  //数据更新
    @Query("update Department set name=:name where id=:id")
    @Modifying
   @Transactional
    void updateUserNameById(@Param("name") String name,@Param("id") Integer id);
    
  
 
 
	/**
	 * 
	 * @Description:  查询树根
	 * @param deptName
	 * @param id
	 * @return List<Dept>  
	 * @throws
	 * @author wanghw
	 * @date 2017年4月25日
	 */
//	List<Department> findByNameOrderByDeptIndexAsc(@Param("name") int i);
	
	@Query("select d from Department d where d.id=:id")
	Department findById(@Param("id") Integer id);	
    

}
