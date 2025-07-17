package start.querydsl_start;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuerydslStartApplicationTests {

    @PersistenceContext
    EntityManager em;

    @Test
    void contextLoads() {
    }

}
