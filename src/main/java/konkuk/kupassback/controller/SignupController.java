package konkuk.kupassback.controller;

import konkuk.kupassback.domain.User;
import konkuk.kupassback.dto.LoginDTO;
import konkuk.kupassback.dto.UserResponseDTO;
import konkuk.kupassback.exceptions.NicknameConflictException;
import konkuk.kupassback.exceptions.NicknameFormatException;
import konkuk.kupassback.exceptions.PasswordFormatException;
import konkuk.kupassback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;

    @GetMapping("/validate/duplicate")
    public ResponseEntity<UserResponseDTO> validateDuplicate(@RequestParam("nickname") String nickname) {
        try {
            userService.checkValidNickname(nickname);
            userService.checkDuplicate(nickname);
            return new ResponseEntity<>(
                    new UserResponseDTO("success", "available user id"), HttpStatus.OK);
        } catch (NicknameFormatException e) {
            return new ResponseEntity<>(
                    new UserResponseDTO("fail", "not supported format"), HttpStatus.BAD_REQUEST);
        } catch (NicknameConflictException e) {
            return new ResponseEntity<>(
                    new UserResponseDTO("fail", "conflict user id"), HttpStatus.CONFLICT);
        }
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> signup(@RequestBody LoginDTO userDTO) {
        User newUser = new User(userDTO.getNickname(), userDTO.getPassword());
        try {
            userService.checkValidNickname(userDTO.getNickname());
            userService.checkValidPassword(userDTO.getPassword());
            userService.signup(newUser);
            return new ResponseEntity<>(
                    new UserResponseDTO("success", "signup ok"), HttpStatus.OK);
        } catch (NicknameFormatException e) {
            return new ResponseEntity<>(
                    new UserResponseDTO("fail", "not supported id format"), HttpStatus.BAD_REQUEST);
        } catch (PasswordFormatException e) {
            return new ResponseEntity<>(
                    new UserResponseDTO("fail", "not supported pw format"), HttpStatus.BAD_REQUEST);
        } catch (NicknameConflictException e) {
            return new ResponseEntity<>(
                    new UserResponseDTO("fail", "conflict user id"), HttpStatus.CONFLICT);
        }
    }
}
