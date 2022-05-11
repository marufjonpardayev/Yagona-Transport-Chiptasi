package uz.transport.yagonatransportchiptasi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.ItemTrainAfrosiyobBinding
import uz.transport.yagonatransportchiptasi.databinding.ItemTrainPopularBinding
import uz.transport.yagonatransportchiptasi.databinding.ItemTrainSharqBinding
import uz.transport.yagonatransportchiptasi.databinding.ItemTrainSpecialBinding
import uz.transport.yagonatransportchiptasi.model.Train
import uz.transport.yagonatransportchiptasi.ui.fragment.TrainDetailsFragment


class TrainAdapter(
    val fragment: TrainDetailsFragment,
    val items: ArrayList<Train>,
    private var onItemClicked: ((Int) -> Unit)
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_ITEM_SHARQ = 1
    private val TYPE_ITEM_POPULAR = 2
    private val TYPE_ITEM_SPECIAL = 3
    private val TYPE_ITEM_AFROSIYOB = 4

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return if (item.count == TYPE_ITEM_SHARQ) {
            TYPE_ITEM_SHARQ
        } else if (item.count == TYPE_ITEM_POPULAR) {
            TYPE_ITEM_POPULAR
        } else if (item.count == TYPE_ITEM_AFROSIYOB) {
            TYPE_ITEM_AFROSIYOB
        } else {
            TYPE_ITEM_SPECIAL
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_SHARQ) {
            val view =
                ItemTrainSharqBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SharqViewHolder(view)
        }
        if (viewType == TYPE_ITEM_POPULAR) {
            val view =
                ItemTrainPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PopularViewHolder(view)
        }

        if (viewType == TYPE_ITEM_AFROSIYOB) {
            val view =
                ItemTrainAfrosiyobBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return AfrosiyobViewHolder(view)
        }

        val view =
            ItemTrainSpecialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpecialViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is SharqViewHolder) {
            holder.view.llSharq.setOnClickListener {
                onItemClicked.invoke(1)
            }
        }

        if (holder is PopularViewHolder) {
            holder.view.llPopular.setOnClickListener {
                onItemClicked.invoke(2)
            }
        }

        if (holder is SpecialViewHolder) {
            holder.view.llSpecial.setOnClickListener {
                onItemClicked.invoke(3)
            }
        }

        if (holder is AfrosiyobViewHolder) {
            holder.view.llAfrosiyob.setOnClickListener {
                onItemClicked.invoke(4)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class SharqViewHolder(val view: ItemTrainSharqBinding) :
        RecyclerView.ViewHolder(view.root)

    class PopularViewHolder(val view: ItemTrainPopularBinding) :
        RecyclerView.ViewHolder(view.root)

    class SpecialViewHolder(val view: ItemTrainSpecialBinding) :
        RecyclerView.ViewHolder(view.root)

    class AfrosiyobViewHolder(val view: ItemTrainAfrosiyobBinding) :
        RecyclerView.ViewHolder(view.root)
}