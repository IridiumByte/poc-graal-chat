package com.iridiumbyte.poc.chat.client;

import com.iridiumbyte.poc.chat.api.ChannelType;
import com.iridiumbyte.poc.chat.client.model.ChatRoom;
import com.iridiumbyte.poc.chat.client.model.ViewModel;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.Optional;

public class ChatApplication extends Application {

	private final ViewModel viewModel = new ViewModel();

	private TextArea chatArea;
	private TextField messageField;
	private ComboBox<ChatRoom> roomSelector;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		buildAndShowUI(stage);
		viewModel.connect();
		roomSelector.setItems(viewModel.getRooms());
		viewModel.getRooms().addListener((ListChangeListener<ChatRoom>) c -> System.out.println("rooms changed"));
	}

	private void buildAndShowUI(Stage stage) {
		stage.setTitle("PoC GraalVM Chat");

		GridPane grid = setupGrid(stage);
		setupChatTextArea(grid);
		setupMessageField(grid);
		setupRoomSelector(grid);

		stage.show();
		stage.requestFocus();
	}

	private GridPane setupGrid(Stage stage) {
		GridPane grid = new GridPane();

		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(25);
		grid.setVgap(25);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Scene scene = new Scene(grid, 800, 600);
		stage.setScene(scene);

		return grid;
	}

	private void setupChatTextArea(GridPane grid) {
		chatArea = new TextArea();
		grid.add(chatArea, 0, 0);
		viewModel.getCurrentMessages().addListener((ListChangeListener<String>) change -> {
			String messages = String.join("\n", change.getList());
			chatArea.setText(messages);
		});
		chatArea.setEditable(false);
	}

	private void setupMessageField(GridPane grid) {
		messageField = new TextField();
		grid.add(messageField, 0, 1);
		messageField.setOnAction(actionEvent -> {
			String msgContent = messageField.getText();
			viewModel.sendMessage(roomSelector.getSelectionModel().getSelectedItem(), msgContent);
			messageField.setText("");
		});
	}

	private void setupRoomSelector(GridPane grid) {
		roomSelector = new ComboBox<>();
		grid.add(roomSelector, 0, 2);
		roomSelector.setEditable(true);
		roomSelector.setConverter(new StringConverter<>() {
			@Override
			public String toString(ChatRoom room) {
				if (room == null) {
					return null;
				}
				return room.getName();
			}

			@Override
			public ChatRoom fromString(String value) {
				Optional<ChatRoom> existingRoom = roomSelector.getItems().stream()
						.filter(room -> room.getName().equals(value))
						.findFirst();

				return existingRoom.orElseGet(() -> new ChatRoom(ChannelType.ROOM, value));

			}
		});

		viewModel.getCurrentRoom()
				.addListener((observable, oldValue, newValue) -> roomSelector.getSelectionModel().select(newValue));

		roomSelector.setOnKeyPressed(event -> {
			if (event.getCode() != KeyCode.ENTER) {
				return;
			}

			ChatRoom chatRoom = roomSelector.getConverter().fromString(roomSelector.getEditor().getText());
			viewModel.joinRoom(chatRoom);

			if (roomSelector.getSelectionModel().getSelectedIndex() == -1) {
				viewModel.createNewRoom(roomSelector.getSelectionModel().getSelectedItem());
			} else {
				viewModel.setCurrentRoom(roomSelector.getSelectionModel().getSelectedItem());
			}
		});

		roomSelector.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> viewModel.setCurrentRoom(newValue));
	}

}
