package com.insannity.dscatalog.services;

import com.insannity.dscatalog.dto.ProductDTO;
import com.insannity.dscatalog.entities.Category;
import com.insannity.dscatalog.entities.Product;
import com.insannity.dscatalog.repositories.CategoryRepository;
import com.insannity.dscatalog.repositories.ProductRepository;
import com.insannity.dscatalog.services.exceptions.DatabaseException;
import com.insannity.dscatalog.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged (Pageable pageable){
        Page<Product> list = repository.findAll(pageable);
        return list.map(x -> new ProductDTO(x, x.getCategories()));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById (Long id){
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insertNew(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
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

    private void copyDtoToEntity (ProductDTO dto,Product entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.getCategories().clear();
        dto.getCategories().forEach(x -> {
            Category category = categoryRepository.getById(x.getId());
            entity.getCategories().add(category);
        });
    }

}
