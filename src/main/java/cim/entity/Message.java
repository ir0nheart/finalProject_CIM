package cim.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="messages")
public class Message implements Serializable {
	
private static final long serialVersionUID = -5199557537146670920L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="messageId")
private long messageId;

@Column(name="messageFromId")	
private long messageFromId;

@Column(name="messageToId")	
private long messageToId;
@NotEmpty
@Column(name="messageTitle")	
private String messageTitle;
@NotEmpty
@Column(name="messageBody")	
private String messageBody;
@Column(name="messageDate")
private String messageDate;

public String getMessageTitle() {
	return messageTitle;
}
public void setMessageTitle(String messageTitle) {
	this.messageTitle = messageTitle;
}
public String getMessageDate() {
	return messageDate;
}
public void setMessageDate(String messageDate) {
	this.messageDate = messageDate;
}
public boolean isMessageIsRead() {
	return messageIsRead;
}
public void setMessageIsRead(boolean messageIsRead) {
	this.messageIsRead = messageIsRead;
}
@Column(name="readStatus",nullable=false,columnDefinition = "tinyint default false")
private boolean messageIsRead;

public long getMessageId() {
	return messageId;
}
public void setMessageId(long messageId) {
	this.messageId = messageId;
}
public long getMessageFromId() {
	return messageFromId;
}
public void setMessageFromId(long messageFromId) {
	this.messageFromId = messageFromId;
}
public long getMessageToId() {
	return messageToId;
}
public void setMessageToId(long messageToId) {
	this.messageToId = messageToId;
}

public String getMessageBody() {
	return messageBody;
}
public void setMessageBody(String messageBody) {
	this.messageBody = messageBody;
}



}
