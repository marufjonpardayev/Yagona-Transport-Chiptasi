package uz.transport.yagonatransportchiptasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentAllTransportBinding
import uz.transport.yagonatransportchiptasi.extensions.Extensions.loadData
import uz.transport.yagonatransportchiptasi.extensions.Extensions.loadData2

class AllTransportFragment : Fragment() {

    private lateinit var binding: FragmentAllTransportBinding
    var departureTime: String = ""
    var fromMoscow: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        departureTime = arguments?.get("date").toString()
        fromMoscow = arguments?.get("fromMoscow").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_transport, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAllTransportBinding.bind(view)

        if (fromMoscow == "true") {
            binding.apply {
                tvPlaneCost.text = "4 020 500 so'm"
                tvPlaneCost1.text = "4 020 500 so'm"
                tvPlaneCost2.text = "4 500 990 so'm"

                tvTrainCost.text = "287 000 so'm"
                tvTrainCostUmumiy.text = "287 000 so'm"
                llSharq.visibility = View.GONE
                llAfrosiyob.visibility = View.GONE
            }
        }

        binding.btnShowBuses.setOnClickListener {
            findNavController().navigate(
                R.id.action_allTransportFragment_to_busFragment,
                bundleOf("date" to departureTime)
            )
        }

        binding.btnShowFlights.setOnClickListener {
            findNavController().navigate(
                R.id.action_allTransportFragment_to_planeFragment,
                bundleOf("date" to departureTime, "fromMoscow" to fromMoscow)
            )
        }

        binding.btnShowTrains.setOnClickListener {
            findNavController().navigate(
                R.id.action_allTransportFragment_to_trainDetailsFragment,
                bundleOf("date" to departureTime, "fromMoscow" to fromMoscow)
            )
        }

        binding.tvDirection.text = "${loadData(requireContext())}-${loadData2(requireContext())}"

        binding.tvDate.text = departureTime

        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.checkboxTaxi.setOnClickListener {
            if (binding.checkboxTaxi.isChecked) {
                binding.llTaxi.visibility = View.VISIBLE
            } else {
                binding.llTaxi.visibility = View.GONE
            }
        }
    }
}