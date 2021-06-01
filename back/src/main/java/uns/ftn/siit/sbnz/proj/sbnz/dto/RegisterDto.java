package uns.ftn.siit.sbnz.proj.sbnz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

}
