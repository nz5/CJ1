package hawservice.model.Payload;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class LoginRequest
{
    @NotBlank
    private String Email;

    @NotBlank
    private String password;


}
