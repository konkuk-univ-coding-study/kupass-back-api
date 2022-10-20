package konkuk.kupassback.service;

import konkuk.kupassback.domain.Article;
import konkuk.kupassback.domain.ArticleKeywords;
import konkuk.kupassback.dto.ArticleDTO;
import konkuk.kupassback.repository.ArticleKeywordsRepository;
import konkuk.kupassback.repository.ArticleRepository;
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
        /*Stream<Article> articleStream;
        if (!keyword.isEmpty()) {
            articleStream = articleKeywordsRepository.findAll(ArticleSpecification.searchKeyword(keyword), page)
                    .stream()
                    .map(ArticleKeywords::getArticle);
        } else {
            articleStream = articleRepository.findAll(page)
                    .stream();
        }
        if (!publisher.isEmpty()) {
            articleStream = articleStream
                    .filter(article -> article.getPublisher().equals(publisher));
        }
        if (!category.isEmpty()) {
            articleStream = articleStream
                    .filter(article -> article.getCategory().equals(category));
        }*/

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

        return articleKeywordsRepository.findAll(ArticleSpecification.searchArticle(searchKeys), page)
                .stream()
                .map(ArticleKeywords::getArticle)
                .map(ArticleDTO::new)
                .collect(Collectors.toList());
    }
}
