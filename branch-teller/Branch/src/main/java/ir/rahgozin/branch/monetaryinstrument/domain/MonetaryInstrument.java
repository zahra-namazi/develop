package ir.rahgozin.branch.monetaryinstrument.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BT_MONETARY_INSTRUMENT")
@RequiredArgsConstructor
@Getter
@Setter
public class MonetaryInstrument /*extends BaseAuditEntity*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MONETARY_INSTRUMENT_ID")
    private Long id;
    @Column(name = "CODE", nullable = false)
    private String code;
    @Column(name = "CURRENCY", nullable = false)
    private String currency;
    @Column(name = "IS_CASH", nullable = false)
    private Boolean isCash;
    @Column(name = "IS_DELETED", columnDefinition = "NUMBER(1,0) DEFAULT 1")
    private Boolean isDeleted;
    @Column(name = "VERSION_NUM")
    private Integer versionNum;
    @Column(name = "TRANSIT_ACCOUNT")
    private String transitAccount;
}
