package nsu.oop.marketplace.server.database.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "marketplace_db")
public class UsersEntity {
    private int id;
    private String firstName;
    private String lastName;
    private String role;

    private Collection<ChangesEntity> changesById;

    private Collection<LogHistoryEntity> logHistoriesById;

    private Collection<LoginInfoEntity> loginInfosById;

    private Collection<TasksEntity> tasksById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 255)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @OneToMany(targetEntity = ChangesEntity.class, mappedBy = "usersByUserId", fetch=FetchType.LAZY)
    public Collection<ChangesEntity> getChangesById() {
        return changesById;
    }

    public void setChangesById(Collection<ChangesEntity> changesById) {
        this.changesById = changesById;
    }

    @OneToMany(targetEntity = LogHistoryEntity.class, mappedBy = "usersByUserId", fetch=FetchType.LAZY)
    public Collection<LogHistoryEntity> getLogHistoriesById() {
        return logHistoriesById;
    }

    public void setLogHistoriesById(Collection<LogHistoryEntity> logHistoriesById) {
        this.logHistoriesById = logHistoriesById;
    }

    @OneToMany(targetEntity = LoginInfoEntity.class, mappedBy = "usersByUserId", fetch=FetchType.LAZY)
    public Collection<LoginInfoEntity> getLoginInfosById() {
        return loginInfosById;
    }

    public void setLoginInfosById(Collection<LoginInfoEntity> loginInfosById) {
        this.loginInfosById = loginInfosById;
    }

    @OneToMany(targetEntity = TasksEntity.class, mappedBy = "usersByUserId", fetch=FetchType.LAZY)
    public Collection<TasksEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TasksEntity> tasksById) {
        this.tasksById = tasksById;
    }

    @Override
    public String toString(){
        return id + " - " + firstName + " - " + lastName + " - " + role + "-";
    }
}
