package br.com.fiap.techchallenge5authenticatelogin.repository;

import br.com.fiap.techchallenge5authenticatelogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);

    boolean existsById(String id);
}
