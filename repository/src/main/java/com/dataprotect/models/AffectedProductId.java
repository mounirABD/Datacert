package com.dataprotect.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AffectedProductId implements Serializable {

    private Long idProduct;

    private Long idVulnerability;

    public AffectedProductId(){}

    public AffectedProductId(Long idProduct, Long idVulnerability) {
        this.idProduct = idProduct;
        this.idVulnerability = idVulnerability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AffectedProductId that = (AffectedProductId) o;
        return Objects.equals(idProduct, that.idProduct) &&
                Objects.equals(idVulnerability, that.idVulnerability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, idVulnerability);
    }
}
