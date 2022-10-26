package net.obs.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import net.obs.enums.StateEnum;

@Entity
@Table(name = "record")
public class Record {
    public Record() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @NotNull
    @OneToOne
    @JoinColumn(name = "agencyId", referencedColumnName = "id")
    private Agency agency;

    @NotNull
    private Date recordDatetime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StateEnum state;

    public Integer getRecordId() {
        return id;
    }

    public void setRecordId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Date getRecordDatetime() {
        return recordDatetime;
    }

    public void setRecordDatetime(Date recordDatetime) {
        this.recordDatetime = recordDatetime;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }
}


