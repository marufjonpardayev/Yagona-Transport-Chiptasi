package uz.transport.yagonatransportchiptasi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.model.Ticket
import uz.transport.yagonatransportchiptasi.ui.fragment.TicketsFragment


class TicketAdapter(val fragment: TicketsFragment, val items: ArrayList<Ticket>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM_BIZNES = 1
    private val TYPE_ITEM_EKONOM = 2
    private val TYPE_ITEM_PLATSKART = 3
    private val TYPE_ITEM_KUPE = 4
    private val TYPE_ITEM_LYUKS = 5
    private val TYPE_ITEM_VIP = 6

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return if (item.type == TYPE_ITEM_BIZNES){
            TYPE_ITEM_BIZNES
        }else if (item.type == TYPE_ITEM_EKONOM){
            TYPE_ITEM_EKONOM
        }else if (item.type == TYPE_ITEM_KUPE){
            TYPE_ITEM_KUPE
        }else if (item.type == TYPE_ITEM_LYUKS){
            TYPE_ITEM_LYUKS
        }else if (item.type == TYPE_ITEM_PLATSKART){
            TYPE_ITEM_PLATSKART
        } else{
            TYPE_ITEM_VIP
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_BIZNES){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket_biznes, parent, false)
            return BiznesViewHolder(view)
        }

        if (viewType == TYPE_ITEM_EKONOM){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket_ekonom, parent, false)
            return EkonomViewHolder(view)
        }

        if (viewType == TYPE_ITEM_KUPE){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket_kupe, parent, false)
            return KupeViewHolder(view)
        }

        if (viewType == TYPE_ITEM_LYUKS){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket_lyuks, parent, false)
            return LyuksViewHolder(view)
        }
        if (viewType == TYPE_ITEM_PLATSKART) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ticket_platskart, parent, false)
            return PlatskartViewHolder(view)
        }

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticket_vip, parent, false)
        return VipViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class BiznesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    class EkonomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    class KupeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    class LyuksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    class PlatskartViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    class VipViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}
