package start.querydsl_start.valuetype.collection;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@ToString(of={"id","call","numbers","infos"})
public class Collect {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    public Call call;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="CALL",
    joinColumns = @JoinColumn(name="COLLECT_ID"))
    @Column(name="COLLECT_ID")
    private List<Call> numbers =new ArrayList<Call>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="CALL",
            joinColumns = @JoinColumn(name="COLLECT_ID"))
    @Column(name="CALL_CONTENT")
    private List<String> infos =new ArrayList<String>();

}
