package konkuk.kupassback.repository;

import konkuk.kupassback.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Optional<Keyword> findKeywordByKeyword(String keyword);
}
