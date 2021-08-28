package com.iridiumbyte.poc.chat.client;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Runner extends Application {

	private ChatClient chatClient;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("PoC GraalVM Chat");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(25);
		grid.setVgap(25);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Scene scene = new Scene(grid, 800, 600);
		stage.setScene(scene);

		TextArea textArea = new TextArea();
		grid.add(textArea, 0, 0);
		textArea.setEditable(false);

		TextField messageField = new TextField();
		grid.add(messageField, 0, 1);
		messageField.setOnAction(actionEvent -> {
			textArea.appendText(messageField.getText() + "\n");
			messageField.setText("");
		});

		ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList("A"));
		comboBox.setEditable(true);
		comboBox.setOnAction(actionEvent -> {
			System.out.println("Something happened");
			String selectedRoom = comboBox.getSelectionModel().getSelectedItem();
			System.out.println(selectedRoom);

			String value = comboBox.getValue();
			System.out.println("Value: " + value);
			System.out.println("Room: " + selectedRoom);
		});
		grid.add(comboBox, 0, 2);

		stage.show();
		stage.requestFocus();

		chatClient = new ChatClient("ws://localhost:8080/chat", "bane");
	}

}
