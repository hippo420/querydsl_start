package start.querydsl_start.proxy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.proxy.eager.Driver;
import start.querydsl_start.proxy.eager.License;
import start.querydsl_start.proxy.lazy.Invoice;
import start.querydsl_start.proxy.lazy.InvoiceLine;
import start.querydsl_start.proxy.orphan.Container;
import start.querydsl_start.proxy.orphan.Docker;

import java.util.List;

@Slf4j
@SpringBootTest
public class OrphanRemoveTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("JPA 고아객체 삭제 테스트")
    @Transactional
    @Rollback(false)
    void testProxy()
    {
        Container c1 = new Container();
        Container c2 = new Container();
        c1.setName("mysql");
        c2.setName("postgreSql");
        em.persist(c1);
        em.persist(c2);

        Docker d1 = new Docker();
        d1.setName("DOCKER_DEV_SERVER");
        d1.setContainers(List.of(c1,c2));
        em.persist(d1);

        em.flush();
        em.clear();

        Docker findDocker = em.find(Docker.class, 1L);
        log.info("도커 : {}",findDocker);

        log.info("{} 도커 컨테이너 정보",findDocker.getName());
        findDocker.getContainers().forEach(f -> log.info("컨테이너 : {}",f));


        log.info("Docker 삭제 컨테이너 연관관계 삭제");
        findDocker.getContainers().clear();
        em.flush();

        Docker findDocker1 = em.find(Docker.class, 1L);
        log.info("도커 : {}",findDocker1);






    }


}
