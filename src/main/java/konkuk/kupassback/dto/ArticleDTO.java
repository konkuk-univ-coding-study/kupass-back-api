package konkuk.kupassback.dto;

import konkuk.kupassback.domain.Article;
import konkuk.kupassback.domain.ArticleKeywords;
import konkuk.kupassback.domain.Keyword;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ArticleDTO {

    private Long articleId;
    private LocalDateTime createDate;
    private String publisher;
    private String category;
    private String title;
    private String content;
    private String summary;
    private List<String> keywords = new ArrayList<>();

    public ArticleDTO(Article article) {
        this.articleId = article.getId();
        this.createDate = article.getCreateDate();
        this.publisher = article.getPublisher();
        this.category = article.getCategory();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.summary = article.getSummary();
        this.keywords = article.getKeywords()
                .stream()
                .map(articleKeywords -> articleKeywords.getKeyword().getKeyword())
                .collect(Collectors.toList());
    }

}
