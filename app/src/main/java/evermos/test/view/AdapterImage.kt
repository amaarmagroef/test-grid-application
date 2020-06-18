package evermos.test.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import evermos.test.R
import evermos.test.databinding.ItemImageBinding
import evermos.test.model.ModelImage


/**
 * Created by siapaSAYA on 6/18/2020
 */


class AdapterImage() : RecyclerView.Adapter<AdapterImage.ItemViewHolder>() {

    private var data = mutableListOf<ModelImage.Result>()

    fun update(data: List<ModelImage.Result>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_image,
            parent,
            false)
        return ItemViewHolder(view)

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = data[position]
        holder.bind(model)
        holder.itemView.setOnClickListener {
            val data = BottomSheetDetail()
                .newInstance(model)
            data.show((it.context as AppCompatActivity).supportFragmentManager, "detailMovie")
        }
    }

    class ItemViewHolder(view: View) :
        BindableViewHolder<ItemImageBinding, ModelImage.Result>(view) {
        override fun bind(viewModel: ModelImage.Result) {
            binding?.model =viewModel
            ViewCompat.setTransitionName(binding!!.images, viewModel.posterPath)
        }
    }

    abstract class BindableViewHolder<out B : ViewDataBinding, in VM>(view: View) : RecyclerView.ViewHolder(view) {

        val binding: B? by lazy { DataBindingUtil.bind<B>(view) }

        abstract fun bind(viewModel: VM)

        fun setViewModel(viewModel: VM) {
            bind(viewModel)
            binding?.executePendingBindings()
        }

    }


}