package sample.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.PersonaException;
import sample.logic.entities.Persona;
import sample.logic.entities.ProfessionEnum;
import sample.logic.services.impl.PersonaServices;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    // Visual Properties
    private Scene scene;
    private TableView<Persona> personasTable;
    private TextField nameInput;
    private TextField lastNameInput;
    private TextField deathDateInput;
    private TextField municipalityInput;
    private TextField departmentInput;
    private TextField professionInput;
    private TextField isVictimInput;

    private Button addPersona;
    private Button deletePersona;
    private Button openReport;

    // Menu
    private MenuBar menuBar;
    private Map<String, MenuItem> fileMenuItems;

    // Logic Properties
    private PersonaServices personaServices;


    //CRUD -
    @Override
    public void start(Stage primaryStage) throws Exception {

        setUp();
        behavior(primaryStage);

        primaryStage.setTitle("final project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void behavior(Stage stage) {
        this.personaServices = new PersonaServices();
        try {
            this.personaServices.insert(new Persona("Edwin", "Antonio Indaburo", "07/01/2021", "Nechí", "Antoquia", ProfessionEnum.CAMPESINO,true));
            this.personaServices.insert(new Persona("Alfredo", "García", "10/01/2021", "Ituango", "Antoquia", ProfessionEnum.CAMPESINO,true));
            this.personaServices.insert(new Persona("Yordan Eduardo", "Guetio", "02/02/2021", "Corinto", "Cauca", ProfessionEnum.CAMPESINO,true));
            this.personaServices.insert(new Persona("Albeiro", "Hoyos", "05/04/2021", "Anorí", "Antoquia", ProfessionEnum.CAMPESINO,true));
            this.personaServices.insert(new Persona("Wilson", "Lopez", "28/04/2021", "Aguadas", "Caldas", ProfessionEnum.CAMPESINO,true));
            this.personaServices.insert(new Persona("Aldinever", "Cruz Guaraca", "09/05/2021", "Aipe", "Huila", ProfessionEnum.CAMPESINO,true));

            this.personaServices.insert(new Persona("Robinson", "Quino Bonilla", "15/01/2021","Yondó","Antoquia",ProfessionEnum.COMUNAL,true));
            this.personaServices.insert(new Persona("Janeth", "Zapata", "21/01/2021","Dosquebradas","Risaralda",ProfessionEnum.COMUNAL,true));
            this.personaServices.insert(new Persona("José Miguel", "Barrientos Uribe", "28/01/2021","Yarumal","Antoquia",ProfessionEnum.COMUNAL,true));
            this.personaServices.insert(new Persona("Arcenio", "Quinayás Ruiz", "30/01/2021","San Agustín","Huila",ProfessionEnum.COMUNAL,true));
            this.personaServices.insert(new Persona("Remberto", "Arrieta Bohorquez", "31/01/2021","Tarazá","Antoquia",ProfessionEnum.COMUNAL,true));
            this.personaServices.insert(new Persona("Yordan Eduardo", "Guetio","02/02/2021","Corinto","Cauca",ProfessionEnum.COMUNAL,true));
            this.personaServices.insert(new Persona("Ovidio de Jesús", "Salazar","02/02/2021","Herveo","Tolima",ProfessionEnum.COMUNAL,true));
            this.personaServices.insert(new Persona("Juan Carlos", "Aguirre","14/04/2021","La macarena","Meta",ProfessionEnum.COMUNAL,true));
            this.personaServices.insert(new Persona("Justiniano", "Torres García","19/04/2021","Bucaramanga","Santander",ProfessionEnum.COMUNAL,true));
            this.personaServices.insert(new Persona("Jorge Emilio", "Ramírez Venegas","09/05/2021","Aipe","Huila",ProfessionEnum.COMUNAL,true));

            this.personaServices.insert(new Persona("Gerardo", "León", "01/01/2021","Puerto Gaitán","Meta", ProfessionEnum.SINDICAL,true));
            this.personaServices.insert(new Persona("Diego", "Betancourt Higuera", "01/01/2021","El Yopal","Casanare",ProfessionEnum.SINDICAL,true));
            this.personaServices.insert(new Persona("Carlos Alberto", "Vidal", "29/03/2021","Florida","Valle del Cauca",ProfessionEnum.SINDICAL,true));
            this.personaServices.insert(new Persona("Beatríz", "Moreno Mosquera", "03/05/2021","Buenaventura","Valle",ProfessionEnum.SINDICAL,true));

            this.personaServices.insert(new Persona("Andrés", "Moreno", "10/04/2021","Mosquera","Cauca",ProfessionEnum.LGTBIQ,true));

        } catch (PersonaException e) {
            e.printStackTrace();
        }
        personasTable.setItems((ObservableList<Persona>) this.personaServices.getAll());

        addPersona.setOnAction(e -> {
            try {
                Persona p = new Persona(nameInput.getText(), lastNameInput.getText(), deathDateInput.getText(), municipalityInput.getText(), departmentInput.getText());
                this.personaServices.insert(p);
                nameInput.clear();
                lastNameInput.clear();
                deathDateInput.clear();
                municipalityInput.clear();
                departmentInput.clear();
            } catch (PersonaException personaException) {
                personaException.printStackTrace();
            }
        });

        deletePersona.setOnAction(e -> {
            this.personaServices.delete(personasTable.getSelectionModel().getSelectedItems());
        });

        fileMenuItems.get("Export").setOnAction(e -> {
            try {
                this.personaServices.export();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        fileMenuItems.get("Import").setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select personas file");
            File file = fileChooser.showOpenDialog(stage);
            if (file == null) {
                System.out.println("No file");
            } else {
                try {
                    this.personaServices.importPersonas(file);
                    this.personaServices.getAll().stream().forEach(p -> System.out.println(p));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        openReport.setOnAction(e -> {
            new ReportScene();
        });
    }


    private void setUp() {
        setupTable();
        setupInputs();
        setupMenu();
        setUpCrud();

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, lastNameInput, deathDateInput, municipalityInput, departmentInput, addPersona, deletePersona);

        //Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(personasTable, hBox);

        BorderPane layout2 = new BorderPane();
        layout2.setTop(menuBar);
        layout2.setCenter(layout);

        //Scene
        //scene = new Scene(layout, 770, 300);
        scene = new Scene(layout2, 400, 400);

    }

    private void setUpCrud() {
        addPersona = new Button("Add");
        addPersona.setMinWidth(30);

        deletePersona = new Button("Delete");
        deletePersona.setMinWidth(30);

        openReport = new Button("Open Report");
        openReport.setMinWidth(90);
    }

    private void setupInputs() {
        nameInput = new TextField();
        nameInput.setPromptText("name");
        nameInput.setMinWidth(30);

        lastNameInput = new TextField();
        lastNameInput.setPromptText("last name");
        lastNameInput.setMinWidth(30);

        deathDateInput = new TextField();
        deathDateInput.setPromptText("death date");
        deathDateInput.setMinWidth(30);

        municipalityInput = new TextField();
        municipalityInput.setPromptText("municipality");
        municipalityInput.setMinWidth(30);

        departmentInput = new TextField();
        departmentInput.setPromptText("department");
        departmentInput.setMinWidth(30);
    }

    private void setupTable() {
        //Name column
        TableColumn<Persona, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMaxWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Name column
        TableColumn<Persona, String> lastNameColumn = new TableColumn<>("LastName");
        lastNameColumn.setMaxWidth(200);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        //Name column
        TableColumn<Persona, Integer> deathDateColumn = new TableColumn<>("Death Date");
        deathDateColumn.setMaxWidth(200);
        deathDateColumn.setCellValueFactory(new PropertyValueFactory<>("deathDate"));

        //Name column
        TableColumn<Persona, Integer> municipalityColumn = new TableColumn<>("Municipality");
        municipalityColumn.setMaxWidth(200);
        municipalityColumn.setCellValueFactory(new PropertyValueFactory<>("municipality"));

        //Name column
        TableColumn<Persona, Integer> departmentColumn = new TableColumn<>("Department");
        departmentColumn.setMaxWidth(200);
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        //Table
        personasTable = new TableView<>();
        personasTable.getColumns().addAll(nameColumn, lastNameColumn, deathDateColumn, municipalityColumn, departmentColumn);
    }

   private void setupMenu() {

        Menu fileMenu = new Menu("File");

        fileMenuItems = new HashMap<>();
        fileMenuItems.put("Import", new MenuItem("Import"));
        fileMenuItems.put("Export", new MenuItem("Export"));

        fileMenu.getItems().add(fileMenuItems.get("Import"));
        fileMenu.getItems().add(fileMenuItems.get("Export"));

        menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
