package konkuk.kupassback.controller;

import konkuk.kupassback.domain.User;
import konkuk.kupassback.dto.ArticleResponseDTO;
import konkuk.kupassback.dto.KeywordDTO;
import konkuk.kupassback.dto.KeywordResponseDTO;
import konkuk.kupassback.dto.UserResponseDTO;
import konkuk.kupassback.exceptions.KeywordExistsException;
import konkuk.kupassback.jwt.TokenProvider;
import konkuk.kupassback.service.KeywordService;
import konkuk.kupassback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static konkuk.kupassback.jwt.JWTFilter.AUTHORIZATION_HEADER;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final KeywordService keywordService;

    private final TokenProvider tokenProvider;

    @GetMapping("/{nickname}/keywords")
    public ResponseEntity<KeywordResponseDTO> getKeywords(@PathVariable String nickname) {
        User user = userService.findUser(nickname);
        List<String> keywords = keywordService.getKeywords(user);

        return new ResponseEntity<>(new KeywordResponseDTO("success", "ok", keywords), HttpStatus.OK);
    }

    // todo 키워드 저장
    @PostMapping("/{nickname}/keywords")
    public ResponseEntity<UserResponseDTO> saveKeyword(@PathVariable String nickname,
                                                       @RequestBody KeywordDTO keywordDTO,
                                                       HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        String token = header.substring(7);
        String subject = tokenProvider.getSubject(token);

        if (!subject.equals(nickname)) {
            return new ResponseEntity<>(
                    new UserResponseDTO("fail", "user doesn't match"), HttpStatus.OK);
        }

        User user = userService.findUser(nickname);
        try {
            keywordService.saveKeyword(user, keywordDTO.getKeyword());
        } catch (KeywordExistsException e) {
            return new ResponseEntity<>(
                    new UserResponseDTO("fail", "keyword already exists"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new UserResponseDTO("success", "ok"), HttpStatus.OK);
    }
}
