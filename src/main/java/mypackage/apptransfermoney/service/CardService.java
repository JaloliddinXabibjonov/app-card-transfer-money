package mypackage.apptransfermoney.service;

import mypackage.apptransfermoney.entity.Card;
import mypackage.apptransfermoney.entity.IncomeAndOutcome;
import mypackage.apptransfermoney.entity.enums.RoleName;
import mypackage.apptransfermoney.payload.CardDto;
import mypackage.apptransfermoney.payload.IncomeOutcomeDto;
import mypackage.apptransfermoney.payload.template.Result;
import mypackage.apptransfermoney.repository.CardRepository;
import mypackage.apptransfermoney.repository.IncomeAndOutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    IncomeAndOutcomeRepository incomeAndOutcomeRepository;
    /**
     * YANGI KARTA QO`SHISH UCHUN
     */
    public Result addCard(CardDto cardDto){
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getAuthorities().toString().equals(RoleName.EMPLOYEE_OF_BANK.toString()))
            return new Result("Siz yangi karta qo`shish huquqiga ega emassiz",false);
        try {
        boolean cardNumber = cardRepository.existsCardByCardNumber(cardDto.getCardNumber());
        if (cardNumber)
            return new Result("Ushbu karta raqami tizimda mavjud", false);
        if (cardDto.getBalance()<0){
            return new Result("Karta mablag'i manfiy bo`lmasligi kerak", false);
        }
        Card card=new Card();
        card.setUsername(cardDto.getUsername());
        card.setCardNumber(cardDto.getCardNumber());
        SimpleDateFormat sdf=new SimpleDateFormat("MMyy");

            Date date=sdf.parse(cardDto.getExpireDate());
            if (date.getTime()>(new Date()).getTime()) {
                card.setExpireDate(date);
                card.setBalance(cardDto.getBalance());
                card.setActive(true);
                cardRepository.save(card);
                return new Result("Karta muvaffaqiyatli qo`shildi", true);
            }
            else {
                return new Result("Kartaning amal qilish muddati tugamagan bo`lishi kerak",false);
            }

        } catch (ParseException e) {
            return new Result("Kartaning amal qilish muddati noto'g'ri kiritildi!!!", false);
        }
//        return new Result("Karta muvaffaqiyatli qo`shildi", true);

    }


    public Result transferMoney(IncomeOutcomeDto dto){
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Card> optional = cardRepository.findByCardNumber(dto.getFromCardNumber());
        Optional<Card> optionalCard = cardRepository.findByCardNumber(dto.getToCardNumber());
        if (optional.isPresent()){
            Card card = optional.get();
            if (!card.getUsername().equals(user.getUsername()))
                return new Result("Karta sizga tegishli emas!", false);
            if (card.getBalance()< (dto.getAmount()+dto.getCommissionAmount()))
                return new Result("Kartada yetarli mablag' mavjud emas", false);
            card.setBalance(card.getBalance()-(dto.getAmount()+dto.getCommissionAmount()));
            Card card1 = optionalCard.get();
            card1.setBalance(card1.getBalance()+dto.getAmount());
            cardRepository.save(card1);
            cardRepository.save(card);
            IncomeAndOutcome incomeAndOutcome=new IncomeAndOutcome();
            incomeAndOutcome.setFromCardNumber(dto.getFromCardNumber());
            incomeAndOutcome.setToCardNumber(dto.getToCardNumber());
            incomeAndOutcome.setAmount(dto.getAmount());
            incomeAndOutcome.setDate(new Date());
            incomeAndOutcome.setCommissionAmount(dto.getCommissionAmount());
            incomeAndOutcomeRepository.save(incomeAndOutcome);
            return new Result("Muvaffaqiyatli bajarildi", true);
        }
        return new Result("Karta topilmadi", false);
    }


}
