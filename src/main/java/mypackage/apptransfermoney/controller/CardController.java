package mypackage.apptransfermoney.controller;

import mypackage.apptransfermoney.payload.CardDto;
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
     * @param cardDto
     * @return HttpEntity<?>
     */
    @PostMapping
    public HttpEntity<?> add(@RequestBody CardDto cardDto){
        Result result = cardService.addCard(cardDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }

    /**
     * GET ALL CARDS
     * @return HttpEntity<?>
     */
    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(cardService.getAllCards());
    }

    /**
     * GET CARD BY ID
     * @param id
     * @return HttpEntity<?>
     */
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    /**
     * EDIT CARD
     * @param id
     * @param cardDto
     * @return HttpEntity<?>
     */
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody CardDto cardDto){
        Result result = cardService.editCard(id, cardDto);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }

    /**
     * DELETE CARD BY ID
     * @param id
     * @return HttpEntity<?>
     */
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        Result result = cardService.deleteCard(id);
        return ResponseEntity.status(result.isSuccess()?200:404).body(result);
    }
}
