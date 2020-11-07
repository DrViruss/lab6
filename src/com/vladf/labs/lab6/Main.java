package com.vladf.labs.lab6;

import com.vladf.labs.lab6.data.Person;
import com.vladf.labs.lab6.utils.Requester;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

import javafx.scene.control.MenuItem;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;

public class Main extends Application {
    private TableView table = new TableView();

    @Override
    public void start(Stage stage) throws Exception{
        Scene scene = new Scene(new Group());
        stage.setTitle("People In Space!");
        stage.setWidth(420);
        stage.setHeight(670);

        final Label label = new Label("Now in space:");
        label.setFont(new Font("Arial", 20));

        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setPrefWidth(185);
        nameColumn.setCellValueFactory( new PropertyValueFactory<Person, String>("name"));
        TableColumn craftColumn = new TableColumn("Craft");
        craftColumn.setPrefWidth(185);
        craftColumn.setCellValueFactory( new PropertyValueFactory<Person, String>("craft"));

        Requester requester = new Requester();
        requester.sendRequest();


        table.setPrefSize(400,600);
        table.setEditable(true);
        table.setItems(FXCollections.observableArrayList(requester.getPeople()));
        table.getColumns().setAll(nameColumn,craftColumn);

        ContextMenu menu = new ContextMenu();
        MenuItem goName = new MenuItem("Who is this?");
        MenuItem goCraft = new MenuItem("What is this?");
        goName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person currentPerson = (Person) table.getSelectionModel().getSelectedItem();
                try {
                    Desktop.getDesktop().browse(URI.create("https://www.google.com/search?q=" + (currentPerson.getName()).replace(' ','+')));
                } catch (IOException ev) {
                    ev.printStackTrace();    //ERROR WINDOW
                }
            }
        });
        goCraft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person currentPerson = (Person) table.getSelectionModel().getSelectedItem();
                try {
                    Desktop.getDesktop().browse(URI.create("https://www.google.com/search?q=" + (currentPerson.getCraft()).replace(' ','+')));
                } catch (IOException ev) {
                    ev.printStackTrace();    //ERROR WINDOW
                }
            }
        });
        menu.getItems().addAll(goName,goCraft);
        table.setContextMenu(menu);

        final VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
