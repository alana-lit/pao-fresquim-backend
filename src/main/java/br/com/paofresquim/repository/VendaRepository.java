package br.com.paofresquim.repository;

import br.com.paofresquim.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {
}
