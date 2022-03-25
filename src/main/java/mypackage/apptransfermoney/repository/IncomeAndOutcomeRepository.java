package mypackage.apptransfermoney.repository;

import mypackage.apptransfermoney.entity.IncomeAndOutcome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeAndOutcomeRepository extends JpaRepository<IncomeAndOutcome, Integer> {


    List<IncomeAndOutcome> findAllByFromCardNumberOrToCardNumber(String fromCardNumber, String toCardNumber);

}
