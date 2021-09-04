package com.iridiumbyte.poc.chat.server.user;

import com.iridiumbyte.poc.chat.server.user.ChatUser.Username;

public interface ActiveUserDao {

	ChatUser save(ChatUser chatUser);

	ChatUser getByName(Username username);

	void deleteByName(Username username);

}
