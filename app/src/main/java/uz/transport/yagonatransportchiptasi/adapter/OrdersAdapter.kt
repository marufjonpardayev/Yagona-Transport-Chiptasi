package uz.transport.yagonatransportchiptasi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.databinding.ItemTicketBinding
import uz.transport.yagonatransportchiptasi.model.Order

class OrdersAdapter(val context: Context, private var onItemClicked: (() -> Unit)) :
    RecyclerView.Adapter<OrdersAdapter.VH>() {

    private val orders = ArrayList<Order>()

    class VH(val view: ItemTicketBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val order = orders[position]
        holder.view.apply {
            tvFrom.text = order.from
            tvTo.text = order.to
            tvDate.text = order.date
            tvFullname.text = order.passengerFullName

            llOrder.setOnClickListener {
                onItemClicked.invoke()
            }

            btnNFC.setOnClickListener {
                Toast.makeText(context, "NFC tizimini aktivlashtiring", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = orders.size

    fun submitData(list: ArrayList<Order>) {
        orders.addAll(list)
        notifyDataSetChanged()
    }
}