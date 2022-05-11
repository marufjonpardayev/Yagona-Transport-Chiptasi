package uz.transport.yagonatransportchiptasi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.databinding.ItemBusListBinding
import uz.transport.yagonatransportchiptasi.extensions.Extensions
import uz.transport.yagonatransportchiptasi.extensions.Extensions.setDirection
import uz.transport.yagonatransportchiptasi.model.BusTicket

class BusTicketAdapter(val context: Context, val items: ArrayList<BusTicket>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            ItemBusListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BusTicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val from = Extensions.loadData(context)
        val to = Extensions.loadData2(context)
        (holder as BusTicketViewHolder).view.apply {
            tvCompanyName.text = item.tv_company_name
            tvStartTime.text = item.tv_start_time
            tvFinishTime.text = item.tv_finish_time
            holder.view.tvPalceStart.setDirection(from)
            holder.view.tvPlaceFinish.setDirection(to)
            tvDepartureTime.text = item.tv_departure_time
            tvTimeSpend.text = item.tv_time_spend
            tvSeatNum.text = item.tv_seat_num
            tvFare.text = item.tv_fare
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class BusTicketViewHolder(val view: ItemBusListBinding) : RecyclerView.ViewHolder(view.root) {
    }
}