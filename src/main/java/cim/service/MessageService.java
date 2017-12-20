package cim.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import cim.dao.CarOwnerDAO;
import cim.dao.InsuranceCompanyDAO;
import cim.dao.MaintenanceCompanyDAO;
import cim.dao.UserDAO;
import cim.entity.Message;
import cim.entity.UserInfo;

public class MessageService implements IMessageService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private CarOwnerDAO carOwnerDAO;
	@Autowired
	private InsuranceCompanyDAO insuranceCompanyDAO;
	@Autowired
	private MaintenanceCompanyDAO maintenanceCompanyDAO;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public Map<UserInfo, String> getMessageUser() {
		List<UserInfo> activeUsers=userDAO.getActiveUsers();
		Iterator itUserInfo = activeUsers.iterator();
		Map<UserInfo, String> messageMap = new HashMap<UserInfo,String>();
		while(itUserInfo.hasNext()) {
			UserInfo uinfo = (UserInfo) itUserInfo.next();
			switch(uinfo.getRole()) {
			
			case "ROLE_ADMIN":messageMap.put(uinfo, "ADMIN");break;
			case "ROLE_CAROWNER":messageMap.put(uinfo, (carOwnerDAO.getCarOwnerById(uinfo.getUserid()).getFirstName()+" "+
					carOwnerDAO.getCarOwnerById(uinfo.getUserid()).getLastName()));break;
			case "ROLE_INSURANCE":messageMap.put(uinfo, (insuranceCompanyDAO.getInsuranceCompany(uinfo.getUserid()).getInsuranceCompanyName()));break;
			case "ROLE_MAINTENANCE":messageMap.put(uinfo, (maintenanceCompanyDAO.getMaintenanceCompany(uinfo.getUserid()).getMaintenanceCompanyName()));break;
			
			}
			
		}
		
		return messageMap;
	}
	@Transactional
	public void addMessage(Message msg) {
		hibernateTemplate.saveOrUpdate(msg);
	}
	
	public List<Message> getMessagesForUser(UserInfo uinf){
		List<?> messageList = hibernateTemplate.find("FROM Message where messageToId=?", uinf.getUserid() );
		return (List<Message>)messageList;
	}
	public Message getMessageByMessageId(Long msgId) {
		List<?> messageList = hibernateTemplate.find("FROM Message where messageId=?", msgId );
		if(!messageList.isEmpty()) {
			return (Message)messageList.get(0);
		}
		return null;
	}
	@Transactional
	public void updateMessageStatus(Message msg) {
		hibernateTemplate.update(msg);
	}
	
	@Transactional
	public void deleteMessage(Message msg) {
		hibernateTemplate.delete(msg);
	}
}
