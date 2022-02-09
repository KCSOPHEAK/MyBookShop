package com.example.mybookshop

import com.google.gson.annotations.SerializedName

data class BookList(@SerializedName("@context")val context:String?,
                    @SerializedName("@id")val id:String?,
                    @SerializedName("@type")val type:String?,
                    @SerializedName("hydra:member")val data:List<Book>,
                    @SerializedName("hydra:totalItems")val total:Int?,
                    @SerializedName("hydra:view")val hView:hView,
                    @SerializedName("hydra:search")val hSearch:hSearch)

data class hSearch(@SerializedName("@type")val hType:String?,
                    @SerializedName("hydra:template")val hTemplate:String?,
                    @SerializedName("hydra:variableRepresentation")val hVarRepresentation:String?,
                    @SerializedName("hydra:mapping")val hMapping:List<hMapping>)

data class hMapping(@SerializedName("@type")val mType:String?,
                    @SerializedName("variable")val mVar:String?,
                    @SerializedName("property")val mProperty:String?,
                    @SerializedName("required")val mRequired:Boolean?)

data class hView(@SerializedName("@id")val hId:String?,
                @SerializedName("@type")val hType:String?,
                @SerializedName("hydra:first")val hFirst:String?,
                @SerializedName("hydra:last")val hLast:String?,
                @SerializedName("hydra:next")val hNext:String?)

data class Book(@SerializedName("@id")val id:String?,
                @SerializedName("@type") val type:String?,
                @SerializedName("isbn") val isbn:String?,
                @SerializedName("title") val title:String?,
                @SerializedName ("description") val description:String?,
                @SerializedName("author") val author:String?,
                @SerializedName("publicationDate") val publicationDate:String?,
                @SerializedName("reviews") val reviews:List<Review>)

data class Review(@SerializedName("@id")val rID:String?,
                  @SerializedName("@type")val rType:String?,
                  @SerializedName("body")val rBody:String?,
                @SerializedName("rating")val rating:Int?,
                @SerializedName("book")val rBook:String?,
                @SerializedName("publicationDate")val rPubDate:String?)