package start.querydsl_start.entity.proxy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.proxy.entity.Department;
import start.querydsl_start.proxy.entity.Employee;

import java.util.List;
@Slf4j
@SpringBootTest
public class ProxyTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("JPA 프록시 테스트")
    @Transactional
    @Rollback(false)
    void testProxy()
    {

        Department dept1 =new Department();
        dept1.setName("개발팀");
        em.persist(dept1);
        Department dept2 =new Department();
        dept2.setName("인사팀");
        em.persist(dept2);

        Employee emp1 = new Employee();
        emp1.setName("개발자");
        emp1.setDepartment(dept1);

        Employee emp2 = new Employee();
        emp2.setName("괴발자");
        emp1.setDepartment(dept1);
        em.persist(emp1);

        Employee emp3 = new Employee();
        emp3.setName("Non개발자");
        emp1.setDepartment(dept2);
        em.persist(emp3);

        em.flush();
        em.clear();

        Employee findEmp = em.find(Employee.class, 1L);
        log.info("임직원정보 : {}",findEmp);



        log.info("부서정보 : {}",findEmp.getDepartment());

    }


    @Test
    @DisplayName("다대일 - 양방향매핑 테스트")
    @Transactional
    @Rollback(false)
    void testManyToOne_OneToMany()
    {


        em.persist(null);

        em.flush();
        em.clear();



        //조회
        List<Object> res1 = em.createQuery("select m from Stock m", Object.class)
                .getResultList();


        res1.stream().forEach(r -> System.out.println(r));
    }

}
