package konkuk.kupassback.specification;

import konkuk.kupassback.domain.ArticleKeywords;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleKeywordSpecification {

    public static Specification<ArticleKeywords> searchArticle(Map<String, String> searchKeys) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> keyVal : searchKeys.entrySet()) {
                Join<Object, Object> joinEntity;
                if (keyVal.getKey().equals("keyword")) {
                    joinEntity = root.join("keyword", JoinType.INNER);
                }
                else {
                    joinEntity = root.join("article", JoinType.INNER);
                }
                predicates.add(criteriaBuilder.equal(joinEntity.get(keyVal.getKey()), keyVal.getValue()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
