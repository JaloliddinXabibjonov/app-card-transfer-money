package mypackage.apptransfermoney.payload;

import lombok.Data;

import java.util.List;

@Data
public class RegisterDto {

    private String userName;
    private String password;
    private Integer cardId;

}
