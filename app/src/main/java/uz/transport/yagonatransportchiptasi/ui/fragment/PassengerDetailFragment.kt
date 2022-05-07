package uz.transport.yagonatransportchiptasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.adapter.PassengerDetailAdapter
import uz.transport.yagonatransportchiptasi.databinding.FragmentPassengerDetailBinding
import uz.transport.yagonatransportchiptasi.databinding.FragmentPassengersSetupBinding
import uz.transport.yagonatransportchiptasi.model.PassengerStatus

class PassengerDetailFragment : Fragment() {

    lateinit var passengerDetailAdapter: PassengerDetailAdapter
    lateinit var binding: FragmentPassengerDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPassengerDetailBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        passengerDetailAdapter = PassengerDetailAdapter{
            openDetailEntryFragment()
        }
        refreshAdapter(getPassengers())
    }

    private fun openDetailEntryFragment() {
        findNavController().navigate(R.id.action_passengerDetailFragment_to_passengerDetailEntryFragment)
    }

    private fun refreshAdapter(passengers: java.util.ArrayList<PassengerStatus>) {
        passengerDetailAdapter.submitData(passengers)
        binding.rvPassengerDetail.adapter = passengerDetailAdapter
    }

    private fun getPassengers(): ArrayList<PassengerStatus> {
        return ArrayList<PassengerStatus>().apply {
            this.add(PassengerStatus(12, "Yuqori", "Voyaga yetgan"))
            this.add(PassengerStatus(42, "Past", "Voyaga yetgan"))
            this.add(PassengerStatus(10, "Yuqori", "Voyaga yetgan"))
        }
    }
}