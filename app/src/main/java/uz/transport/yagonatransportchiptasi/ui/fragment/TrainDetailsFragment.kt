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
    var departureTime:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        departureTime = arguments?.get("date").toString()
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

        binding.tvDirection.text = "${Extensions.loadData(requireContext())}-${Extensions.loadData2(requireContext())}"

        refreshAdapter(allTrains())

        binding.tvDate.text = departureTime

        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun refreshAdapter(items: ArrayList<Train>) {
        val adapter = TrainAdapter(this, items) { type ->
            findNavController().navigate(
                R.id.action_trainDetailsFragment_to_ticketsFragment,
                bundleOf("type" to type,  "date" to departureTime)
            )
        }
        binding.recyclerView.adapter = adapter
    }

    private fun allTrains(): ArrayList<Train> {
        val items = ArrayList<Train>()

        items.add(Train(1))
        items.add(Train(3))
        items.add(Train(1))
        items.add(Train(2))
        items.add(Train(2))
        items.add(Train(3))
        items.add(Train(2))
        items.add(Train(1))

        return items
    }
}