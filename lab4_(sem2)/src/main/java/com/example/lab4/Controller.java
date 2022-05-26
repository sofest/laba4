package com.example.lab4;

import com.example.lab4.bd.Settter;
import com.example.lab4.bd.DBProvider;
import com.example.lab4.bd.SimplePr;
import com.example.lab4.bd.classBD.Company;
import com.example.lab4.bd.classBD.Country;
import com.example.lab4.bd.classBD.Region;
import com.example.lab4.cast.ParserJSON;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    ParserJSON parserJSON = null;
    DBProvider provider;
    Settter calc;

    @FXML
    private Button btn;

    @FXML
    private TableColumn<SimplePr, SimpleStringProperty> column1;

    @FXML
    private TableColumn<SimplePr, SimpleStringProperty> column2;

    @FXML
    private TableView<SimplePr> table;

    public void initialize(){btn.setDisable(true);}

    public static void error(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    public static void error(String e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e);
        alert.showAndWait();
    }


    @FXML
    void companyT(ActionEvent event) {
        column1.setText("Компания");
        column2.setText("Объем ежегодного потребления, т.");
        ObservableList<SimplePr> data = FXCollections.observableArrayList();
        for (Company company : provider.getCompanies()) {
            data.add(new SimplePr(new SimpleStringProperty(company.getName()), new SimpleDoubleProperty(company.getConsumption())));
        }
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.setCellValueFactory(new PropertyValueFactory("consumption"));
        table.setItems(data);
    }

    @FXML
    void countryT(ActionEvent event) {
        column1.setText("Country");
        column2.setText("Объем ежегодного потребления, т.");
        ObservableList<SimplePr> data = FXCollections.observableArrayList();
        for (Country country : provider.getCountries()) {
            data.add(new SimplePr(new SimpleStringProperty(country.getName()), new SimpleDoubleProperty(country.getConsumption())));
        }
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.setCellValueFactory(new PropertyValueFactory("consumption"));
        table.setItems(data);

    }

    @FXML
    void regionT(ActionEvent event) {
        column1.setText("Region");
        column2.setText("Объем ежегодного потребления, т.");
        ObservableList<SimplePr> data = FXCollections.observableArrayList();
        for (Region region : provider.getRegions()) {
            data.add(new SimplePr(new SimpleStringProperty(region.getName()), new SimpleDoubleProperty(region.getConsumption())));
        }
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.setCellValueFactory(new PropertyValueFactory("consumption"));
        table.setItems(data);

    }


    @FXML
    void importFile(ActionEvent event) {
        try {
            FileChooser chooser = new FileChooser();
            File json = chooser.showOpenDialog(null);
            parserJSON = new ParserJSON(json);
            btn.setDisable(false);
        } catch (Exception e) {
            error(e);
        }
    }

    @FXML
    void importBD(ActionEvent event) {
        if (parserJSON == null) {
            error("Import JSON");
        } else {
            try {
                FileChooser dbFC = new FileChooser();
                File db = dbFC.showOpenDialog(null);
                provider = new DBProvider();
                provider.connect(db);
                provider.setAll(parserJSON.getReactorArrayList());
                provider.close();
                calc = new Settter(provider);
                companyT(event);
            } catch (Exception e) {
                error(e);
            }
        }
    }
}
