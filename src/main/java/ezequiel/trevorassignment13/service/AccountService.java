package ezequiel.trevorassignment13.service;

import ezequiel.trevorassignment13.domain.Account;
import ezequiel.trevorassignment13.domain.User;
import ezequiel.trevorassignment13.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public Account findById(Long accountId) {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        return accountOpt.orElse(new Account());
    }
}
