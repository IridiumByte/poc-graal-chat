package com.iridiumbyte.poc.chat.client.model;

import com.iridiumbyte.poc.chat.api.ChannelType;
import com.iridiumbyte.poc.chat.api.MessageDto;
import com.iridiumbyte.poc.chat.client.client.ChatClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ViewModel {

	private final ChatClient chatClient;
	private final ObservableList<String> currentMessages = FXCollections.observableArrayList();
	private final ObservableList<ChatRoom> rooms = FXCollections.observableArrayList();
	private ChatRoom currentRoom;

	public ViewModel() {
		this.chatClient = new ChatClient("ws://localhost:8080/chat", this::acceptMessage);
		fetchRooms();
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

	public ObservableList<ChatRoom> getRooms() {
		return rooms;
	}

	public ObservableList<String> getCurrentMessages() {
		return currentMessages;
	}

	public void sendMessage(ChatRoom room, String msgContent) {
		chatClient.sendMessage(room.getType(), room.getName(), msgContent);
	}

	public void createNewRoom(ChatRoom room) {
		chatClient.joinRoom(room.getName());
		rooms.add(room);
		setCurrentRoom(room);
	}

	public void setCurrentRoom(ChatRoom currentRoom) {
		this.currentRoom = currentRoom;
		currentMessages.setAll(currentRoom.getMessages());
	}

	private void acceptMessage(MessageDto messageDto) {
		// TODO: 28.08.2021 type of message will change
		rooms.stream()
				.filter(room -> room.getName().equals(messageDto.target))
				.findFirst()
				.ifPresent(room -> {
					room.getMessages().add(messageDto.content);
					if (currentRoom.equals(room)) {
						currentMessages.setAll(room.getMessages());
					}
				});
	}

	private void fetchRooms() {
		rooms.add(new ChatRoom(ChannelType.ROOM, "Room A"));
		rooms.add(new ChatRoom(ChannelType.ROOM, "Room B"));
	}

}
