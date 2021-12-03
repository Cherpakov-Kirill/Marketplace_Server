package nsu.oop.marketplace.server.database.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "products", schema = "marketplace_db")
public class ProductsEntity {

    private int id;
    private String name;
    private double price;
    private String description;
    private Collection<ChangesEntity> changesById;
    private Collection<SalesEntity> salesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 4)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(targetEntity = ChangesEntity.class, mappedBy = "productsByProductId", fetch = FetchType.LAZY)
    public Collection<ChangesEntity> getChangesById() {
        return changesById;
    }

    public void setChangesById(Collection<ChangesEntity> changesById) {
        this.changesById = changesById;
    }

    @OneToMany(targetEntity = SalesEntity.class, mappedBy = "productsByProductId", fetch = FetchType.LAZY)
    public Collection<SalesEntity> getSalesById() {
        return salesById;
    }

    public void setSalesById(Collection<SalesEntity> salesById) {
        this.salesById = salesById;
    }

    @Override
    public String toString(){
        return id + " - " + name + " - " + price + " - " + description + "-";
    }
}
