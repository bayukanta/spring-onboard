package com.example.day1.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GenericGenerator(name = "UUID")
    private String id;

    @Getter
    @Column(name = "number", nullable = false, length = 32)
    private String number;
    @Getter
    @Column(name = "first_name", nullable = false, length = 32)
    private String firstName;
    @Getter
    @Column(name = "last_name", nullable = false, length = 32)
    private String lastName;
    @Getter
    @Column(name = "email", nullable = false, length = 32)
    private String email;
    @Getter
    @Column(name = "city", nullable = false, length = 32)
    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Order> order;

    public Customer(String id, String customerNumber, String firstName, String lastName, String email, String city) {
    }



}
