package start.querydsl_start.proxy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.proxy.lazy.Invoice;
import start.querydsl_start.proxy.lazy.InvoiceLine;

@Slf4j
@SpringBootTest
public class NullableJoinTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("nullable=false로 내부조인")
    @Transactional
    @Rollback(false)
    void testNotNull_InnerJoin()
    {
        Department dept1 =new Department();
        Department dept2 =new Department();
        dept2.setName("인사팀");
        dept1.setName("개발팀");
        em.persist(dept1);
        em.persist(dept2);

        Employee emp1 = new Employee();
        Employee emp2 = new Employee();
        Employee emp3 = new Employee();
        emp1.setName("개발자");
        emp1.setDepartment(dept1);
        emp2.setName("괴발자");
        emp2.setDepartment(dept1);
        emp3.setName("Non개발자");
        emp3.setDepartment(dept2);
        em.persist(emp1);
        em.persist(emp2);
        em.persist(emp3);

        em.flush();
        em.clear();

        Employee findEmp = em.find(Employee.class, 1L);
        log.info("임직원정보(nullable=false) : {}",findEmp);
    }

    @Test
    @DisplayName("nullable=true로 외부조인")
    @Transactional
    @Rollback(false)
    void testNotNull_OuterJoin()
    {
        Department dept1 =new Department();
        Department dept2 =new Department();
        dept2.setName("인사팀");
        dept1.setName("개발팀");
        em.persist(dept1);
        em.persist(dept2);

        EmployeeV1 emp1 = new EmployeeV1();
        EmployeeV1 emp2 = new EmployeeV1();
        EmployeeV1 emp3 = new EmployeeV1();
        emp1.setName("개발자");
        emp1.setDepartment(dept1);
        emp2.setName("괴발자");
        emp2.setDepartment(dept1);
        em.persist(emp1);
        emp3.setName("Non개발자");
        emp3.setDepartment(dept2);
        em.persist(emp3);

        em.flush();
        em.clear();

        EmployeeV1 findEmp = em.find(EmployeeV1.class, 1L);
        log.info("임직원정보(nullable=true) : {}",findEmp);
    }
}
