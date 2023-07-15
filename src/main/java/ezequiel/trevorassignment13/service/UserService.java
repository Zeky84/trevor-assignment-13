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

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(User user) {
        if(user.getUser_id()!=null){
            Address address = new Address();
            address.setUser(user);
//            address.setUser_id(user.getUser_id());
            user.setAddress(address);
            address.setUser_id(user.getUser_id());
            addressRepository.save(address);
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(new User());
    }

    public void deleteUserById(Long user_id) {
        userRepository.deleteById(user_id);
    }
}
