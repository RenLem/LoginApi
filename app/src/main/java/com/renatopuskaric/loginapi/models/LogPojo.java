package com.renatopuskaric.loginapi.models;

import com.google.gson.annotations.SerializedName;

public class LogPojo {

   @SerializedName("username")
   private String username;

   @SerializedName("accessToken")
   private String accessToken;

   @SerializedName("refreshToken")
   private String refreshToken;

   public LogPojo() {
   }

   public LogPojo(String username, String accessToken, String refreshToken) {
      this.username = username;
      this.accessToken = accessToken;
      this.refreshToken = refreshToken;
   }

   public String getUsername() {
      return username;
   }

   public String getAccessToken() {
      return accessToken;
   }

   public String getRefreshToken() {
      return refreshToken;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setAccessToken(String accessToken) {
      this.accessToken = accessToken;
   }

   public void setRefreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
   }
}
