package cloud.matheudcunha.ecommerce.entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billing_address_id")
    private BillingAddressEntity billingAddress;

    public UserEntity() {
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getfullName() {
        return this.fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }

    public BillingAddressEntity getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddressEntity billingAddress) {
        this.billingAddress = billingAddress;
    }
}
