package uz.transport.yagonatransportchiptasi.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentPaymentBinding
import uz.transport.yagonatransportchiptasi.extensions.Extensions.isNotEmpty
import uz.transport.yagonatransportchiptasi.extensions.Extensions.loadData
import uz.transport.yagonatransportchiptasi.extensions.Extensions.loadData2
import uz.transport.yagonatransportchiptasi.manager.database.OrderDatabase
import uz.transport.yagonatransportchiptasi.model.Order
import uz.transport.yagonatransportchiptasi.model.PassengerDetail
import uz.transport.yagonatransportchiptasi.model.PassengerStatus
import uz.transport.yagonatransportchiptasi.ui.activity.TicketActivity


class PaymentFragment : Fragment() {

    lateinit var binding: FragmentPaymentBinding
    lateinit var orderDatabase: OrderDatabase
    lateinit var passengerDetails: ArrayList<PassengerDetail>
    private var orders: ArrayList<Order> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderDatabase = OrderDatabase.getInstance(requireContext())
        passengerDetails = arguments?.get("passengerDetails") as ArrayList<PassengerDetail>
    }

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

        fillOrderList()

        binding.btnNext.setOnClickListener {
            openTicketActivity()
        }
    }

    private fun fillOrderList() {
        Log.d("TAG", "fillOrderList: $passengerDetails")
        passengerDetails.forEach {
            if (it != null) {
                val fullName = it.name + " " + it.surname + " " + it.middleName
                val from = loadData(requireContext())
                val to = loadData2(requireContext())
                val departureDate = arguments?.get("departureDate")

                orders.add(
                    Order(
                        from = from,
                        to = to,
                        passengerFullName = fullName,
                        date = departureDate.toString()
                    )
                )
            }
        }
    }

    private fun openTicketActivity() {
        if (binding.edtCardNumber.isNotEmpty() && binding.edtCardExpireMonth.isNotEmpty() && binding.edtCardExpireYear.isNotEmpty()) {
            saveToDatabase()
            callTicketActivity()
        } else {
            Toast.makeText(requireContext(), "Kerakli ma'lumotlar kiritilmadi", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun saveToDatabase() {
        orders.forEach { order ->
            orderDatabase.orderDao().insertProduct(order)
        }
    }

    private fun callTicketActivity() {
        val intent = Intent(requireActivity(), TicketActivity::class.java)
        startActivity(intent)
    }
}