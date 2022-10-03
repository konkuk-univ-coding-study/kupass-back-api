package konkuk.kupassback.dto;

import konkuk.kupassback.domain.Article;
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

    public ArticleDTO(Article article) {
        this.createDate = article.getCreateDate();
        this.publisher = article.getPublisher();
        this.category = article.getCategory();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.summary = article.getSummary();
        this.keywords = article.getKeywords()
                .stream()
                .map(Keyword::getKeyword).collect(Collectors.toList());
    }

    private LocalDateTime createDate;

    private String publisher;

    private String category;

    private String title;

    private String content;

    private String summary;

    private List<String> keywords = new ArrayList<>();

}
