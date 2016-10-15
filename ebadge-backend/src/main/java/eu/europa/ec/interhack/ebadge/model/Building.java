package eu.europa.ec.interhack.ebadge.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Date;

/**
 * Created by rromero on 15/10/16.
 */
@Document
public class Building {

    @Id
    private String code;
    private @Version Long version;
    private @LastModifiedDate Date date;
    private String name;
    private String address;
    private Integer postalCode;
    private Collection<String> occupiers;
    private Collection<String> telephones;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public Collection<String> getOccupiers() {
        return occupiers;
    }

    public void setOccupiers(Collection<String> occupiers) {
        this.occupiers = occupiers;
    }

    public Collection<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(Collection<String> telephones) {
        this.telephones = telephones;
    }
}
