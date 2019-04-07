package org.pandec.applicationmeal

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("meals")
	val meals: List<MealsItem>? = null
)