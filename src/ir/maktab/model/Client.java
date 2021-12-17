package ir.maktab.model;

import ir.maktab.model.enuramation.ClientHistoryType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String family;
    private long nationalNumber;
    @Enumerated(EnumType.STRING)
    private ClientHistoryType clientHistoryType;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date clientCreationDate;
    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @ToString.Exclude
    private List<Account> accounts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @ToString.Exclude
    private List<Updates> updates = new ArrayList<>();

}
