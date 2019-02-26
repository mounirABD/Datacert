package com.dataprotect.models;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class AffectedProduct {

    @Embedded
    private AffectedProductId affectedProductId;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vulnerability vulnerability;

    public AffectedProduct() {}

    public AffectedProduct(AffectedProductId affectedProductId, String description, Product product, Vulnerability vulnerability) {
        this.affectedProductId = affectedProductId;
        this.description = description;
        this.product = product;
        this.vulnerability = vulnerability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AffectedProduct that = (AffectedProduct) o;
        return Objects.equals(affectedProductId, that.affectedProductId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(product, that.product) &&
                Objects.equals(vulnerability, that.vulnerability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(affectedProductId, description, product, vulnerability);
    }
}
