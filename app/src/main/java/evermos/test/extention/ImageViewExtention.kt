package evermos.test.extention

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import evermos.test.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


/**
 * Created by siapaSAYA on 6/18/2020
 */


@BindingAdapter("sourceThumb")
fun ImageView.sourceThumb(path: String?) {
    path?.also {
        val thumbBigger = BuildConfig.IMAGE_BASE_URL + "w500" + it
        Glide.with(this)
            .load(thumbBigger)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(0.05F)
            .into(this)
    }
}

@BindingAdapter("sourceOriginal")
fun ImageView.sourceOriginal(path: String?) {
    path?.also {
        val thumb = BuildConfig.IMAGE_BASE_URL + "w200" + it
        val original = BuildConfig.IMAGE_BASE_URL + "original" + it
        Glide.with(this)
            .load(original)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(Glide.with(this).load(thumb))
            .into(this)
    }
}