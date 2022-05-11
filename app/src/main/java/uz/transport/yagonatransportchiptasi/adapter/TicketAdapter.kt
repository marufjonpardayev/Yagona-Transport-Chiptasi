package uz.transport.yagonatransportchiptasi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.databinding.*
import uz.transport.yagonatransportchiptasi.extensions.Extensions.setRandomWagonNumber
import uz.transport.yagonatransportchiptasi.model.Ticket
import uz.transport.yagonatransportchiptasi.ui.fragment.TicketsFragment
import kotlin.random.Random


class TicketAdapter(
    val fragment: TicketsFragment,
    val items: ArrayList<Ticket>,
    private var onItemClicked: ((Int) -> Unit),
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM_BIZNES = 1
    private val TYPE_ITEM_EKONOM = 2
    private val TYPE_ITEM_PLATSKART = 3
    private val TYPE_ITEM_KUPE = 4
    private val TYPE_ITEM_LYUKS = 5
    private val TYPE_ITEM_VIP = 6

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return if (item.type == TYPE_ITEM_BIZNES) {
            TYPE_ITEM_BIZNES
        } else if (item.type == TYPE_ITEM_EKONOM) {
            TYPE_ITEM_EKONOM
        } else if (item.type == TYPE_ITEM_KUPE) {
            TYPE_ITEM_KUPE
        } else if (item.type == TYPE_ITEM_LYUKS) {
            TYPE_ITEM_LYUKS
        } else if (item.type == TYPE_ITEM_PLATSKART) {
            TYPE_ITEM_PLATSKART
        } else {
            TYPE_ITEM_VIP
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_BIZNES) {
            val view =
                ItemTicketBiznesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return BiznesViewHolder(view)
        }

        if (viewType == TYPE_ITEM_EKONOM) {
            val view =
                ItemTicketEkonomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return EkonomViewHolder(view)
        }

        if (viewType == TYPE_ITEM_KUPE) {
            val view =
                ItemTicketKupeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return KupeViewHolder(view)
        }

        if (viewType == TYPE_ITEM_LYUKS) {
            val view =
                ItemTicketLyuksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LyuksViewHolder(view)
        }
        if (viewType == TYPE_ITEM_PLATSKART) {
            val view =
                ItemTicketPlatskartBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return PlatskartViewHolder(view)
        }

        val view =
            ItemTicketVipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VipViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BiznesViewHolder) {
            holder.view.tvWagonNum.setRandomWagonNumber()
            holder.view.llBiznes.setOnClickListener {
                onItemClicked.invoke(holder.view.tvWagonNum.text.toString().toInt())
            }

        }

        if (holder is EkonomViewHolder) {
            holder.view.tvWagonNum.setRandomWagonNumber()
            holder.view.llEkonom.setOnClickListener {
                onItemClicked.invoke(holder.view.tvWagonNum.text.toString().toInt())
            }
        }

        if (holder is KupeViewHolder) {
            holder.view.tvWagonNum.setRandomWagonNumber()
            holder.view.llKupe.setOnClickListener {
                onItemClicked.invoke(holder.view.tvWagonNum.text.toString().toInt())
            }
        }

        if (holder is LyuksViewHolder) {
            holder.view.tvWagonNum.setRandomWagonNumber()
            holder.view.llLyuks.setOnClickListener {
                onItemClicked.invoke(holder.view.tvWagonNum.text.toString().toInt())
            }
        }

        if (holder is PlatskartViewHolder) {
            holder.view.tvWagonNum.setRandomWagonNumber()
            holder.view.llPlaskart.setOnClickListener {
                onItemClicked.invoke(holder.view.tvWagonNum.text.toString().toInt())
            }
        }

        if (holder is VipViewHolder) {
            holder.view.tvWagonNum.setRandomWagonNumber()
            holder.view.llVip.setOnClickListener {
                onItemClicked.invoke(holder.view.tvWagonNum.text.toString().toInt())
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class BiznesViewHolder(val view: ItemTicketBiznesBinding) : RecyclerView.ViewHolder(view.root)

    class EkonomViewHolder(val view: ItemTicketEkonomBinding) : RecyclerView.ViewHolder(view.root)

    class KupeViewHolder(val view: ItemTicketKupeBinding) : RecyclerView.ViewHolder(view.root)

    class LyuksViewHolder(val view: ItemTicketLyuksBinding) : RecyclerView.ViewHolder(view.root)

    class PlatskartViewHolder(val view: ItemTicketPlatskartBinding) :
        RecyclerView.ViewHolder(view.root)

    class VipViewHolder(val view: ItemTicketVipBinding) : RecyclerView.ViewHolder(view.root)
}
