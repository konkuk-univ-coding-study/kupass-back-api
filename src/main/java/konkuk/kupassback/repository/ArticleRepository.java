package konkuk.kupassback.repository;

import konkuk.kupassback.domain.Article;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long>, JpaSpecificationExecutor<Article> {

}
