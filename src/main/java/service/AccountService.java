package service;

import entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service("AccountService")
public class AccountService implements BaseService<Account> {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public Account save(Account entity) throws Exception {
        try {
            return this.accountRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) throws Exception {
        Optional<Account> result = accountRepository.findById(id);
        try{
            return result.orElseThrow();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try {
            if (accountRepository.existsById(id)) {
                accountRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean banAccount(Long id) throws Exception{
        try {
            if (accountRepository.existsById(id)) {
                accountRepository.banAccount(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean unbanAccount(Long id) throws Exception{
        try {
            if (accountRepository.existsById(id)) {
                accountRepository.unbanAccount(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
