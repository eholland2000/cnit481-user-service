package org.cnit481.group11.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "USER_STOCK")
public class UserStock extends PanacheEntityBase implements Serializable {
    @Id private String stockSymbol;
    @Id private String username;
    private String dateAdded;

    public UserStock(String stockSymbol, String username, String dateAdded) {
        this.stockSymbol = stockSymbol;
        this.username = username;
        this.dateAdded = dateAdded;
    }

    public UserStock() {
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
