package start.querydsl_start.valuetype.collection;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Embeddable
@Setter
@Getter
@ToString(of={"sender","receiver"})
public class Call implements Cloneable{

    private String sender;

    private String receiver;

    public Call(){}

    //생성자로만 초기화시키면 됨.
    public Call(String sender, String receiver){
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Call call = (Call) o;
        return Objects.equals(sender, call.sender) && Objects.equals(receiver, call.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver);
    }
}
