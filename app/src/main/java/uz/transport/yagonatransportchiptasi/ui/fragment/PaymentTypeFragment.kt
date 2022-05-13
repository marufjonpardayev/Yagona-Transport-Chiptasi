package uz.transport.yagonatransportchiptasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentPaymentTypeBinding

class PaymentTypeFragment : Fragment() {

    private lateinit var binding: FragmentPaymentTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPaymentTypeBinding.bind(view)

        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.cvPayme.setOnClickListener {
            findNavController().navigate(
                R.id.action_paymentTypeFragment_to_paymentFragment,
                bundleOf(
                    "passengerDetails" to arguments?.get("passengerDetails"),
                    "departureDate" to arguments?.get("departureDate"),
                    "fromMoscow" to arguments?.get("fromMoscow").toString()
                )
            )
        }
    }
}