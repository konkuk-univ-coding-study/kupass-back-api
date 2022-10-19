package konkuk.kupassback.specification;

import konkuk.kupassback.domain.ArticleKeywords;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleSpecification {
    public static Specification<ArticleKeywords> searchKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> articleKeywords = root.join("keyword", JoinType.INNER);
            return criteriaBuilder.equal(articleKeywords.get("keyword"), keyword);
        };
    }
    public static Specification<ArticleKeywords> searchArticle(Map<String, Object> searchKeys) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> keyVal : searchKeys.entrySet()) {
                if (keyVal.getKey().equals("keyword")) {
                    Join<Object, Object> articleKeywords = root.join("keyword", JoinType.INNER);
                    predicates.add(criteriaBuilder.equal(articleKeywords.get(keyVal.getKey()), keyVal.getValue()));
//                    predicates.add(criteriaBuilder.equal(root.get("keyword"), keyVal.getValue()));
                }
                else {
                    predicates.add(criteriaBuilder.equal(root.get(keyVal.getKey()), keyVal.getValue()));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
