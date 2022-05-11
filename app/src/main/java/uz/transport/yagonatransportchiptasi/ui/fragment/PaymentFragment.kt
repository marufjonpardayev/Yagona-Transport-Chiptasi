package uz.transport.yagonatransportchiptasi.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentPaymentBinding
import uz.transport.yagonatransportchiptasi.extensions.Extensions.isNotEmpty
import uz.transport.yagonatransportchiptasi.ui.activity.TicketActivity


class PaymentFragment : Fragment() {

    lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPaymentBinding.bind(view)

        binding.btnNext.setOnClickListener {
            openTicketActivity()
        }
    }

    private fun openTicketActivity() {
        if (binding.edtCardNumber.isNotEmpty() && binding.edtCardExpireMonth.isNotEmpty() && binding.edtCardExpireYear.isNotEmpty()) {
            val intent = Intent(requireActivity(), TicketActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "Kerakli ma'lumotlar kiritilmadi", Toast.LENGTH_SHORT)
                .show()
        }
    }
}