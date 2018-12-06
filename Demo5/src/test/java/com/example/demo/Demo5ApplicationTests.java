package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.pojo.Department;
import com.example.demo.service.DepartmentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo5ApplicationTests {
    @Autowired
    DepartmentRepository departmnetRepository;

    
    /**
     * Repository--------@Qurey--更新操作
     */
    @Test
//    @Transactional     // @Transactional和  @Test 一起使用是回滚
//    @Rollback(false)  //取消回滚
    public void testUpdateUserNameById() {
       
        this.departmnetRepository.updateUserNameById("xiaosan", 2);
    }


    @Test
    public void contextLoads() {
//findall
//        final List<Department> all = departmnetRepository.findAll();
//        System.out.println(all.get(0).getName());
//findone        
//        final Department one = departmnetRepository.findOne(1);
//        System.out.println(one.getName());
    	
//insert    	
//        Department department = new Department();
//        department.setName("bbb");
//        department.setAge("30");
//        departmnetRepository.save(department);
    	
//delete
//        departmnetRepository.delete(1);

//query按条件查询
//        final Department bb = departmnetRepository.findByName("bb");
//        System.out.println(bb.getAge());
    	
//分页
//        Sort sort = new Sort(Sort.Direction.ASC, "id");
//        PageRequest pageable = new PageRequest(2,3,sort);
//        final Page<Department> all = departmnetRepository.findAll(pageable);
//
//        System.out.println(all.getSize());
//        System.out.println(all.getNumber());
//        System.out.println(all.getTotalPages());
    	
    	
//update
//        final Department one = departmnetRepository.findOne(1);
//        one.setAge("99");
//        departmnetRepository.saveAndFlush(one);


        Map searchParameters = new HashMap();
        Map map = new HashMap();
        searchParameters.put("size", 1);
        searchParameters.put("pageSize", 2);

        Map map1 = new HashMap();
        Map map2 = new HashMap();
        map2.put("field", "name");
        map2.put("value", "li");
        List list = new ArrayList();
        list.add(map2);
        map1.put("filters", list);
        searchParameters.put("filter", map1);
        int page = 0;
        int pageSize = 10;
        if (searchParameters != null && searchParameters.size() > 0 && searchParameters.get("page") != null) {
            page = Integer.parseInt(searchParameters.get("page").toString()) - 1;
        }
        if (searchParameters != null && searchParameters.size() > 0 && searchParameters.get("pageSize") != null) {
            pageSize = Integer.parseInt(searchParameters.get("pageSize").toString());
        }

        if (pageSize < 1)
            pageSize = 1;
        if (pageSize > 100)
            pageSize = 100;

        Map filter = (Map) searchParameters.get("filter");
        if (filter != null) {
            final List<Map> filters = (List<Map>) filter.get("filters");
            Specification<Department> spec = new Specification<Department>() {
                @Override
                public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> pl = new ArrayList<Predicate>();
                    for (Map f : filters) {
                        String field = f.get("field").toString().trim();
                        String value = f.get("value").toString().trim();
                        if (value != null && value.length() > 0) {
                            if ("name".equalsIgnoreCase(field)) {
                                pl.add(cb.like(root.<String>get(field), value + "%"));
                            } 
                        }

                    }

                    return cb.and(pl.toArray(new Predicate[0]));
                }
            };
            Sort sort = new Sort(Sort.Direction.ASC, "id");
            PageRequest pageable = new PageRequest(2, 3, sort);
            Page<Department> all = departmnetRepository.findAll(spec,pageable);

            System.out.println(all.getTotalElements());
            System.out.println(all.getTotalPages());
        }


    }

}
