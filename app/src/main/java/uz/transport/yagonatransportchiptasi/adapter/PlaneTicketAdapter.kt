package uz.transport.yagonatransportchiptasi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.databinding.ItemPlaneListBinding
import uz.transport.yagonatransportchiptasi.extensions.Extensions
import uz.transport.yagonatransportchiptasi.extensions.Extensions.setDirection
import uz.transport.yagonatransportchiptasi.model.PlaneTicket

class PlaneTicketAdapter(val context: Context, val items: ArrayList<PlaneTicket>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            ItemPlaneListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaneTicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val from = Extensions.loadData(context)
        val to = Extensions.loadData2(context)
        if(holder is PlaneTicketViewHolder){
            holder.view.tvFrom.setDirection(from)
            holder.view.tvTo.setDirection(to)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PlaneTicketViewHolder(val view:ItemPlaneListBinding) : RecyclerView.ViewHolder(view.root) {

    }
}
