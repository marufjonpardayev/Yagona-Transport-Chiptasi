package uz.transport.yagonatransportchiptasi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.ui.activity.FromActivity

class FromAdapter(var regions: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var mlistener: RecyclerClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_region_layout, parent, false)
        return FromViewHolder(view,mlistener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val region = regions[position]
        if (holder is FromViewHolder) {

            val tv_region = holder.tv_region
            tv_region.text = region


        }
    }

    override fun getItemCount(): Int {
        return regions.size
    }

    inner class FromViewHolder(view: View,listener:RecyclerClickListener) : RecyclerView.ViewHolder(view) {

        var tv_region: TextView = view.findViewById(R.id.tv_region)

        init {
            view.setOnClickListener {
                listener.onClick(absoluteAdapterPosition)
            }
        }




    }
     interface RecyclerClickListener{
        fun onClick( position: Int)

    }
    fun setOnItemClickListener(listener:RecyclerClickListener){
        mlistener =listener
    }


}