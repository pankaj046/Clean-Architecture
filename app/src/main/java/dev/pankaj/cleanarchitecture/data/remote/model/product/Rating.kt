package dev.pankaj.cleanarchitecture.data.remote.model.product

import com.google.gson.annotations.SerializedName


data class Rating (
  @SerializedName("rate")
  var rate  : Double? = null,
  @SerializedName("count")
  var count : Int?    = null
)