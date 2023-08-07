package developer.jorayev.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Integer id;
    private String username;
    private String password;

}
