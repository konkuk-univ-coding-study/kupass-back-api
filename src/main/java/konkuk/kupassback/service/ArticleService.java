package konkuk.kupassback.service;

import konkuk.kupassback.domain.Article;
import konkuk.kupassback.domain.ArticleKeywords;
import konkuk.kupassback.dto.ArticleDTO;
import konkuk.kupassback.repository.ArticleKeywordsRepository;
import konkuk.kupassback.repository.ArticleRepository;
import konkuk.kupassback.specification.ArticleKeywordSpecification;
import konkuk.kupassback.specification.ArticleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleKeywordsRepository articleKeywordsRepository;
    private final ArticleRepository articleRepository;

    public List<ArticleDTO> searchArticle(String publisher, String keyword, String category, Pageable page) {
        Map<String, String> searchKeys = new HashMap<>();
        if (!publisher.isEmpty()) {
            searchKeys.put("publisher", publisher);
        }
        if (!keyword.isEmpty()) {
            searchKeys.put("keyword", keyword);
        }
        if (!category.isEmpty()) {
            searchKeys.put("category", category);
        }

        if (!keyword.isEmpty()) {
            return articleKeywordsRepository.findAll(ArticleKeywordSpecification.searchArticle(searchKeys), page)
                    .stream()
                    .map(ArticleKeywords::getArticle)
                    .map(ArticleDTO::new)
                    .collect(Collectors.toList());
        }
        return articleRepository.findAll(ArticleSpecification.searchArticle(searchKeys), page)
                .stream()
                .map(ArticleDTO::new)
                .collect(Collectors.toList());
    }
}
