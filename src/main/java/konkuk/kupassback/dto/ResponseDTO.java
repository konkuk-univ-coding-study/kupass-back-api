package konkuk.kupassback.dto;

import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseDTO<T> {

    private String success;
    private String message;

    private List<T> articles;

}
