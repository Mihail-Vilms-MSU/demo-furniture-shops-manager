package com.mvilms.demo_furniture_shops_manager.data;

import com.mvilms.demo_furniture_shops_manager.model.Employee;
import com.mvilms.demo_furniture_shops_manager.model.Shop;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class EmployeeRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Shop shop1;
    private Shop shop2;

    @Before
    public void setupTestData() {
        shop1 = entityManager.persist(new Shop("Shop1", "City1", "State1", "Address11", "111 111 1111"));
        shop2 = entityManager.persist(new Shop("Shop2", "City2", "State2", "Address12", "222 222 2222"));

        entityManager.persist(new Employee("first11", "last11", "role1", "phone11", "email1", shop1));
        entityManager.persist(new Employee("first12", "last12", "role2", "phone12", "email2", shop1));
        entityManager.persist( new Employee("first13", "last13", "role3", "phone13", "email3", shop1));

        entityManager.persist(new Employee("first21", "last21", "role1", "phone21", "email21", shop2));
        entityManager.persist(new Employee("first22", "last22", "role2", "phone22", "email22", shop2));
        entityManager.persist( new Employee("first23", "last23", "role3", "phone23", "email23", shop2));

        entityManager.flush();
    }

    @Test
    public void getAll_dbHas6Records_returns2Records(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        Assert.assertEquals(6, employeePage.getContent().size());
    }

    @Test
    public void findByRole_dbHas6Records_returns2Records(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Employee> employeePage = employeeRepository.findByRole("role1", pageable);
        Assert.assertEquals(2, employeePage.getContent().size());
    }

    @Test
    public void findByName_dbHas6Records_returns3Records(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Employee> employeePage = employeeRepository.findByName("last1", pageable);
        Assert.assertEquals(3, employeePage.getContent().size());
    }

    @Test
    public void liveSearch_firstNameAsParam_returns3Records(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Employee> employeePage = employeeRepository.liveSearch("first2", pageable);
        Assert.assertEquals(3, employeePage.getContent().size());
    }

    @Test
    public void liveSearch_roleAsParam_returns3Records(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Employee> employeePage = employeeRepository.liveSearch("role2", pageable);
        Assert.assertEquals(2, employeePage.getContent().size());
    }

    @Test
    public void advancedSearch_roleAsParam_returns1Records(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Employee> employeePage = employeeRepository.advancedSearch("", "", shop2.getId(), "role2", pageable);
        Assert.assertEquals(1, employeePage.getContent().size());
    }
}
