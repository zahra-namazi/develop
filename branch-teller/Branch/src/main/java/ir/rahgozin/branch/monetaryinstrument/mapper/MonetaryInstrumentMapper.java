package ir.rahgozin.branch.monetaryinstrument.mapper;

import ir.rahgozin.branch.monetaryinstrument.domain.MonetaryInstrument;
import ir.rahgozin.branch.monetaryinstrument.dto.MonetaryInstrumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MonetaryInstrumentMapper {
    MonetaryInstrumentMapper INSTANCE = Mappers.getMapper(MonetaryInstrumentMapper.class);

    MonetaryInstrument toMonetaryInstrument(MonetaryInstrumentDto monetaryInstrumentDto);
}