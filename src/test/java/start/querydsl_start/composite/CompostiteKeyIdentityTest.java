package start.querydsl_start.composite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@Slf4j
@SpringBootTest
public class CompostiteKeyIdentityTest {

    @PersistenceContext
    EntityManager em;


    @Test
    @DisplayName("엔티티 식별관계 - [IdClass] 테스트")
    @Transactional
    @Rollback(false)
    void testIdentify_IdClass()
    {

    }

    @Test
    @DisplayName("엔티티 식별관계 - [Embedded] 테스트")
    @Transactional
    @Rollback(false)
    void testIdentify_Embedded()
    {

    }


}
