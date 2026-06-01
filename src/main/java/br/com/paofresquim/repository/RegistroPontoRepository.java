package br.com.paofresquim.repository;

import br.com.paofresquim.model.Funcionario;
import br.com.paofresquim.model.RegistroPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistroPontoRepository
        extends JpaRepository<RegistroPonto, Long> {

    List<RegistroPonto> findByFuncionario(
            Funcionario funcionario
    );

    boolean existsByFuncionarioAndDataRegistro(
            Funcionario funcionario,
            LocalDate dataRegistro
    );
}