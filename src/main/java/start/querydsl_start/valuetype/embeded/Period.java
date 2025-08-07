package start.querydsl_start.valuetype.embeded;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Embeddable
@Setter @Getter
@ToString(of={"startDate","endDate"})
public class Period implements Cloneable{
    @Temporal(TemporalType.DATE)
    private String startDate;

    @Temporal(TemporalType.DATE)
    private String endDate;

    public Period(){}
    public Period(String startDate, String endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Period period)) return false;
        return Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
