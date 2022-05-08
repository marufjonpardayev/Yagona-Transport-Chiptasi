package uz.transport.yagonatransportchiptasi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.databinding.ItemPassengerDetailBinding
import uz.transport.yagonatransportchiptasi.model.PassengerDetail
import uz.transport.yagonatransportchiptasi.model.PassengerStatus

class PassengerDetailAdapter(private var onItemClicked: ((Int) -> Unit)) :
    RecyclerView.Adapter<PassengerDetailAdapter.VH>() {

    private val passengers: ArrayList<PassengerStatus> = ArrayList()

    inner class VH(val binding: ItemPassengerDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemPassengerDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val passengerStatus = passengers[position]
        holder.binding.apply {
            if (passengerStatus.status != "Infant") {
                tvPlaceNumber.text = "Joy ${passengerStatus.placeNumber}"
                tvUpOrDown.text = passengerStatus.upOrDown
                tvStatus.text = passengerStatus.status
            } else {
                ivInfant.visibility = View.VISIBLE
                tvPlaceNumber.visibility = View.GONE
                tvUpOrDown.visibility = View.GONE
            }
            tvChoose.setOnClickListener {
                onItemClicked.invoke(position)
            }

        }
    }

    override fun getItemCount(): Int = passengers.size

    fun submitData(
        list: ArrayList<PassengerStatus>
    ) {
        passengers.addAll(list)
    }
}