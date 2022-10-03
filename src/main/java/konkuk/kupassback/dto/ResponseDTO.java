package konkuk.kupassback.dto;

import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseDTO {

    private String success;
    private String message;

    private List<ArticleDTO> articles;

}
