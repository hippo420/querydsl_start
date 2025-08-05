package start.querydsl_start.entity.advanceMapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.entity.advancedMapping.inherit.join.Movie;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@SpringBootTest
public class EntityCompostiteKeyTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("엔티티 비식별관계 - [IdClass] 테스트")
    @Transactional
    @Rollback(false)
    void testNonIdentify_IdClass()
    {
//        User user = new User();
//        user.setId1(1L);
//        user.setId2(2L);
//        user.setName("사용자1");
//        em.persist(user);
//
//        // 2. 사용자 주소(UserAddress) 생성
//        UserAddress address = new UserAddress();
//        address.setUser(user);
//        em.persist(address);
//
//
//        em.flush();
//        em.clear();
//
//        //조회
//        UserAddress foundAddress = em.find(UserAddress.class, address.getId());
//        System.out.println("조회된 주소: " + foundAddress);
//        System.out.println("주소에 연결된 사용자: " + foundAddress.getUser());
    }

    @Test
    @DisplayName("엔티티 비식별관계 - [Embedded] 테스트")
    @Transactional
    @Rollback(false)
    void testNonIdentify_Embedded()
    {

    }

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
