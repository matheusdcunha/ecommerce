package cloud.matheudcunha.ecommerce.service;


import cloud.matheudcunha.ecommerce.controller.dto.CreateUserDto;
import cloud.matheudcunha.ecommerce.entity.BillingAddressEntity;
import cloud.matheudcunha.ecommerce.entity.UserEntity;
import cloud.matheudcunha.ecommerce.repository.BillingAddressRepository;
import cloud.matheudcunha.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BillingAddressRepository billingAddressRepository;

    public UserService(
            UserRepository userRepository,
            BillingAddressRepository billingAddressRepository
    ) {
        this.userRepository = userRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UserEntity createUser(CreateUserDto dto){
        var billingAddress = new BillingAddressEntity();
        billingAddress.setAddress(dto.address());
        billingAddress.setComplement(dto.complement());
        billingAddress.setNumber(dto.number());

        var savedBillingAddress = this.billingAddressRepository.save(billingAddress);

        var user = new UserEntity();
        user.setfullName(dto.fullName());
        user.setBillingAddress(savedBillingAddress);

        return this.userRepository.save(user);
    }

    public Optional<UserEntity> findById(UUID userId) {
        return this.userRepository.findById(userId);
    }

    public boolean deleteUser(UUID userId) {
        var user = this.userRepository.findById(userId);

        if (user.isPresent()){
            this.userRepository.delete(user.get());

            this.billingAddressRepository.delete(user.get().getBillingAddress());
        }

        return user.isPresent();
    }
}
