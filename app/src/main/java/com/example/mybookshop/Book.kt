package com.example.mybookshop

import com.google.gson.annotations.SerializedName

data class Book(@SerializedName("isbn") val isbn:String?,
                @SerializedName("title") val title:String?,
                @SerializedName ("description") val description:String?,
                @SerializedName("author") val author:String?,
                @SerializedName("publicationDate") val publicationDate:String?,
                @SerializedName("reviews") val reviews:List<Review>)
data class Review(val body:String?)