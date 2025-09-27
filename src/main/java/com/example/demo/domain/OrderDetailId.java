package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class OrderDetailId implements java.io.Serializable {
    private static final long serialVersionUID = 6842449356352416454L;
    @Size(max = 60)
    @NotNull
    @Column(name = "id_order", nullable = false, length = 60)
    private String idOrder;

    @Size(max = 60)
    @NotNull
    @Column(name = "id_product", nullable = false, length = 60)
    private String idProduct;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderDetailId entity = (OrderDetailId) o;
        return Objects.equals(this.idOrder, entity.idOrder) &&
                Objects.equals(this.idProduct, entity.idProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idProduct);
    }

}