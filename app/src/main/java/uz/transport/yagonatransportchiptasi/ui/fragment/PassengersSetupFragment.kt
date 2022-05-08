package uz.transport.yagonatransportchiptasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentPassengersSetupBinding
import uz.transport.yagonatransportchiptasi.extensions.Extensions.changeTextColorAndBackground
import uz.transport.yagonatransportchiptasi.extensions.Extensions.decreaseValue
import uz.transport.yagonatransportchiptasi.extensions.Extensions.increaseValue
import uz.transport.yagonatransportchiptasi.extensions.Extensions.selectOneAtLeast


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

        binding.apply {
            btnNext.setOnClickListener {
                openPassengerDetailFragment()
            }

            tvAddAdult.setOnClickListener {
                tvAdultsNumber.increaseValue(tvMinusAdult)
            }

            tvAddChildren.setOnClickListener {
                tvChildrenNumber.increaseValue(tvMinusChildren)
            }

            tvAddInfant.setOnClickListener {
                tvInfantsNumber.increaseValue(tvMinusInfant)
            }

            tvMinusAdult.setOnClickListener {
                if (tvAdultsNumber.text.toString().toInt() > 1) {
                    tvAdultsNumber.decreaseValue()
                }
                if (tvAdultsNumber.text.toString().toInt() == 1) {
                    tvMinusAdult.changeTextColorAndBackground()
                }
            }

            tvMinusChildren.setOnClickListener {
                if (tvChildrenNumber.text.toString().toInt() > 0) {
                    tvChildrenNumber.decreaseValue()
                }

                if (tvChildrenNumber.text.toString().toInt() == 0) {
                    tvMinusChildren.changeTextColorAndBackground()
                }
            }

            tvMinusInfant.setOnClickListener {
                if (tvInfantsNumber.text.toString().toInt() > 0) {
                    tvInfantsNumber.decreaseValue()
                }
                if (tvChildrenNumber.text.toString().toInt() == 0) {
                    tvMinusInfant.changeTextColorAndBackground()
                }
            }

            checkboxDown.setOnCheckedChangeListener { _, b ->
                checkboxDown.selectOneAtLeast(checkboxUp, b)
            }

            checkboxUp.setOnCheckedChangeListener { _, b ->
                checkboxUp.selectOneAtLeast(checkboxDown, b)
            }
        }
    }

    private fun openPassengerDetailFragment() {
        findNavController().navigate(R.id.action_passengersSetupFragment_to_passengerDetailFragment)
    }
}
