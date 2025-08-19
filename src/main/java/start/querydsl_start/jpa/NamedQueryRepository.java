package start.querydsl_start.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import start.querydsl_start.jpa.entity.Player;
import start.querydsl_start.jpa.named.Named;

import java.util.List;

public interface NamedQueryRepository extends JpaRepository<Named, Long> {
    List<Named> findByName(@Param("name") String name);

    @Query("select m from Named m where m.age > :age")
    List<Named> lstLimitAgeNameParam(@Param("age")int age);

    @Query("select m from Named m where m.age > ?1")
    List<Named> lstLimitAgePriorityParam(@Param("age") int age);

    @Modifying(clearAutomatically = true)
    @Query("update Named m set m.name = '초기화됨' where m.age < :age")
    int updateNameInit(@Param("age") int age);

    @Modifying()
    @Query("update Named m set m.name = '초기화안됨' where m.age < :age")
    int updateNameNoInit(@Param("age") int age);
}
