package konkuk.kupassback.specification;

import konkuk.kupassback.domain.Article;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleSpecification {
    public static Specification<Article> searchArticle(Map<String, Object> searchKeys) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> keyVal : searchKeys.entrySet()) {
                if (keyVal.getKey().equals("keyword")) {
                    Join<Object, Object> join = root.join("keywords", JoinType.INNER);
                    predicates.add(criteriaBuilder.equal(join.get(keyVal.getKey()), keyVal.getValue()));
                }
                else {
                    predicates.add(criteriaBuilder.equal(root.get(keyVal.getKey()), keyVal.getValue()));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
