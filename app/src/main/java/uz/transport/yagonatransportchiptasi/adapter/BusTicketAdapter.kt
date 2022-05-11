package uz.transport.yagonatransportchiptasi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.databinding.ItemBusListBinding
import uz.transport.yagonatransportchiptasi.model.BusTicket

class BusTicketAdapter(val context: Context, val items: ArrayList<BusTicket>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            ItemBusListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BusTicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        (holder as BusTicketViewHolder).view.apply {
//            tvCompanyName.text = item.tv_company_name
//            tvStartTime.text = item.tv_start_time
//            tvFinishTime.text = item.tv_finish_time
//            tvPalceStart.text = item.tv_palce_start
//            tvPlaceFinish.text = item.tv_place_finish
//            tvDepartureTime.text = item.tv_departure_time
//            tvTimeSpend.text = item.tv_time_spend
//            tvSeatNum.text = item.tv_seat_num
//            tvFare.text = item.tv_fare
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class BusTicketViewHolder(val view:  ItemBusListBinding) : RecyclerView.ViewHolder(view.root) {
    }
}