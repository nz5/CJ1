package hawservice.model.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse
{
    private Boolean success;
    private String message;


}