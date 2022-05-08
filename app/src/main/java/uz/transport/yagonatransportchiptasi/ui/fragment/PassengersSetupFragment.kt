package uz.transport.yagonatransportchiptasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentPassengersSetupBinding
import uz.transport.yagonatransportchiptasi.ui.activity.MainActivity


class PassengersSetupFragment : Fragment() {

    private lateinit var binding: FragmentPassengersSetupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passengers_setup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPassengersSetupBinding.bind(view)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_passengersSetupFragment_to_passengerDetailFragment)
        }
    }
}