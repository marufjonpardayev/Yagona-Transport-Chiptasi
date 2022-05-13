package uz.transport.yagonatransportchiptasi.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.adapter.TrainAdapter
import uz.transport.yagonatransportchiptasi.databinding.FragmentTrainDetailsBinding
import uz.transport.yagonatransportchiptasi.extensions.Extensions
import uz.transport.yagonatransportchiptasi.model.Train

class TrainDetailsFragment : Fragment() {

    private lateinit var binding: FragmentTrainDetailsBinding
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
    ): View {
        return inflater.inflate(R.layout.fragment_train_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTrainDetailsBinding.bind(view)
        initViews()
    }


    @SuppressLint("SetTextI18n")
    private fun initViews() {

        if (fromMoscow == "true") {
            binding.tvDirection.text =
                "Toshkent-${Extensions.loadData2(requireContext())}"
        } else {
            binding.tvDirection.text =
                "${Extensions.loadData(requireContext())}-${Extensions.loadData2(requireContext())}"
        }

        refreshAdapter(allTrains())

        binding.tvDate.text = departureTime

        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun refreshAdapter(items: ArrayList<Train>) {
        val adapter = TrainAdapter(requireContext(), items) { type ->
            findNavController().navigate(
                R.id.action_trainDetailsFragment_to_ticketsFragment,
                bundleOf(
                    "type" to type,
                    "date" to departureTime,
                    "fromMoscow" to arguments?.get("fromMoscow").toString()
                )
            )
        }
        binding.recyclerView.adapter = adapter
    }

    private fun allTrains(): ArrayList<Train> {
        val items = ArrayList<Train>()

        if (fromMoscow == "true") {
            items.add(Train(2, true))
            items.add(Train(2, true))
        } else {
            items.add(Train(1))
            items.add(Train(2))
            items.add(Train(4))
            items.add(Train(4))
            items.add(Train(1))
            items.add(Train(2))
            items.add(Train(3))
            items.add(Train(4))
        }
        return items
    }
}