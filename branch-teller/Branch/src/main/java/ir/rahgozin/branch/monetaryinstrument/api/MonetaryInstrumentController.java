package ir.rahgozin.branch.monetaryinstrument.api;

import ir.rahgozin.branch.monetaryinstrument.dto.MonetaryInstrumentDto;
import ir.rahgozin.branch.monetaryinstrument.service.MonetaryInstrumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/monetary-instrument")
@RequiredArgsConstructor
public class MonetaryInstrumentController {
    private final MonetaryInstrumentService monetaryInstrumentService;

    @GetMapping
    public ResponseEntity<String> getAllMonetaryInstrument() {
        //TODO
        return ResponseEntity.ok("ok");
    }

    @PostMapping(value = "/create-monetary-instrument")
    public void createMonetaryInstrument(@Valid @RequestBody MonetaryInstrumentDto monetaryInstrumentDto) {
        monetaryInstrumentService.createMonetaryInstrument(monetaryInstrumentDto);
    }
}

