package sample.logic.entities;

import sample.logic.PersonaException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Persona {

    private UUID id;
    private String name;
    private String lastName;
    private LocalDate fechaFallecimiento;
    private String municipio;
    private String departamento;
    private String tipoLider;
    private int age;

    public Persona(String name, String lastName, String fechaFallecimiento, String municipio, String departamento, String tipoLider, String age) throws PersonaException {
        this.name = name;
        this.lastName = lastName;
        this.setFechaFallecimiento(fechaFallecimiento);
        this.municipio = municipio;
        this.departamento = departamento;
        this.tipoLider = tipoLider;
        this.id = UUID.randomUUID();
        this.setAge(age);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMunicipio(){
        return municipio;
    }

    public String getDepartamento(){
        return departamento;
    }

    public String getTipoLider(){
        return tipoLider;
    }


    public void setAge(String ageInput) throws PersonaException {
        try{
            int age = Integer.parseInt(ageInput);

            if(age < 0) throw new PersonaException(PersonaException.BAD_AGE_LOWER);
            if(age > 120) throw new PersonaException(PersonaException.BAD_AGE_UPPER);

            this.age = age;
        }catch (NumberFormatException er) {
            throw new PersonaException(PersonaException.BAD_AGE +" : "+ er.getMessage());
        }
    }

    public void setFechaFallecimiento(String fechaInput){
        this.fechaFallecimiento = LocalDate.parse(fechaInput);
    }

    public LocalDate getFechaFallecimiento(){

        return this.fechaFallecimiento;
    }

    public String getAge(){
        return String.valueOf(this.age);
    }
}
