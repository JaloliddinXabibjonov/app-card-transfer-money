package mypackage.apptransfermoney.payload;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CardDto {

    @NotNull(message = "Username bo`sh bo`lmasligi kerak!")
    private String username;

    @NotNull(message = "Karta raqami bo`sh bo`lmasligi kerak!")
    private String cardNumber;

    @NotNull(message = "Karta balansi bo`sh bo`lmasligi kerak!")
    private long balance;

    @NotNull(message = "Kartaning amal qilish muddati bo`sh bo`lmasligi kerak!")
    private String expireDate;

    private boolean active;

}
