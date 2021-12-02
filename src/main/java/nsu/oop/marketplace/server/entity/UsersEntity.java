package nsu.oop.marketplace.server.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "marketplace")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;
    @Basic
    @Column(name = "role", nullable = false, length = 255)
    private String role;
    @OneToMany(mappedBy = "usersByUserId")
    private Collection<ChangesEntity> changesById;
    @OneToMany(mappedBy = "usersByUserId")
    private Collection<LogHistoryEntity> logHistoriesById;
    @OneToMany(mappedBy = "usersByUserId")
    private Collection<LoginInfoEntity> loginInfosById;
    @OneToMany(mappedBy = "usersByUserId")
    private Collection<TasksEntity> tasksById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<ChangesEntity> getChangesById() {
        return changesById;
    }

    public void setChangesById(Collection<ChangesEntity> changesById) {
        this.changesById = changesById;
    }

    public Collection<LogHistoryEntity> getLogHistoriesById() {
        return logHistoriesById;
    }

    public void setLogHistoriesById(Collection<LogHistoryEntity> logHistoriesById) {
        this.logHistoriesById = logHistoriesById;
    }

    public Collection<LoginInfoEntity> getLoginInfosById() {
        return loginInfosById;
    }

    public void setLoginInfosById(Collection<LoginInfoEntity> loginInfosById) {
        this.loginInfosById = loginInfosById;
    }

    public Collection<TasksEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TasksEntity> tasksById) {
        this.tasksById = tasksById;
    }
}
