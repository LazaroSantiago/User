package service;

import entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service("UserService")
public class UserService implements BaseService<User>{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User save(User entity) throws Exception {
        try {
            return this.userRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<User> findAll() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) throws Exception {
        Optional<User> result = userRepository.findById(id);
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
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
