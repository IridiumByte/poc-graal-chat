package com.iridiumbyte.poc.chat.client.model;

import com.iridiumbyte.poc.chat.api.ChannelType;
import com.iridiumbyte.poc.chat.api.MessageDto;
import com.iridiumbyte.poc.chat.client.client.ChatClient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ViewModel {

	private final ObservableList<String> currentMessages = FXCollections.observableArrayList();
	private final ObservableList<ChatRoom> rooms = FXCollections.observableArrayList();
	private final ObjectProperty<ChatRoom> currentRoom = new SimpleObjectProperty<>();
	private ChatClient chatClient;

	public void connect() {
		chatClient = new ChatClient("ws://localhost:8080/chat", this::acceptMessage);
		currentRoom.addListener((observable, oldValue, newValue) -> currentMessages.setAll(newValue.getMessages()));
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
	}

	private void acceptMessage(MessageDto messageDto) {
		// TODO: 28.08.2021 type of message will change
		rooms.stream()
				.filter(room -> room.getName().equals(messageDto.target))
				.findFirst()
				.ifPresent(room -> {
					room.getMessages().add(messageDto.content);
					if (room.equals(currentRoom.get())) {
						currentMessages.setAll(room.getMessages());
					}
				});
	}

	private void fetchRooms() {
		rooms.add(new ChatRoom(ChannelType.ROOM, "Room A"));
		rooms.add(new ChatRoom(ChannelType.ROOM, "Room B"));

		rooms.get(0).getMessages().addAll(
				List.of(
						"Room A: 1",
						"Room A: 2",
						"Room A: 3"
				)
		);
		rooms.get(1).getMessages().addAll(
				List.of(
						"Room B: 1",
						"Room B: 2",
						"Room B: 3"
				)
		);
	}

}
