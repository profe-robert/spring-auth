package biblioteca.salas.duoc.biblioteca.salas.duoc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
