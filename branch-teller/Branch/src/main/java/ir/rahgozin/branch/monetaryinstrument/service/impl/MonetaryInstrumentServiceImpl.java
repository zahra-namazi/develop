package ir.rahgozin.branch.monetaryinstrument.service.impl;

import ir.rahgozin.branch.monetaryinstrument.dto.MonetaryInstrumentDto;
import ir.rahgozin.branch.monetaryinstrument.mapper.MonetaryInstrumentMapper;
import ir.rahgozin.branch.monetaryinstrument.repository.MonetaryInstrumentRepository;
import ir.rahgozin.branch.monetaryinstrument.service.MonetaryInstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonetaryInstrumentServiceImpl implements MonetaryInstrumentService {
    private final MonetaryInstrumentRepository monetaryInstrumentRepository;

    public void createMonetaryInstrument(MonetaryInstrumentDto monetaryInstrumentDto) {
        this.monetaryInstrumentRepository.save(MonetaryInstrumentMapper.INSTANCE.toMonetaryInstrument(monetaryInstrumentDto));
    }
}