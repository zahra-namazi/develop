package ir.rahgozin.branch.monetaryinstrument.repository;

import ir.rahgozin.branch.monetaryinstrument.domain.MonetaryInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonetaryInstrumentRepository extends JpaRepository<MonetaryInstrument, Long> {
}