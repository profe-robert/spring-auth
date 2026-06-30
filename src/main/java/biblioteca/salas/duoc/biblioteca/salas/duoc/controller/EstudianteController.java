package biblioteca.salas.duoc.biblioteca.salas.duoc.controller;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.findAll();
    }

    @GetMapping("/{id}")
    public Estudiante getEstudianteById(@PathVariable Integer id) {
        Estudiante estudiante = estudianteService.findById(id);
        // CORRECCIÓN 1: Manejar el caso de que no exista (evitar devolver 200 OK con null)
        if (estudiante == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado con ID: " + id);
        }
        return estudiante;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // CORRECCIÓN 2: Devolver 201 Created al crear
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.save(estudiante);
    }

    @PutMapping("/{id}")
    public Estudiante updateEstudiante(@PathVariable Integer id, @RequestBody Estudiante estudiante) {
        // Opcional: También podrías verificar si existe antes de actualizar
        estudiante.setId(id);
        return estudianteService.save(estudiante);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // CORRECCIÓN 3: Devolver 204 No Content al eliminar
    public void deleteEstudiante(@PathVariable Integer id) {
        estudianteService.deleteById(id);
    }
}
