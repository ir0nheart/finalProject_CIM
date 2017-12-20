package cim.service;

import java.util.List;
import java.util.Map;

import cim.entity.Message;
import cim.entity.UserInfo;

public interface IMessageService {
	
	public Map<UserInfo,String> getMessageUser();
	public void addMessage(Message msg);
	public List<Message> getMessagesForUser(UserInfo uinf);
	public Message getMessageByMessageId(Long msgId);
	public void updateMessageStatus(Message msg);
	public void deleteMessage(Message msg);
}
