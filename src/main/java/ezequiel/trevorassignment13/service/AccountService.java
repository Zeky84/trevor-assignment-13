package ezequiel.trevorassignment13.service;

import ezequiel.trevorassignment13.domain.Account;
import ezequiel.trevorassignment13.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
}
