package uz.transport.yagonatransportchiptasi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.model.Train
import uz.transport.yagonatransportchiptasi.ui.fragment.TrainDetailsFragment


class TrainAdapter(val fragment: TrainDetailsFragment, val items: ArrayList<Train>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_ITEM_SHARQ = 1
    private val TYPE_ITEM_POPULAR = 2
    private val TYPE_ITEM_SPECIAL = 3
    private val TYPE_ITEM_AFROSIYOB = 4

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return if (item.count == TYPE_ITEM_SHARQ){
            TYPE_ITEM_SHARQ
        }else if (item.count == TYPE_ITEM_POPULAR) {
            TYPE_ITEM_POPULAR
        }else if (item.count == TYPE_ITEM_AFROSIYOB){
            TYPE_ITEM_AFROSIYOB
        }else{
            TYPE_ITEM_SPECIAL
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_SHARQ){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_train_sharq, parent, false)
            return SharqViewHolder(view)
        }
        if (viewType == TYPE_ITEM_POPULAR){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_train_popular, parent, false)
            return PopularViewHolder(view)
        }

        if (viewType == TYPE_ITEM_AFROSIYOB){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_train_afrosiyob, parent, false)
            return AfrosiyobViewHolder(view)
        }

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_train_special, parent, false)
        return SpecialViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is SharqViewHolder){
            val ll_sharq = holder.ll_sharq
            fragment.openTicket(1)
        }

        if (holder is PopularViewHolder){
            val ll_popular = holder.ll_popular
            fragment.openTicket(2)
        }

        if (holder is SpecialViewHolder){
            val ll_special = holder.ll_special
            fragment.openTicket(3)
        }

        if (holder is AfrosiyobViewHolder){
            val ll_afrosyob = holder.ll_afrosiyob
            fragment.openTicket(4)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class SharqViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ll_sharq: LinearLayout

        init {
            ll_sharq = view.findViewById(R.id.ll_sharq)
        }
    }

    class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ll_popular: LinearLayout

        init {
            ll_popular = view.findViewById(R.id.ll_popular)
        }
    }

    class SpecialViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ll_special: LinearLayout

        init {
            ll_special = view.findViewById(R.id.ll_special)
        }
    }

    class AfrosiyobViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ll_afrosiyob: LinearLayout

        init {
            ll_afrosiyob = view.findViewById(R.id.ll_afrosiyob)
        }
    }
}