package br.com.paofresquim.repository;

import br.com.paofresquim.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
//    Optional<Funcionario> findByCpf(String cpf);

    Optional<Funcionario> findById(Long id);
    Optional<Funcionario> findByMatricula(Long matricula);
}
