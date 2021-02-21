package rbac.server.payload.response;

import lombok.*;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private List<String> permissions;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, List<String> permissions) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.permissions = permissions;
    }
}
