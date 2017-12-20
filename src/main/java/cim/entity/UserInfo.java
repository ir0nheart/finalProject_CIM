package cim.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="userTable")
public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3534958066885875705L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private long userid;
	@Column(name="username",nullable=false,unique = true)
	private String username;
	@Column(name="password",nullable=false,length=100)
	private String password;
	@Column(name="email",nullable=false)
	private String email;
	@Column(name="role",nullable=false)
	private String role;
	@Column(name="active",nullable=false,columnDefinition = "tinyint default false")
	private boolean active;
	
	

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
		this.role = role;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	public UserInfo() {
    }
 
     
    public UserInfo(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.active = false;
    }
	
	

}
