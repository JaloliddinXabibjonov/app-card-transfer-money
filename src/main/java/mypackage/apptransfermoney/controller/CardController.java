package mypackage.apptransfermoney.controller;

import mypackage.apptransfermoney.payload.CardDto;
import mypackage.apptransfermoney.payload.IncomeOutcomeDto;
import mypackage.apptransfermoney.payload.template.Result;
import mypackage.apptransfermoney.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    CardService cardService;

    /**
     * ADD CARD
     */
    @PostMapping("/add")
    public HttpEntity<Result> addCard(@RequestBody CardDto cardDto){
        Result result = cardService.addCard(cardDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }



    /** Pul jo'natish */
    @PostMapping("/transferMoney")
    public HttpEntity<?> transferMoney(@RequestBody IncomeOutcomeDto incomeOutcomeDto){
        Result result = cardService.transferMoney(incomeOutcomeDto);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);

    }
}
