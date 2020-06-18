package evermos.test.repo

import evermos.test.model.ModelImage
import io.reactivex.rxjava3.core.Maybe
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by siapaSAYA on 6/10/2020
 */

interface ApiRoute {
    @GET("upcoming")
    fun getUpcoming(@Query("api_key") apiKey: String,
                    @Query("page") page : Int): Maybe<ModelImage>
    @GET("popular")
    fun getPopular(@Query("api_key") apiKey: String,
                    @Query("page") page : Int): Maybe<ModelImage>
    @GET("top_rated")
    fun getTopRated(@Query("api_key") apiKey: String,
                    @Query("page") page : Int): Maybe<ModelImage>
}