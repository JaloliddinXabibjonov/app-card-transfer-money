package mypackage.apptransfermoney.repository;

import mypackage.apptransfermoney.entity.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {
}
