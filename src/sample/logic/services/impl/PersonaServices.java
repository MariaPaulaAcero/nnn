package sample.logic.services.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.logic.entities.Persona;
import sample.logic.services.IPersonaServices;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PersonaServices implements IPersonaServices {

    private Map<UUID, Persona> personas;
    ObservableList<Persona> personasVisual;

    public PersonaServices(){
        this.personas = new HashMap<>();
        this.personasVisual = FXCollections.observableArrayList();
    }

    @Override
    public ObservableList<Persona> getAll() {
        return this.personasVisual;
    }

    @Override
    public Persona getById(UUID id) {
        return null;
    }

    @Override
    public Persona insert(Persona persona) {
        personas.put(persona.getId(), persona);
        personasVisual.add(persona);
        return persona;
    }

    @Override
    public void delete(UUID id) {

    }
}
