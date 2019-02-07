package com.springcommerceapi.SpringCommerceAPI.repository;

import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface IProdutoRepository extends CrudRepository<Produto,Long> {

    Produto findByNome(String nome);
    Optional<Produto> findById(Long id);
}