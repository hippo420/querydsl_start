package start.querydsl_start.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import start.querydsl_start.jpa.entity.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByType(String type);
}
