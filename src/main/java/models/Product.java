/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package models;

/**
 *
 * @author luliou
 */
import java.sql.ResultSet;

public class Product extends Model<Product> {

    private int id;
    private String name;
    private double price;

    public Product() {
        this.table = "product";
        this.primaryKey = "id";
    }

    public Product(int id, String name, double price) {
        this.table = "product";
        this.primaryKey = "id";
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    Product toModel(ResultSet rs) {
        try {

            if (select.equals("*")) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                );
            } else {
                Product p = new Product();
                if (select.contains("id"))
                    p.setId(rs.getInt("id"));
                if (select.contains("name"))
                    p.setName(rs.getString("name"));
                if (select.contains("price"))
                    p.setPrice(rs.getInt("price"));
                return p;
            } 
            } catch(Exception e) {
            setMessage(e.getMessage());
                    }
            return null;
        }

    

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
}
