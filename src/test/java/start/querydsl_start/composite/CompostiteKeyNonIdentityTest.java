package start.querydsl_start.composite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.composite.noidentify.embed.ChildV2;
import start.querydsl_start.composite.noidentify.embed.ParentV2;
import start.querydsl_start.composite.noidentify.embed.ParentV2Id;
import start.querydsl_start.composite.noidentify.idclass.ChildV1;
import start.querydsl_start.composite.noidentify.idclass.ParentV1;
import start.querydsl_start.composite.noidentify.idclass.ParentV1Id;

@Slf4j
@SpringBootTest
public class CompostiteKeyNonIdentityTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("엔티티 비식별관계 - [IdClass] 테스트")
    @Transactional
    @Rollback(false)
    void testNonIdentify_IdClass()
    {
        ParentV1 p = new ParentV1();
        p.setId1(1L);
        p.setId2(2L);
        p.setName("부모");
        em.persist(p);

        ChildV1 c = new ChildV1();
        c.setId(1L);
        c.setName("자식");
        c.setParentV1(p);
        em.persist(c);

        em.flush();
        em.clear();

        ChildV1 res1 = em.find(ChildV1.class,1L);
        log.info("Result = {}",res1);

        ParentV1Id pId =  new ParentV1Id(res1.getParentV1().getId1(),res1.getParentV1().getId2());
        ParentV1 res = em.find(ParentV1.class,pId);
        log.info("Result = {}",res);

    }

    @Test
    @DisplayName("엔티티 비식별관계 - [Embedded] 테스트")
    @Transactional
    @Rollback(false)
    void testNonIdentify_Embedded()
    {
        ParentV2Id id1 = new ParentV2Id(1L,2L);
        ParentV2 parent = new ParentV2();
        parent.setId(id1);
        parent.setName("부모111");
        em.persist(parent);

        ChildV2 c = new ChildV2();
        c.setId(1L);
        c.setParentV2(parent);
        c.setName("나는 자식이다.");
        em.persist(c);

        em.flush();
        em.clear();



        ChildV2 res1 = em.find(ChildV2.class,1L);
        log.info("Result = {}",res1);

        ParentV2Id pId =  res1.getParentV2().getId();
        ParentV2 res = em.find(ParentV2.class,pId);
        log.info("Result = {}",res);
    }



}
