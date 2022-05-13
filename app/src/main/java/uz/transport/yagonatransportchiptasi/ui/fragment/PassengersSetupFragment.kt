package uz.transport.yagonatransportchiptasi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentPassengersSetupBinding
import uz.transport.yagonatransportchiptasi.extensions.Extensions.changeBackgroundTint
import uz.transport.yagonatransportchiptasi.extensions.Extensions.changeBackgroundTintCenter
import uz.transport.yagonatransportchiptasi.extensions.Extensions.changeTextColorAndBackground
import uz.transport.yagonatransportchiptasi.extensions.Extensions.changeTint
import uz.transport.yagonatransportchiptasi.extensions.Extensions.decreaseValue
import uz.transport.yagonatransportchiptasi.extensions.Extensions.getAmount
import uz.transport.yagonatransportchiptasi.extensions.Extensions.increaseValue
import uz.transport.yagonatransportchiptasi.extensions.Extensions.loadData
import uz.transport.yagonatransportchiptasi.extensions.Extensions.loadData2
import uz.transport.yagonatransportchiptasi.extensions.Extensions.selectOneAtLeast
import uz.transport.yagonatransportchiptasi.model.PassengerStatus


class PassengersSetupFragment : Fragment() {

    private lateinit var binding: FragmentPassengersSetupBinding
    private var passengers: ArrayList<PassengerStatus> = ArrayList()
    var pickedSeatsList = ArrayList<Int>()
    var wagonNumber = 0
    var pickedSeats = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        wagonNumber = arguments?.get("wagonNumber").toString().toInt()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passengers_setup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPassengersSetupBinding.bind(view)

        binding.tvDirection.text = "${loadData(requireContext())}-${loadData2(requireContext())}"

        binding.tvWagonNumber.text = wagonNumber.toString()

        binding.tvDepartureTime.text = arguments?.get("departureDate").toString()

        binding.apply {

            ivBack.setOnClickListener {
                requireActivity().onBackPressed()
            }

            btnNext.setOnClickListener {
                passengers.clear()
                addPassengersToList()
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

        controlPickSeat()
    }

    private fun controlPickSeat() {

        binding.apply {
            ivSeat1.setOnClickListener {
                pickedSeats++
                ivSeat1.changeTint(pickedSeats, getPassengersAmount(), pickedSeatsList, 1)
                checkNextButton()
            }

            ivSeat6.setOnClickListener {
                pickedSeats++
                ivSeat6.changeTint(pickedSeats, getPassengersAmount(), pickedSeatsList, 6)
                checkNextButton()
            }

            ivSeat16.setOnClickListener {
                pickedSeats++
                ivSeat16.changeTint(pickedSeats, getPassengersAmount(), pickedSeatsList, 16)
                checkNextButton()
            }

            ivSeat17.setOnClickListener {
                pickedSeats++
                ivSeat17.changeTint(pickedSeats, getPassengersAmount(), pickedSeatsList, 17)
                checkNextButton()
            }

            ivSeat48.setOnClickListener {
                pickedSeats++
                ivSeat48.changeTint(pickedSeats, getPassengersAmount(), pickedSeatsList, 48)
                checkNextButton()
            }

            ivSeat70.setOnClickListener {
                pickedSeats++
                ivSeat70.changeTint(pickedSeats, getPassengersAmount(), pickedSeatsList, 70)
                checkNextButton()
            }
        }
    }

    private fun checkNextButton() {
        if (pickedSeats == getPassengersAmount()) {
            binding.btnNext.isEnabled = true
            binding.btnNext.setBackgroundResource(R.drawable.button_gradient)
        }
    }

    private fun addPassengersToList() {
        val adultsNumber = binding.tvAdultsNumber.text.toString().toInt()
        val childrenNumber = binding.tvChildrenNumber.text.toString().toInt()
        val infantNumber = binding.tvInfantsNumber.text.toString().toInt()

        for (i in 0 until adultsNumber) {
            passengers.add(PassengerStatus(32, "pastki", "Voyaga yetgan"))
        }

        for (i in 0 until childrenNumber) {
            passengers.add(PassengerStatus(35, "ustki", "Bola"))
        }

        for (i in 0 until infantNumber) {
            passengers.add(PassengerStatus(0, null, "Infant"))
        }
    }

    private fun openPassengerDetailFragment() {
        findNavController().navigate(
            R.id.action_passengersSetupFragment_to_passengerDetailFragment,
            bundleOf(
                "passengers" to passengers,
                "departureDate" to arguments?.get("departureDate"),
                "pickedSeatsList" to pickedSeatsList,
                "fromMoscow" to arguments?.get("fromMoscow").toString()
            )
        )
    }

    private fun getPassengersAmount(): Int {
        var all: Int
        binding.apply {
            all = tvAdultsNumber.getAmount() + tvChildrenNumber.getAmount()
        }
        return all
    }
}
