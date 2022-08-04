package uz.transport.yagonatransportchiptasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.adapter.PlaneTicketAdapter
import uz.transport.yagonatransportchiptasi.databinding.FragmentPlaneBinding
import uz.transport.yagonatransportchiptasi.extensions.Extensions.loadData
import uz.transport.yagonatransportchiptasi.extensions.Extensions.loadData2
import uz.transport.yagonatransportchiptasi.model.PlaneTicket


class PlaneFragment : Fragment() {
    lateinit var binding: FragmentPlaneBinding
    var departureTime: String = ""
    var fromMoscow = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        departureTime = arguments?.get("date").toString()
        fromMoscow = arguments?.get("fromMoscow").toString()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_plane, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlaneBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        if (fromMoscow == "true") {
            binding.tvDirection.text =
                "${loadData(requireContext())}-Toshkent"
        } else {
            binding.tvDirection.text =
                "Toshkent-${loadData2(requireContext())}"
        }

        refreshAdapter(allPlane())

        binding.tvDate.text = departureTime

        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun refreshAdapter(items: ArrayList<PlaneTicket>) {
        val adapter = PlaneTicketAdapter(requireContext(), items)
        binding.recyclerView.adapter = adapter
    }

    private fun allPlane(): ArrayList<PlaneTicket> {
        val items = ArrayList<PlaneTicket>()
        if (fromMoscow == "true") {
            items.add(PlaneTicket(true))
            items.add(PlaneTicket(true))
            items.add(PlaneTicket(true))
        } else {
            items.add(PlaneTicket(false))
            items.add(PlaneTicket(false))
            items.add(PlaneTicket(false))
        }

        return items
    }
}