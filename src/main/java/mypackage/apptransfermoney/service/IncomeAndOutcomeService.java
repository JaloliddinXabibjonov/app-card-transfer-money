package mypackage.apptransfermoney.service;

import mypackage.apptransfermoney.entity.IncomeAndOutcome;
import mypackage.apptransfermoney.payload.template.Result;
import mypackage.apptransfermoney.repository.CardRepository;
import mypackage.apptransfermoney.repository.IncomeAndOutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeAndOutcomeService {

    @Autowired
    IncomeAndOutcomeRepository incomeAndOutcomeRepository;

    @Autowired
    CardRepository cardRepository;

    public Result getIncomeAndOutcomeHistory(String cardNumber) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist = cardRepository.existsCardByCardNumberAndUsername(cardNumber, user.getUsername());
        if (exist) {
            return new Result("Muvaffaqiyatli bajarildi", true, incomeAndOutcomeRepository.findAllByFromCardNumberOrToCardNumber(cardNumber, cardNumber));
        }
        return new Result("Ushbu karta sizga tegishli emas!",false);

    }
}
