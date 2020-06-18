package evermos.test.model

import com.google.gson.annotations.SerializedName
import dagger.Module

/**
 * Created by siapaSAYA on 6/18/2020
 */

@Module
class ModelImage {

    @SerializedName("results")
    var results: List<Result>? = null

    @SerializedName("page")
    var page: Int? = null

    @SerializedName("total_results")
    var totalResults: Int? = null

    @SerializedName("total_pages")
    var totalPages: Int? = null

    class Result {
        @SerializedName("poster_path")
        var posterPath: String? = null
        @SerializedName("title")
        var title: String? = null

        constructor(posterPath: String?, title: String?) {
            this.posterPath = posterPath
            this.title = title
        }
    }
}
