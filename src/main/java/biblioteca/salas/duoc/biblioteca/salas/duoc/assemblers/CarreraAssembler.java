package biblioteca.salas.duoc.biblioteca.salas.duoc.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import biblioteca.salas.duoc.biblioteca.salas.duoc.controller.CarreraController;
import biblioteca.salas.duoc.biblioteca.salas.duoc.controller.SalaController;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class CarreraAssembler implements RepresentationModelAssembler<Carrera, EntityModel<Carrera>> {

    @Override
    public EntityModel<Carrera> toModel(Carrera carrera) {
        EntityModel<Carrera> carreraModel = EntityModel.of(carrera);

        carreraModel.add(
            linkTo(
                methodOn(CarreraController.class) // controlador
                .getCarreraByCodigo(carrera.getCodigo()) // método
            )
            .withSelfRel()
        );

        carreraModel.add(
            linkTo(
                methodOn(CarreraController.class)
                .getAllCarreras()
            )
            .withRel("carreras")
        );
        carreraModel.add(
            linkTo(
                methodOn(SalaController.class).getAllSalas()
            )
            .withRel("salas")
        );
        
        return carreraModel;
        
        // return EntityModel.of(carrera,
        //     linkTo(methodOn(CarreraController.class).getCarreraByCodigo(carrera.getCodigo())).withSelfRel(),
        //     linkTo(methodOn(CarreraController.class).getAllCarreras()).withRel("carreras")
        // );
    }
}