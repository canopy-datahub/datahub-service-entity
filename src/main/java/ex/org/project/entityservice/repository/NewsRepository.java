package ex.org.project.entityservice.repository;

import ex.org.project.entityservice.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

	List<News> findByType_NameOrderByStartDateDesc(String typeName);

	List<News> findByExpirationDateGreaterThanEqualAndType_NameOrderByStartDateDesc(LocalDateTime localDateTime, String typeName);

	Optional<News> findBySlug(String slug);
}
