package start.querydsl_start.realationMapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.realation.onetoone.Rifle;
import start.querydsl_start.realation.onetoone.RifleV1;
import start.querydsl_start.realation.onetoone.Soldier;
import start.querydsl_start.realation.onetoone.SoldierV1;

import java.util.List;
@Slf4j
@SpringBootTest
public class OneToOneTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("일대일 - 단방향매핑 테스트")
    @Transactional
    @Rollback(false)
    void testOneToOne()
    {
        Rifle rifle = new Rifle();
        rifle.setCode("039171");
        rifle.setName("K-1a");
        em.persist(rifle);

        Soldier soldier = new Soldier();
        soldier.setName("군인1");
        soldier.setRifle(rifle);
        em.persist(soldier);

        em.flush();
        em.clear();

        //조회
        Soldier foundSoldier = em.find(Soldier.class, soldier.getId());
        log.info("foundSoldier: {}", foundSoldier);

        Rifle foundRifle = em.find(Rifle.class, soldier.getRifle().getId());
        log.info("foundRifle: {}", foundRifle);

    }


    @Test
    @DisplayName("일대일 - 양방향매핑 테스트")
    @Transactional
    @Rollback(false)
    void testOneToOne_OneToOne()
    {
        RifleV1 rifle = new RifleV1();
        rifle.setCode("039171");
        rifle.setName("K-1a");
        em.persist(rifle);

        SoldierV1 soldier = new SoldierV1();
        soldier.setName("군인1");
        soldier.setRifle(rifle);
        em.persist(soldier);

        em.flush();
        em.clear();

        //조회
        SoldierV1 foundSoldier = em.find(SoldierV1.class, soldier.getId());
        log.info("Soldier 조회: {}", foundSoldier);

        RifleV1 foundRifle = em.find(RifleV1.class, soldier.getRifle().getId());
        log.info("Soldier -> Rifle 조회: {}", foundRifle);

        SoldierV1 foundSoldier1 = em.find(SoldierV1.class, foundRifle.getSoldier().getId());
        log.info("Rifle -> Soldier 조회: {}", foundSoldier1);


    }

}
