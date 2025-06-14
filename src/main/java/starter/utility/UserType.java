package starter.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    STANDARD_USER("standard_user","secret_sauce"),
    LOCKED_OUT_USER("locked_out_user","secret_sauce");
    private final String username;
    private final String password;


}
