package com.example.usuario.service;

import com.example.usuario.dto.ScooterDTO;
import com.example.usuario.entity.User;
import com.example.usuario.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service("UserService")
public class UserService implements BaseService<User> {

    @Autowired
    private RestTemplate restTemplate;

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
        try {
            return result.orElseThrow();
        } catch (Exception e) {
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

//    Como usuario quiero un listado de los monopatines cercanos a mi zona, para poder encontrar
//    un monopatín cerca de mi ubicación
    public List<ScooterDTO> getScooterByLocation(String location) throws Exception {
        String url = "localhost:8082/scooters/location/{" + location + "}";
        ResponseEntity<ScooterDTO[]> responseEntity = restTemplate.getForEntity(url, ScooterDTO[].class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ScooterDTO[] scooterArray = responseEntity.getBody();
            assert scooterArray != null;
            return Arrays.asList(scooterArray);
        } else {
            throw new Exception("Error al obtener datos del microservicio.");
        }
    }

}
