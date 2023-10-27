package service;

import entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service("AccountService")
public class AccountService implements BaseService<Account> {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account save(Account entity) throws Exception {
        return null;
    }

    @Override
    public List<Account> findAll() throws Exception {
        return null;
    }

    @Override
    @Transactional
    public Account findById(Long id) throws Exception {
        try {
            Optional<Account> result = accountRepository.findById(id);
            return result.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }

    @Transactional
    public boolean verifyAccount(Long id) throws Exception {
        Account account = this.findById(id);

        if (account.isBanned())
            throw new Exception("Cuenta deshabilitada.");
        else if (account.getFunds() == 0)
            throw new Exception("No hay fondos.");
        else
            return true;
    }

    @Transactional
    public Long payTrip(Long id, Long fare) throws Exception {
        Account account = this.findById(id);

        if (fare > account.getFunds())
            account.setFunds(0);
        else
            account.setFunds(account.getFunds() - fare);

        return account.getFunds();
    }
}
