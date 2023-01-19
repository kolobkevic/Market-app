package ru.kolobkevic.market.core.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OrderItem> items;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Order() {
    }

    public static Builder createBuilder(){
        return new Builder();
    }

    public static class Builder{
        private final Order order;

        public Builder(){
            this.order = new Order();
        }

        public Builder withUsername(String username){
            this.order.username = username;
            return this;
        }

        public Builder withTotalPrice(BigDecimal totalPrice){
            this.order.totalPrice = totalPrice;
            return this;
        }

        public Builder withAddress(String address){
            this.order.address = address;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber){
            this.order.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withItems (List<OrderItem> items){
            this.order.items.clear();
            this.order.items.addAll(items);
            return this;
        }

        public Order build(){
            return order;
        }
    }

}
