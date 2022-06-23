package com.assetmanagement.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_tbl")

public class User {

 @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "user_id")
private int userId;

 @NotNull(message ="UserName is required")
@Column(name = "user_name")
private String userName;

 @NotNull(message="Mobile number is required")
 @Column(name = "user_mobile_number")
private long mobileNumber;

 @NotNull(message ="Address is required")
@Column(name = "user_address")
private String address;
@NotNull(message ="EmailId is required")
@Column(name = "user_email_id")
private String emailId;

 @NotNull(message ="Password is required")
@Column(name = "user_password")
private String password;

 @NotNull(message ="UserPosition is required")
@Column(name = "user_position")
private String userPosition;

@JsonIgnore
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
// @JoinColumn(name="order_id")
private Set<Order> getListOfOrders = new HashSet<>();

public int getUserId() {
return userId;
}

 public void setUserId(int userId) {
this.userId = userId;
}

 public String getUserName() {
return userName;
}

 public void setUserName(String userName) {
this.userName = userName;
}

 public long getMobileNumber() {
return mobileNumber;
}

 public void setMobileNumber(long mobileNumber) {
this.mobileNumber = mobileNumber;
}

 public String getAddress() {
return address;
}

 public void setAddress(String address) {
this.address = address;
}

 public Set<Order> getGetListOfOrders() {
return getListOfOrders;
}

 public void setGetListOfOrders(Set<Order> getListOfOrders) {
this.getListOfOrders = getListOfOrders;
}

 public String getEmailId() {
return emailId;
}

 public void setEmailId(String emailId) {
this.emailId = emailId;
}

 public String getPassword() {
return password;
}

 public void setPassword(String password) {
this.password = password;
}

 public String getUserPosition() {
return userPosition;
}

 public void setUserPosition(String userPosition) {
this.userPosition = userPosition;
}


}