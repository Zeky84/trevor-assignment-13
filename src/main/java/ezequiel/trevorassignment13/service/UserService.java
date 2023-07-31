package ezequiel.trevorassignment13.service;

import ezequiel.trevorassignment13.domain.Account;
import ezequiel.trevorassignment13.domain.Address;
import ezequiel.trevorassignment13.domain.User;
import ezequiel.trevorassignment13.repository.AccountRepository;
import ezequiel.trevorassignment13.repository.AddressRepository;
import ezequiel.trevorassignment13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;


    public void saveUser(User user) {

//        if (user.getUser_id() == null) {
//            Account account = new Account();
//            account.setAccountName(user.getFullName()+"New Account");
//            account.getUsers().add(user); //Change this to set instead...
//            accountRepository.save(account);
//            user.getAccounts().add(account);
//        }
        if (user.getAddress() == null) {
            Address address = new Address();
            address.setUser(user);
            address.setUser_id(user.getUser_id());
            user.setAddress(address);
        }else {
            addressRepository.save(user.getAddress());
        }
        userRepository.save(user);
    }

    public Set<User> findAllUsersWithAccountsAndAddress() {
        return userRepository.findAllUsersWithAddressAndAccounts();
    }

    public User findByIdWithAddressAndAccount(Long user_id) {
        return userRepository.findByIdWithAddressAndAccount(user_id).orElse(new User());
    }

    public void deleteById(Long user_id) {
        User userEntity = userRepository.findById(user_id).orElse(null);
        Address addressEntity = addressRepository.findById(user_id).orElse(null);
        addressEntity.setUser(null);
        userEntity.setAddress(null);
        addressRepository.delete(addressEntity);
        userRepository.delete(userEntity);

    }

}
