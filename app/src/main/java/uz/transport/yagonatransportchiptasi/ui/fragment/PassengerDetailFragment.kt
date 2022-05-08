package uz.transport.yagonatransportchiptasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.adapter.PassengerDetailAdapter
import uz.transport.yagonatransportchiptasi.databinding.FragmentPassengerDetailBinding
import uz.transport.yagonatransportchiptasi.model.PassengerDetail
import uz.transport.yagonatransportchiptasi.model.PassengerStatus

class PassengerDetailFragment : Fragment() {

    private lateinit var passengerDetailAdapter: PassengerDetailAdapter
    private lateinit var binding: FragmentPassengerDetailBinding
    private lateinit var passengers: ArrayList<PassengerStatus>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments?.get("passengers") != null) {
            passengers = arguments?.get("passengers") as ArrayList<PassengerStatus>
        }
    }

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
        passengerDetailAdapter = PassengerDetailAdapter { position ->
            openDetailEntryFragment()
        }
        refreshAdapter(passengers)
    }

    private fun openDetailEntryFragment() {
        findNavController().navigate(
            R.id.action_passengerDetailFragment_to_passengerDetailEntryFragment,
            bundleOf(
                "passengers" to passengers
            )
        )
    }

    private fun refreshAdapter(passengers: ArrayList<PassengerStatus>) {
        passengerDetailAdapter.submitData(passengers)
        binding.rvPassengerDetail.adapter = passengerDetailAdapter
    }
}