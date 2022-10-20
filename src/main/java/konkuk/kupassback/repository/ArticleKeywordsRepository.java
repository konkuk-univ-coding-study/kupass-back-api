package konkuk.kupassback.repository;

import konkuk.kupassback.domain.ArticleKeywords;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleKeywordsRepository extends PagingAndSortingRepository<ArticleKeywords, Long>,
        JpaSpecificationExecutor<ArticleKeywords> {
}
