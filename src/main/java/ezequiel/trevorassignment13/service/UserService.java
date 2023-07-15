package ezequiel.trevorassignment13.service;

import ezequiel.trevorassignment13.domain.Account;
import ezequiel.trevorassignment13.domain.Address;
import ezequiel.trevorassignment13.domain.User;
import ezequiel.trevorassignment13.repository.AccountRepository;
import ezequiel.trevorassignment13.repository.AddressRepository;
import ezequiel.trevorassignment13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AccountRepository accountRepository;

    public User saveUser(User user) {
//        if(user.getUser_id()==null){ // here you have to create only one account and set the name for each account. pass a parameter to this method.
//            // and you are going point to this parameter with the @PathVariable to because this one is going to give you the name of th account
//            Account account= new Account();
//            account.setAccountName(accountName);
//            account.getUsers().add(user);
//            accountRepository.save(account);
//            user.getAccounts().add(account);
//    }
//        if(user.getAddress()==null){
//            Address address = new Address();
//            address.setAddressLine1("El reino de los Cielos");
//            address.setAddressLine2("Pertenece a los ninos");
//            address.setCity("Love City");
//            address.setCountry("Eden");
//            address.setRegion("In the Sky");
//            address.setZipCode("333");
//            address.setUser(user);
//            address.setUser_id(user.getUser_id());
//            user.setAddress(address);
//        }
        return userRepository.save(user);
    }
    public User save(User user) {
        return userRepository.save(user);
    }

    public Set<User> getAllUsers() {
        return userRepository.findAllUsersWithAccountAndAddress();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(new User());
    }

    public void deleteById(Long user_id) {
        userRepository.deleteById(user_id);

    }
}
