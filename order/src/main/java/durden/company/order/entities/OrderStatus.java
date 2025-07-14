package durden.company.order.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "order_statuses")
@Data
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "orderStatus", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Order> orders = new ArrayList<>();

}

