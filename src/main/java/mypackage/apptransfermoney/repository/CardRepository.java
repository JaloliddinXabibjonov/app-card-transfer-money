package mypackage.apptransfermoney.repository;

import mypackage.apptransfermoney.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {
        boolean existsCardByCardNumber(String cardNumber);
        boolean existsCardByCardNumberAndUsername(String cardNumber, String username);

        boolean findAllByCardNumberAndIdNot(String cardNumber, Integer id);

        Optional<Card> findByCardNumber(String cardNumber);
}
