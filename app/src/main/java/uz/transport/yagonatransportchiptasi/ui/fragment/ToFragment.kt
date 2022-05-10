package uz.transport.yagonatransportchiptasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.adapter.FromAdapter


class ToFragment : Fragment() {
    lateinit var recyclerFrom: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        recyclerFrom = view.findViewById(R.id.rvFrom)
        recyclerFrom.layoutManager = GridLayoutManager(requireActivity(), 1)
        refreshAdapter(getRegions())

    }

    private fun getRegions(): ArrayList<String> {
        val regions = ArrayList<String>()
        regions.add("Tashkent")
        regions.add("Samarqand")
        regions.add("Sirdaryo")
        regions.add("Buxoro")
        regions.add("Qashqadaryo")
        regions.add("Xorazm")
        regions.add("Qoraqalpogiston")
        regions.add("Andijon")
        regions.add("Namangan")
        regions.add("Fargona")
        regions.add("Tashkent")
        regions.add("Jizzax")
        regions.add("Surxandaryo")
        return regions

    }

    private fun refreshAdapter(regions: ArrayList<String>) {
        val adapter = FromAdapter(regions)
        recyclerFrom.adapter = adapter
    }


}