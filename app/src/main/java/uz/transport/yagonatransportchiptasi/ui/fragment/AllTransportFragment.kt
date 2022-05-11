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
    var departureTime:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        departureTime = arguments?.get("date").toString()
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

        binding.btnShowBuses.setOnClickListener {
            findNavController().navigate(R.id.action_allTransportFragment_to_busFragment, bundleOf("date" to departureTime))
        }

        binding.btnShowFlights.setOnClickListener {
            findNavController().navigate(R.id.action_allTransportFragment_to_planeFragment, bundleOf("date" to departureTime))
        }

        binding.btnShowTrains.setOnClickListener {
            findNavController().navigate(R.id.action_allTransportFragment_to_trainDetailsFragment, bundleOf("date" to departureTime))
        }

        binding.tvDirection.text = "${loadData(requireContext())}-${loadData2(requireContext())}"

        binding.tvDate.text = departureTime

        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}