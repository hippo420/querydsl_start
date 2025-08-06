package start.querydsl_start.composite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.composite.identify.embed.ActorV1;
import start.querydsl_start.composite.identify.embed.ActorV1Id;
import start.querydsl_start.composite.identify.embed.MovieV1;
import start.querydsl_start.composite.identify.idclass.*;
import start.querydsl_start.inherit.join.Movie;

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
        // Flim 엔티티 저장
        Flim flim = new Flim();
        flim.setId(1L);
        flim.setName("The Matrix");
        em.persist(flim);

        // Actor 엔티티 저장
        Actor actor = new Actor();
        actor.setFlim(flim);
        actor.setActorId(100L);
        actor.setName("John Doe");
        em.persist(actor);

        // Person 엔티티 저장
        Person person = new Person();
        person.setActor(actor);
        person.setId(123L);
        person.setName("Keanu Reeves");
        person.setAge(35);
        em.persist(person);

        em.flush();
        em.clear();

        Flim res1 = em.find(Flim.class, 1L);
        log.info("영화 조회 결과 = {}",res1);


        ActorId actorId = new ActorId(res1.getId(), 100L);
        Actor res2 = em.find(Actor.class, actorId);
        log.info("복합키 actorId = {}",actorId);
        log.info("[{}] 영화 배우조회 결과 = {}",res1.getName(),res2);


        // PersonId는 ActorId를 포함하는 복합키입니다.
        PersonId personId = new PersonId(actorId, 123L);
        Person res3 = em.find(Person.class, personId);

        log.info("복합키 personId = {}",personId);
        log.info("조회 결과 = {}",res3);

    }

    @Test
    @DisplayName("엔티티 식별관계 - [Embedded] 테스트")
    @Transactional
    @Rollback(false)
    void testIdentify_Embedded()
    {
        // MovieV1 엔티티 저장
        MovieV1 movie = new MovieV1();
        movie.setId(1L);
        movie.setName("Inception");
        em.persist(movie);

        // ActorV1Id 복합키 생성
        ActorV1Id actorV1Id = new ActorV1Id(100L, 1L); // actorId, movieId

        // ActorV1 엔티티 저장
        ActorV1 actor = new ActorV1();
        actor.setId(actorV1Id);
        actor.setMovieV1(movie); // MovieV1 객체를 set
        actor.setName("Leonardo DiCaprio");
        em.persist(actor);

        em.flush();
        em.clear();

        // 해당 MovieV1 엔티티 조회
        MovieV1 res1 = em.find(MovieV1.class, 1L);
        log.info("영화조회 : {}",res1);

        // ActorV1Id 생성 (actorId, movieId)
        ActorV1Id id = new ActorV1Id(100L, 1L);
        log.info("복합키 ActorV1Id : {}",id);

        // ActorV1 조회
        ActorV1 found = em.find(ActorV1.class, id);
        log.info("[{}]영화 배우 조회 : {}",res1.getName(),found);
    }


}
