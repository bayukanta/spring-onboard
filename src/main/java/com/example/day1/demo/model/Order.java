package com.example.day1.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order1")
public class Order {
    @Id
    @GenericGenerator(name = "UUID")
    private String id;
    @Column(name = "order_number", nullable = false, length = 64)
    private String orderNumber;
    @Column(name = "total_price", nullable = false, length = 64)
    private double totalPrice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", foreignKey=@ForeignKey(name = "fk_customer"))
    private Customer customer;


}
