package evermos.test.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import evermos.test.R
import evermos.test.model.ModelKategori
import kotlinx.android.synthetic.main.item_kategori.view.*

/**
 * Created by siapaSAYA on 6/18/2020
 */


class AdapterKategori() : RecyclerView.Adapter<AdapterKategori.ItemViewHolder>() {

    private var data = mutableListOf<ModelKategori>()
    var mOnClickCategory : OnClickCategory? = null

    interface OnClickCategory{
        fun onUpdate(pos : Int)
    }

    fun setOnClickCategory(listener : OnClickCategory){
        mOnClickCategory = listener
    }

    fun update(data: List<ModelKategori>) {
        clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_kategori,
            parent,
            false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = data[position]
        holder.itemView.textkategori.text = model.name
        if(!model.isselected!!){
            holder.itemView.container.setBackgroundResource(R.drawable.background_chip)
        } else {
            holder.itemView.container.setBackgroundColor(Color.parseColor("#ffffff"))
        }
        holder.itemView.container.setOnClickListener {
            if(model.isselected!!) return@setOnClickListener
            for (item in data){
                item.isselected = item.name!! == model.name
            }
            notifyDataSetChanged()

            mOnClickCategory?.onUpdate(position)
        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }


}