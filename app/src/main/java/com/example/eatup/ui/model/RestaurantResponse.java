package com.example.eatup.ui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantResponse{

	@SerializedName("restaurant")
	private List<RestaurantItem> restaurant;

	public List<RestaurantItem> getRestaurant(){
		return restaurant;
	}
}