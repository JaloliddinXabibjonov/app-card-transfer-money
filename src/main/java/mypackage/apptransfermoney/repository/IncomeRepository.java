package mypackage.apptransfermoney.repository;

import mypackage.apptransfermoney.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
}
