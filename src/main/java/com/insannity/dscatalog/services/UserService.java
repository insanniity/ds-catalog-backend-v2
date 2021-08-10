package com.insannity.dscatalog.services;

import com.insannity.dscatalog.dto.UserDTO;
import com.insannity.dscatalog.dto.UserInsertDTO;
import com.insannity.dscatalog.entities.Category;
import com.insannity.dscatalog.entities.Role;
import com.insannity.dscatalog.entities.User;
import com.insannity.dscatalog.repositories.CategoryRepository;
import com.insannity.dscatalog.repositories.RoleRepository;
import com.insannity.dscatalog.repositories.UserRepository;
import com.insannity.dscatalog.services.exceptions.DatabaseException;
import com.insannity.dscatalog.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged (Pageable pageable){
        Page<User> list = repository.findAll(pageable);
        return list.map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public UserDTO findById (Long id){
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new NotFoundException("User não encontrado"));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insertNew(UserInsertDTO dto) {
        User entity = new User();
        copyDtoToEntity(entity, dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = repository.getById(id);
            copyDtoToEntity(entity, dto);
            entity = repository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e){
            throw new NotFoundException("Id não encontrado: "+ id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException("Id não encontrado: "+ id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade.");
        }
    }

    private void copyDtoToEntity (User entity, UserDTO dto){
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.getRoles().clear();
        dto.getRoles().forEach(x -> {
            Role role = roleRepository.getById(x.getId());
            entity.getRoles().add(role);
        });
    }

}
