package rbac.server.payload.request;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
