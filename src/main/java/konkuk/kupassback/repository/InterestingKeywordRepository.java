package konkuk.kupassback.repository;

import konkuk.kupassback.domain.InterestingKeyword;
import konkuk.kupassback.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface InterestingKeywordRepository extends JpaRepository<InterestingKeyword, Long>,
        JpaSpecificationExecutor<InterestingKeyword> {

    List<InterestingKeyword> findAllByUserEquals(User user);

}
