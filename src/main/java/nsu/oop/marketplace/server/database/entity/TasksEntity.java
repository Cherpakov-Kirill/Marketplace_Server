package nsu.oop.marketplace.server.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "tasks", schema = "marketplace_db")
public class TasksEntity {

    private int id;
    private String taskText;
    private boolean done;
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
    @Column(name = "task_text", nullable = false, length = 255)
    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    @Basic
    @Column(name = "done", nullable = false)
    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @Override
    public String toString(){
        return id + " - " + taskText + " - " + usersByUserId.getFirstName() + " - " + usersByUserId.getLastName() + " - " + done + " - ";
    }
}
