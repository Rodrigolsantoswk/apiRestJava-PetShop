package com.example.repository;

import com.example.entities.ProdutoVenda;
import com.example.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Integer> {
	List<ProdutoVenda> findByVenda(Venda venda);
}
