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
    private val pickedSeatsList: ArrayList<Int> = ArrayList()
    val passengerDetails: ArrayList<PassengerDetail?> = ArrayList()

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
                tvPlaceNumber.text = "Joy ${pickedSeatsList[position]}"
                tvUpOrDown.text = passengerStatus.upOrDown
                tvStatus.text = passengerStatus.status

                checkIfDetailsEntered(holder.binding, position)

            } else {
                ivInfant.visibility = View.VISIBLE
                tvPlaceNumber.visibility = View.GONE
                tvUpOrDown.visibility = View.GONE

                checkIfDetailsEntered(holder.binding, position)
            }
            tvChoose.setOnClickListener {
                onItemClicked.invoke(position)
            }
        }
    }

    private fun checkIfDetailsEntered(binding: ItemPassengerDetailBinding, position: Int) {
        binding.apply {
            if (passengerDetails.isNotEmpty()) {
                if (passengerDetails[position] != null) {
                    val passengerDetail = passengerDetails[position]
                    clBottom.visibility = View.GONE
                    clFullData.visibility = View.VISIBLE

                    tvFullname.text =
                        "${passengerDetail!!.surname} ${passengerDetail.name} ${passengerDetail.middleName}"
                    tvFullData.text =
                        "Jinsi: ${passengerDetail.gender}, Tug'ilgan sana: ${passengerDetail.dateOfBirth},\nHujjat: ${passengerDetail.documentNumber}"
                }
            }
        }
    }

    override fun getItemCount(): Int = passengers.size

    fun submitData(
        list: ArrayList<PassengerStatus>
    ) {
        passengers.addAll(list)
        for (i in list.indices) {
            passengerDetails.add(null)
        }
    }

    fun submitDataToDetails(position: Int, passengerDetail: PassengerDetail) {
        passengerDetails.add(position, passengerDetail)
        notifyItemChanged(position)
    }

    fun submitPickedSeatsList(list: ArrayList<Int>) {
        pickedSeatsList.addAll(list)
    }
}