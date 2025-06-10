package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.JwtUtil;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Usuario;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        // buscando un usuario por su nombre de usuario
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // verificando si la contraseña proporcionada coincide con la almacenada
        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // generando un token JWT para el usuario autenticado
        return jwtUtil.generateToken(usuario.getUsername(), usuario.getRol());
    }
}

