package ir.maktab.model;

import ir.maktab.model.enuramation.UpdateType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Mahsa Alikhani m-58
 */
@Data
@Entity
public class Updates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date date;
    @Enumerated(EnumType.STRING)
    private UpdateType updateType;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Account account;
}
