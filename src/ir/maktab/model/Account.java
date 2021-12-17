package ir.maktab.model;

import ir.maktab.model.enuramation.AccountType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 6)
    private int accountNumber;
    @Column(length = 16)
    private long cardNumber;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date openingDate;
    @Temporal(TemporalType.DATE)
    private Date expireDate;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private double fund;
    @Column(length = 4)
    private int ccv2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Transaction> transactions = new ArrayList<>();
    @ManyToOne
    private Client client;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Updates> updates = new ArrayList<>();
}
