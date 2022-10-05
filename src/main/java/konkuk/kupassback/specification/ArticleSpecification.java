package konkuk.kupassback.specification;

import konkuk.kupassback.domain.Article;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleSpecification {
    public static Specification<Article> searchArticle(Map<String, Object> searchKeys) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> keyVal : searchKeys.entrySet()) {
                predicates.add(criteriaBuilder.equal(root.get(keyVal.getKey()), keyVal.getValue()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
