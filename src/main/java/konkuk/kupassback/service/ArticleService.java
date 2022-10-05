package konkuk.kupassback.service;

import konkuk.kupassback.domain.Article;
import konkuk.kupassback.dto.ArticleDTO;
import konkuk.kupassback.repository.ArticleRepository;
import konkuk.kupassback.specification.ArticleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleDTO> searchArticle(String publisher, String keyword, String category) {
        Map<String, Object> searchKeys = new HashMap<>();
        if (!publisher.isEmpty()) {
            searchKeys.put("publisher", publisher);
        }
        if (!keyword.isEmpty()) {
            searchKeys.put("keyword", keyword);
        }
        if (!category.isEmpty()) {
            searchKeys.put("category", category);
        }

        return articleRepository.findAll(ArticleSpecification.searchArticle(searchKeys))
                .stream()
                .map(ArticleDTO::new)
                .collect(Collectors.toList());
    }
}
