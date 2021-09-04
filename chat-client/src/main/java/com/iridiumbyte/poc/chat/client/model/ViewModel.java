package com.iridiumbyte.poc.chat.client.model;

import com.iridiumbyte.poc.chat.api.server.ServerMessage;
import com.iridiumbyte.poc.chat.client.client.ChatClient;
import com.iridiumbyte.poc.chat.client.socket.ConnectionProperties;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeFormatter;

public class ViewModel {

	private static final Logger log = LoggerFactory.getLogger(ViewModel.class);

	private static final ConnectionProperties CONNECTION_PROPERTIES = new ConnectionProperties(
			"ws://localhost:8080/chat",
			"mn-secret"
	);
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	private final ObservableList<String> currentMessages = FXCollections.observableArrayList();
	private final ObservableList<ChatRoom> rooms = FXCollections.observableArrayList();
	private final ObjectProperty<ChatRoom> currentRoom = new SimpleObjectProperty<>();
	private ChatClient chatClient;

	public void connect() {
		chatClient = new ChatClient(CONNECTION_PROPERTIES, this::acceptMessage);
		currentRoom.addListener((observable, oldValue, newValue) -> refreshCurrentMessages(newValue));
		fetchRooms();

		if (!rooms.isEmpty()) {
			currentRoom.set(rooms.get(0));
		}
	}

	public ObservableList<ChatRoom> getRooms() {
		return rooms;
	}

	public ObjectProperty<ChatRoom> getCurrentRoom() {
		return currentRoom;
	}

	public ObservableList<String> getCurrentMessages() {
		return currentMessages;
	}

	public void sendMessage(ChatRoom room, String msgContent) {
		if (!room.isJoined()) {
			log.info("Joining room");
			chatClient.joinRoom(room.getName());
			room.setJoined(true);
		}

		chatClient.sendMessage(room.getType(), room.getName(), msgContent);
	}

	public void joinRoom(ChatRoom room) {
		rooms.stream()
				.filter(r -> r.equals(room))
				.findFirst()
				.ifPresentOrElse(this::setCurrentRoom, () -> createNewRoom(room));
	}

	public void createNewRoom(ChatRoom room) {
		chatClient.joinRoom(room.getName());
		rooms.add(room);
		currentRoom.set(room);
	}

	public void setCurrentRoom(ChatRoom room) {
		currentRoom.set(room);
		refreshCurrentMessages(room);
	}

	private void acceptMessage(ServerMessage serverMessage) {
		rooms.stream()
				.filter(room -> room.getName().equals(serverMessage.channelId.channelName))
				.findFirst()
				.ifPresent(room -> {
					room.getMessages().add(formatMessage(serverMessage));
					if (room.equals(currentRoom.get())) {
						refreshCurrentMessages(room);
					}
				});
	}

	private void fetchRooms() {
		rooms.setAll(chatClient.fetchRoomList());
	}

	private void refreshCurrentMessages(ChatRoom room) {
		currentMessages.setAll(room.getMessages());
	}

	private String formatMessage(ServerMessage msg) {
		System.out.println(msg.creationTime);
		return String.format(
				"[%s] %s: %s",
				msg.creationTime.format(DATE_TIME_FORMATTER),
				msg.author,
				msg.content
		);
	}

}
