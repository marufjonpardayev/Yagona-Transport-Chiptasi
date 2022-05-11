package uz.transport.yagonatransportchiptasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.adapter.PlaneTicketAdapter
import uz.transport.yagonatransportchiptasi.databinding.FragmentPlaneBinding
import uz.transport.yagonatransportchiptasi.model.PlaneTicket


class PlaneFragment : Fragment() {
    lateinit var binding: FragmentPlaneBinding

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
        refreshAdapter(allPlane())
    }

    private fun refreshAdapter(items: ArrayList<PlaneTicket>) {
        val adapter = PlaneTicketAdapter(requireContext(), items)
        binding.recyclerView.adapter = adapter
    }

    private fun allPlane(): ArrayList<PlaneTicket> {
        val items = ArrayList<PlaneTicket>()

        items.add(PlaneTicket(1))
        items.add(PlaneTicket(1))
        items.add(PlaneTicket(1))
        items.add(PlaneTicket(1))
        items.add(PlaneTicket(1))
        items.add(PlaneTicket(1))
        items.add(PlaneTicket(1))
        items.add(PlaneTicket(1))

        return items
    }
}