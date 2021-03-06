package mypackage.apptransfermoney.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class IncomeAndOutcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fromCardNumber;

    @Column(nullable = false)
    private String toCardNumber;

    @Column(nullable = false)
    private long amount;

    private Date date;

    private long commissionAmount;
}
