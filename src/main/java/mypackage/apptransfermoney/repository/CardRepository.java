package mypackage.apptransfermoney.repository;

import mypackage.apptransfermoney.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card, Integer> {
        boolean existsCardByCardNumber(String cardNumber);

        boolean findAllByCardNumberAndIdNot(String cardNumber, Integer id);
}
