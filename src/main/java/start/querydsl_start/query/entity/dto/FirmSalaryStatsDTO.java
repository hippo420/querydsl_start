package start.querydsl_start.query.entity.dto;

import lombok.ToString;

import java.math.BigDecimal;

@ToString(of={"firmName","traderCount","totalSalary",
                "averageSalary","averageSalary","maxSalary","minSalary"})
public class FirmSalaryStatsDTO {
    private String firmName;
    private Long traderCount;
    private BigDecimal totalSalary;
    private Double averageSalary;
    private BigDecimal maxSalary;
    private BigDecimal minSalary;

    // JPQL의 new 구문은 기본 생성자가 아닌 "매개변수 생성자"를 반드시 사용해야 함
    public FirmSalaryStatsDTO(String firmName,
                           Long traderCount,
                           BigDecimal totalSalary,
                           Double averageSalary,
                           BigDecimal maxSalary,
                           BigDecimal minSalary) {
        this.firmName = firmName;
        this.traderCount = traderCount;
        this.totalSalary = totalSalary;
        this.averageSalary = averageSalary;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
    }
}
