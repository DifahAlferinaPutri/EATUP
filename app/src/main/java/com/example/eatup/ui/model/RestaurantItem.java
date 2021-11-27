package com.example.eatup.ui.model;

import com.google.gson.annotations.SerializedName;

public class RestaurantItem{

	@SerializedName("restaurant_id")
	private int restaurant_id;

	@SerializedName("type_name")
	private String typeName;

	@SerializedName("address")
	private String address;

	@SerializedName("rating")
	private Double rating;

	@SerializedName("avatar")
	private String avatar;

	@SerializedName("restaurant_name")
	private String restaurantName;

	@SerializedName("status")
	private int status;

	public int getId(){
		return restaurant_id;
	}

	public String getTypeName(){
		return typeName;
	}

	public String getAddress(){
		return address;
	}

	public Double getRating(){
		return rating;
	}

	public String getAvatar(){
		return avatar;
	}

	public String getRestaurantName(){
		return restaurantName;
	}

	public int getStatus(){
		return status;
	}
}