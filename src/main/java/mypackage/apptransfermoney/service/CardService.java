package mypackage.apptransfermoney.service;

import mypackage.apptransfermoney.entity.Card;
import mypackage.apptransfermoney.payload.CardDto;
import mypackage.apptransfermoney.payload.template.Result;
import mypackage.apptransfermoney.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    /**
     * YANGI KARTA QO`SHISH UCHUN
     * @param cardDto
     * @return Result
     */
    public Result addCard(CardDto cardDto){
        boolean cardNumber = cardRepository.existsCardByCardNumber(cardDto.getCardNumber());
        if (cardNumber)
            return new Result("Ushbu karta raqami mavjud", false);
        Card card=new Card();
        card.setUsername(cardDto.getUsername());
        card.setCardNumber(cardDto.getCardNumber());
        SimpleDateFormat sdf=new SimpleDateFormat("MMyy");
        try {
            Date date=sdf.parse(cardDto.getExpireDate());
            if (date.getTime()>(new Date()).getTime()) {
                card.setExpireDate(date);
                card.setBalance(cardDto.getBalance());
                card.setActive(true);
            }
            else {
                card.setActive(false);
                return new Result("Kartaning amal qilish muddati tugagan!",false);
            }

        } catch (ParseException e) {
            return new Result("Kartaning amal qilish muddati noto'g'ri kiritildi!!!", false);
        }
        return new Result("Karta muvaffaqiyatli qo`shildi", true);

    }

    /**
     * BARCHA KARTALARNI 100 TADAN SAHIFALAB OLISH UCHUN
     * @return  Page<Card>
     */
    public Page<Card> getAllCards(){
        Pageable pageable= PageRequest.of(0,100);
        return cardRepository.findAll(pageable);
    }

    /**
     * KARTANI UNI ID SI ORQALI OLIB KELISH UCHUN
     * @param id
     * @return Card
     */
    public Card getCardById(Integer id){
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent())
            return optionalCard.get();
        return null;
    }

    /**
     * KARTA NI TAHRIRLASH UCHUN
     * @param id
     * @param cardDto
     * @return Result
     */
    public Result editCard(Integer id, CardDto cardDto){
        boolean allByCardNumberAndIdNot = cardRepository.findAllByCardNumberAndIdNot(cardDto.getCardNumber(), id);
        if (!allByCardNumberAndIdNot)
            return new Result("Bunday karta mavjud",false);
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent())
            return new Result("Bunday karta mavjud emas!",false);
        Card card = optionalCard.get();
        card.setUsername(cardDto.getUsername());
        card.setCardNumber(cardDto.getCardNumber());
        SimpleDateFormat sdf=new SimpleDateFormat("MMyy");
        try {
            Date date=sdf.parse(cardDto.getExpireDate());
            if (date.getTime()>(new Date()).getTime()) {
                card.setExpireDate(date);
                card.setBalance(cardDto.getBalance());
                card.setActive(true);
            }
            else {
                card.setActive(false);
                return new Result("Kartaning amal qilish muddati tugagan!",false);
            }

        } catch (ParseException e) {
            return new Result("Kartaning amal qilish muddati noto'g'ri kiritildi!!!", false);
        }
        return new Result("Karta muvaffaqiyatli tahrirlandi", true);
    }

    /**
     * KARTA NI O`CHIRISH UCHUN
     * @param id
     * @return Result
     */
    public Result deleteCard(Integer id){
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent())
            return new Result("Bunday karta mavjud emas", false);
        cardRepository.deleteById(id);
        return new Result("Karta o`chirildi",true);
    }
}
