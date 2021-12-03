package nsu.oop.marketplace.server.database.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales", schema = "marketplace_db")
public class SalesEntity {

    private int id;
    private Date date;
    private int quantity;
    private double amount;
    private ProductsEntity productsByProductId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "amount", nullable = false, precision = 4)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public ProductsEntity getProductsByProductId() {
        return productsByProductId;
    }

    public void setProductsByProductId(ProductsEntity productsByProductId) {
        this.productsByProductId = productsByProductId;
    }

    @Override
    public String toString() {
        return id + " - " + productsByProductId.getName() + " - " + date + " - " + quantity + " - " + amount + " - ";
    }
}
