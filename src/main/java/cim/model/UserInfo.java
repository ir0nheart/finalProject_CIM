package cim.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserInfo{
	
private long userid;

@NotEmpty(message="Username Cannot be empty")
@Size(min=5,max=45)
private String username;

@NotEmpty(message="Password Cannot be empty")
@Size(min=8,max=45)
private String password;

@NotEmpty(message = "E-mail cannot be emtpy!")
@Size(max=45)
@Email
private String email;

private String role;
private boolean active;

public long getUserid() {
	return userid;
}
public void setUserid(long userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	if(role.equals("Car Owner")) this.role="ROLE_CAROWNER";
	if(role.equals("Insurance Company")) this.role="ROLE_INSURANCE";
	if(role.equals("Maintenance Company")) this.role="ROLE_MAINTENANCE";
}
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}

	
}
