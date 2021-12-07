package nsu.oop.marketplace.server.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "log_history", schema = "marketplace_db")
public class LogHistoryEntity {

    private int id;
    private int userId;
    private String logDescription;
    private String actionType;
    private UsersEntity usersByUserId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "log_description", nullable = false, length = 255)
    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    @Basic
    @Column(name = "action_type", nullable = false, length = 255)
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @Override
    public String toString() {
        return usersByUserId.getFirstName() + " - " + usersByUserId.getLastName() + " - " + logDescription + " - " + actionType + " - ";
    }
}
