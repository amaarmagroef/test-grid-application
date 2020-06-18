package evermos.test.model

import com.google.gson.annotations.SerializedName


/**
 * Created by siapaSAYA on 6/18/2020
 */

class ModelKategori {
    @SerializedName("name")
    var name : String? = null
    @SerializedName("isselected")
    var isselected : Boolean? = true

    constructor(name: String?, isselected: Boolean?) {
        this.name = name
        this.isselected = isselected
    }
}