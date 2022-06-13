package com.renatopuskaric.loginapi.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class User {

   @PrimaryKey
   @NonNull
   @SerializedName("id")
   private float id;

   @SerializedName("name")
   @ColumnInfo(name = "name")
   private String name;

   @SerializedName("surname")
   @ColumnInfo(name = "surname")
   private String surname;

   @SerializedName("fullName")
   @ColumnInfo(name = "fullName")
   private String fullName;

   @SerializedName("imageId")
   @ColumnInfo(name = "imageId")
   private float imageId;

   @SerializedName("address")
   @ColumnInfo(name = "address")
   private String address;

   @SerializedName("phoneNumber")
   @ColumnInfo(name = "phoneNumber")
   private String phoneNumber;

   @SerializedName("oib")
   @ColumnInfo(name = "oib")
   private String oib;

   @SerializedName("email")
   @ColumnInfo(name = "email")
   private String email;

   @SerializedName("statusId")
   @ColumnInfo(name = "statusId")
   private float statusId;

   public User(float id) {
      this.id = id;
   }

   // Getter Methods

   public float getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getSurname() {
      return surname;
   }

   public String getFullName() {
      return fullName;
   }

   public float getImageId() {
      return imageId;
   }

   public String getAddress() {
      return address;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public String getOib() {
      return oib;
   }

   public String getEmail() {
      return email;
   }

   public float getStatusId() {
      return statusId;
   }

   // Setter Methods

   public void setId(float id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public void setImageId(float imageId) {
      this.imageId = imageId;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public void setOib(String oib) {
      this.oib = oib;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setStatusId(float statusId) {
      this.statusId = statusId;
   }

   @Override
   public String toString() {
      return "User{" + "\r\n" +
              "id=" + id + "\r\n" +
              ", name='" + name + "\r\n" +
              ", surname='" + surname + "\r\n" +
              ", fullName='" + fullName + "\r\n" +
              ", imageId=" + imageId + "\r\n" +
              ", address='" + address + "\r\n" +
              ", phoneNumber='" + phoneNumber + "\r\n" +
              ", oib='" + oib + "\r\n" +
              ", email='" + email + "\r\n" +
              ", statusId=" + statusId + "\r\n" +
              '}';
   }
}
