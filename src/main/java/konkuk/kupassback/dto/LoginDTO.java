package konkuk.kupassback.dto;

import javax.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull
    private String nickname;

    @NotNull
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
