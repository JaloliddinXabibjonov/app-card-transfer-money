package mypackage.apptransfermoney.controller;

import mypackage.apptransfermoney.entity.IncomeAndOutcome;
import mypackage.apptransfermoney.payload.template.Result;
import mypackage.apptransfermoney.service.IncomeAndOutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/incomeAndOutcome")
public class IncomeAndOutcomeController {
    @Autowired
    IncomeAndOutcomeService incomeAndOutcomeService;

    @GetMapping("/getHistory/{cardNumber}")
    public HttpEntity<?> getIncomeAndOutcomeHistory(@PathVariable String cardNumber){
        Result result = incomeAndOutcomeService.getIncomeAndOutcomeHistory(cardNumber);
        return ResponseEntity.status(result.isSuccess()?200:409).body(result);
    }

}
