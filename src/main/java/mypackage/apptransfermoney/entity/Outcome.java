package mypackage.apptransfermoney.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int fromCardId;

    @Column(nullable = false)
    private int toCardId;

    @Column(nullable = false)
    private long amount;

    private Date date;

    private long comissionAmount;
}
