package mypackage.apptransfermoney.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String cardNumber;
    private long balance;
    @Column(nullable = false)
    private Date expireDate;
    private boolean active;

}
