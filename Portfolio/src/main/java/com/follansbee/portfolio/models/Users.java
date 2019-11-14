package com.follansbee.portfolio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {

        @Id
        private String employeeId;
        private String firstName;
        private String lastName;
        private String address1;
        private String address2;
        private String city;
        private String state;
        private String zipCode;
        private String phoneNumber;
        private String email;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
        private List<TradeOrder> tradeOrders;

        @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
        @JsonIgnoreProperties("users")
        private List<UserGroup> groupList = new ArrayList<>();


        public void addGroup(UserGroup group) {
                this.groupList.add(group);
        }

        public String getEmployeeId() {
                return employeeId;
        }


        public void setEmployeeId(String employeeId) {
                this.employeeId = employeeId;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getAddress1() {
                return address1;
        }

        public void setAddress1(String address1) {
                this.address1 = address1;
        }

        public String getAddress2() {
                return address2;
        }

        public void setAddress2(String address2) {
                this.address2 = address2;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }

        public String getZipCode() {
                return zipCode;
        }

        public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public List<UserGroup> getGroupList() {
                return groupList;
        }

        public void setGroupList(List<UserGroup> groupList) {
          this.groupList = groupList;
        }

        public List<TradeOrder> getTradeOrders() {
                return tradeOrders;
        }

        public void setTradeOrders(List<TradeOrder> tradeOrders) {
                this.tradeOrders = tradeOrders;
        }

        @Override
        public String toString() {
                return "Users{" +
                        "employeeId='" + employeeId + '\'' +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", address1='" + address1 + '\'' +
                        ", address2='" + address2 + '\'' +
                        ", city='" + city + '\'' +
                        ", state='" + state + '\'' +
                        ", zipCode='" + zipCode + '\'' +
                        ", phoneNumber='" + phoneNumber + '\'' +
                        ", email='" + email + '\'' +
                        ", tradeOrders=" + tradeOrders +
                        ", groupList=" + groupList +
                        '}';
        }
}
