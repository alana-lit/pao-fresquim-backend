package br.com.paofresquim.repository;

import br.com.paofresquim.model.Funcionario;
import br.com.paofresquim.model.Atestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AtestadoRepository extends JpaRepository<Atestado, Long> {

    List<Atestado> findByFuncionario(
            Funcionario funcionario
    );
}
