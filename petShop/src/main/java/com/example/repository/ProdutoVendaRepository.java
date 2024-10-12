package com.example.repository;

import com.example.entities.ProdutoVenda;
import com.example.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Integer> {
	@Query("SELECT pv FROM ProdutoVenda pv " +
           "JOIN FETCH pv.venda v " +
           "JOIN FETCH pv.produto p " +
           "WHERE v.idVenda = :idVenda")
    List<ProdutoVenda> buscarProdutosPorVenda(@Param("idVenda") Integer idVenda);
	
	List<ProdutoVenda> findByVenda(Venda venda);
	
}
