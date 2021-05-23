package sample.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.logic.PersonaException;
import sample.logic.entities.Persona;
import sample.logic.services.IPersonaServices;
import sample.logic.services.impl.PersonaServices;

import java.time.LocalDate;

public class BasicScene extends Application {

    //private Button button;
    //private TextField input;
    private Scene scene;
    private TableView<Persona> personasTable;

    private IPersonaServices personaServices;

    @Override
    public void start(Stage primaryStage) throws Exception{
        setUp();
        behavior();

        primaryStage.setTitle("Proyecto final");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void behavior(){

        /*button.setOnAction(e -> {
            try {
                persona.setAge(input.getText());
                System.out.println(persona.getAge());
            } catch (PersonaException personaException) {
                personaException.printStackTrace();
            }
        });*/

        this.personaServices = new PersonaServices();
        try {
            this.personaServices.insert(new Persona("Ricardo", "Cortes Rozo", "2021-01-02", "Bogotá", "Bogotá", "Cívico", "22" ));
            this.personaServices.insert(new Persona("Fredman", "Herazo Padilla", "2021-01-15", "La Apartada", "Córdoba", "Afrodescendiente", "28" ));
            this.personaServices.insert(new Persona("Carlos Erlid", "González Cortés", "2021-01-10", "Buga", "Valle del Cauca", "Cívico", "25" ));
            this.personaServices.insert(new Persona("Fermiliano", "Meneses", "2021-01-15", "Argelia", "Cauca", "Cívico", "32" ));
            this.personaServices.insert(new Persona("William Antonio", "Rodriguez", "2021-01-16", "Cucúta", "Norte de Santander", "Cívico", "43" ));
            this.personaServices.insert(new Persona("Linda", "Díaz Romero", "2021-01-19", "Cáceres", "Antioquia", "Cívico", "33" ));
            this.personaServices.insert(new Persona("José", "Abadía Parra", "2021-01-20", "Pereira", "Risaralda", "Cívico", "24" ));
            this.personaServices.insert(new Persona("Julian Sneider", "Muñoz", "2021-01-23", "Cali", "Valle del Cauca", "Cívico", "36" ));
            this.personaServices.insert(new Persona("Giovanis", "Carranza Castillo", "2021-01-27", "Valledupar", "Cesar", "Cívico", "47" ));
            this.personaServices.insert(new Persona("Yecid Andres", "Bolaño", "2021-02-08", "Barranquilla", "Atlántico", "Cívico", "23" ));
            this.personaServices.insert(new Persona("José", "Riascos", "2021-04-08", "Nuquí", "Chocó", "Afrodescendiente", "28" ));
            this.personaServices.insert(new Persona("Luis", "Hermídes Alvarez", "2021-03-03", "Río de Oro", "Cesar", "Cívico", "38" ));
        } catch (PersonaException e) {
            e.printStackTrace();
        }

        personasTable.setItems(this.personaServices.getAll());

    }

    private void setUp(){

        /*input = new TextField();

        button = new Button();
        button.setText("Click me");*/

        TableColumn<Persona, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setMaxWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Persona, String> lastNameColumn = new TableColumn<>("Apellido");
        lastNameColumn.setMaxWidth(200);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Persona, Integer> ageColumn = new TableColumn<>("Edad");
        ageColumn.setMaxWidth(200);
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Persona, LocalDate> fechaColumn = new TableColumn<>("Fecha de Fallecimiento");
        fechaColumn.setMaxWidth(200);
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaFallecimiento"));

        TableColumn<Persona, String> municipioColumn = new TableColumn<>("Municipio");
        municipioColumn.setMaxWidth(200);
        municipioColumn.setCellValueFactory(new PropertyValueFactory<>("municipio"));

        TableColumn<Persona, String> departamentoColumn = new TableColumn<>("Departamento");
        departamentoColumn.setMaxWidth(200);
        departamentoColumn.setCellValueFactory(new PropertyValueFactory<>("departamento"));

        TableColumn<Persona, String> liderColumn = new TableColumn<>("Tipo de Lider");
        liderColumn.setMaxWidth(200);
        liderColumn.setCellValueFactory(new PropertyValueFactory<>("tipoLider"));

        personasTable = new TableView<>();
        personasTable.getColumns().addAll(nameColumn, lastNameColumn, ageColumn, fechaColumn, municipioColumn, departamentoColumn, liderColumn);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(personasTable);

        scene = new Scene(layout, 770, 300);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
