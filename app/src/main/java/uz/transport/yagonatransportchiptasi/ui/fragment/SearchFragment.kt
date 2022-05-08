package uz.transport.yagonatransportchiptasi.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentSearchBinding
import uz.transport.yagonatransportchiptasi.ui.activity.CalendarActivity
import uz.transport.yagonatransportchiptasi.ui.activity.MainActivity

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        binding.apply {
            ivChange.setOnClickListener {
                changeDestinations()
            }

        //open CalendarActivity
            llCalendarDeparture.setOnClickListener {
                openCalendarActivity()
            }

            //openn
            btnSearch.setOnClickListener {
                openPassengerSetupFragment()
            }
        }
    }

    private fun openPassengerSetupFragment() {
        findNavController().navigate(R.id.action_searchFragment_to_passengersSetupFragment)
    }

    private fun openCalendarActivity() {
        val intent = Intent(requireContext(), CalendarActivity::class.java)
        intent.putExtra("locationStart", "Toshkent")
        intent.putExtra("locationEnd", "Samarqand")
        startActivity(intent)
    }

    //to change directions
    private fun changeDestinations() {
        binding.apply {
            if (tvFrom.text.isNotEmpty() && tvTo.text.isNotEmpty()) {
                val departure = tvFrom.text
                tvFrom.text = tvTo.text
                tvTo.text = departure
            }
        }
    }
}