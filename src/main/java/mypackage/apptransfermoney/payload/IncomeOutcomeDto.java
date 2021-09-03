package mypackage.apptransfermoney.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IncomeOutcomeDto {

    @NotNull(message = "Mablag' yuboruvchi karta raqami bo`sh bo`lmasligi kerak!")
    private int fromCardId;
    @NotNull(message = "Mablag' qabul qiluvchi karta raqami bo`sh bo`lmasligi kerak!")
    private int toCardId;
    @NotNull(message = "Yuborilayotgan mablag' bo`sh bo`lmasligi kerak!")
    private long amount;
}
