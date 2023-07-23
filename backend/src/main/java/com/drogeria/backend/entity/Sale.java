package com.drogeria.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales")
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createDate")
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    private Medications medications;


    @Column(name = "quantity")
    private Integer quantity;


    @Column(name = "unitValue",columnDefinition = "NUMBER")
    private BigDecimal unitValue;

    @Column(name = "total",columnDefinition = "NUMBER")
    private BigDecimal total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sale sale = (Sale) o;
        return id != null && Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
