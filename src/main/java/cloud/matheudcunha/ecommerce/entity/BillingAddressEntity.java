package cloud.matheudcunha.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_billing_address")
public class BillingAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billing_address_id")
    private Long billingAddressId;

    @Column(name = "address")
    private String address;

    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String complement;

    @OneToOne(mappedBy = "billingAddress")
    private UserEntity user;

    public BillingAddressEntity() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Long getId() {
        return billingAddressId;
    }

    public void setId(Long billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
