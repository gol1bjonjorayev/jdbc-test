package developer.jorayev.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegDTO {

    //userDto
    private String username;
    private String password;
    //schoolDTo
    private String names;
    private String address;

}
