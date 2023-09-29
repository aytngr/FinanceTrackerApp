package gr.aytn.pocket.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gr.aytn.pocket.AddCategoryListener
import gr.aytn.pocket.CategoryOnClickListener
import gr.aytn.pocket.databinding.AddCategoryLayoutBinding
import gr.aytn.pocket.databinding.CategoryLayoutBinding
import gr.aytn.pocket.model.Category

class CategoryAdapter(val listener: AddCategoryListener, val categoryListener: CategoryOnClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList: List<Category> = listOf()
    private val FOOTER_VIEW = 1

    fun addData(list: List<Category>){
        dataList = list
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: CategoryLayoutBinding): RecyclerView.ViewHolder(binding.root){}
    class FooterViewHolder(val binding: AddCategoryLayoutBinding): RecyclerView.ViewHolder(binding.root){}

    override fun getItemCount(): Int {
        return if (dataList.size == 0) 1
        else dataList.size + 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == FOOTER_VIEW){
            val binding = AddCategoryLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return FooterViewHolder(binding)
        }
        val binding = CategoryLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is ViewHolder){
            val item = dataList[position]
            holder.binding.icon.setBackgroundResource(item.icon)
            holder.binding.name.text = item.name
            holder.binding.price.text = "${item.price} AZN"
            holder.itemView.setOnClickListener {
                categoryListener.onCategoryClick()
            }
        }else if(holder is FooterViewHolder){
            holder.itemView.setOnClickListener {
                listener.addCategory()
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        if(position == dataList.size) return FOOTER_VIEW
        return super.getItemViewType(position)

    }

}