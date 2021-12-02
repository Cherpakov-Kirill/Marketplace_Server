package nsu.oop.marketplace.server.database.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "products", schema = "marketplace")
public class ProductsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "price", nullable = false, precision = 4)
    private double price;
    @Basic
    @Column(name = "description", nullable = false, length = 255)
    private String description;
    @OneToMany(mappedBy = "productsByProductId")
    private Collection<ChangesEntity> changesById;
    @OneToMany(mappedBy = "productsByProductId")
    private Collection<SalesEntity> salesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<ChangesEntity> getChangesById() {
        return changesById;
    }

    public void setChangesById(Collection<ChangesEntity> changesById) {
        this.changesById = changesById;
    }

    public Collection<SalesEntity> getSalesById() {
        return salesById;
    }

    public void setSalesById(Collection<SalesEntity> salesById) {
        this.salesById = salesById;
    }
}
