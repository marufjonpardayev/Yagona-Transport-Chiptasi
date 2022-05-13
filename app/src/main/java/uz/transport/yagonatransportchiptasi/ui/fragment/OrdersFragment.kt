package uz.transport.yagonatransportchiptasi.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.adapter.OrdersAdapter
import uz.transport.yagonatransportchiptasi.databinding.FragmentOrdersBinding
import uz.transport.yagonatransportchiptasi.manager.database.OrderDatabase
import uz.transport.yagonatransportchiptasi.model.Order
import uz.transport.yagonatransportchiptasi.ui.activity.TicketActivity


class OrdersFragment : Fragment() {

    private lateinit var ordersAdapter: OrdersAdapter
    private lateinit var binding: FragmentOrdersBinding
    lateinit var orderDatabase: OrderDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderDatabase = OrderDatabase.getInstance(requireContext())
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentOrdersBinding.bind(view)
        initViews()
    }

    private fun initViews() {

        binding.cv.setOnClickListener {
            orderDatabase.orderDao().clearOrders()
            ordersAdapter.submitData(getOrdersFromDatabase())
        }

        ordersAdapter = OrdersAdapter(requireContext()) {
            openTicketActivity()
        }
        ordersAdapter.submitData(getOrdersFromDatabase())

        binding.rvOrders.adapter = ordersAdapter
    }

    private fun openTicketActivity() {
        val intent = Intent(requireActivity(), TicketActivity::class.java)
        intent.putExtra("fromMoscow", "false")
        startActivity(intent)
    }

    private fun getOrdersFromDatabase(): ArrayList<Order> {
        val orders = orderDatabase.orderDao().getAllProduct() as ArrayList<Order>
        if (orders.isEmpty()) return ArrayList()

        for (i in 0 until orders.size) {
            val order = orders[i]
            orders[i] = orders[orders.size - i - 1]
            orders[orders.size - i - 1] = order
        }
        return orders
    }
}