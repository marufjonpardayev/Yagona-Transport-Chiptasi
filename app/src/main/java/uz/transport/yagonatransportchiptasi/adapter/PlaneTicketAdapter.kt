package uz.transport.yagonatransportchiptasi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.databinding.ItemPlaneListBinding
import uz.transport.yagonatransportchiptasi.model.PlaneTicket

class PlaneTicketAdapter(val context: Context, val items: ArrayList<PlaneTicket>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            ItemPlaneListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaneTicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PlaneTicketViewHolder(val view:ItemPlaneListBinding) : RecyclerView.ViewHolder(view.root) {

    }
}
