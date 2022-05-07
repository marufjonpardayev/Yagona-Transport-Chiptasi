package uz.transport.yagonatransportchiptasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.adapter.TicketAdapter
import uz.transport.yagonatransportchiptasi.databinding.FragmentTicketsBinding
import uz.transport.yagonatransportchiptasi.model.Ticket


class TicketsFragment : Fragment() {
    lateinit var binding: FragmentTicketsBinding
    val type = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_tickets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTicketsBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        refreshAdapter(allTickets())
    }

    private fun refreshAdapter(items: ArrayList<Ticket>) {
        val adapter = TicketAdapter(this, items)
        binding.recyclerView.adapter = adapter
    }

    private fun allTickets(): ArrayList<Ticket> {
        val items = ArrayList<Ticket>()
        if (type == 1){
            items.addAll(trainAfrosiyob())
        }else if (type == 2){
            items.addAll(trainSharq())
        }else if (type == 3){
            items.addAll(trainPopular())
        }else{
            items.addAll(trainSpecial())
        }
        return items
    }

    private fun trainAfrosiyob(): ArrayList<Ticket> {
        val tickets = ArrayList<Ticket>()

        tickets.add(Ticket(1))
        tickets.add(Ticket(1))
        tickets.add(Ticket(2))
        tickets.add(Ticket(2))
        tickets.add(Ticket(1))
        tickets.add(Ticket(2))

        return tickets
    }

    private fun trainSharq(): ArrayList<Ticket> {
        val tickets = ArrayList<Ticket>()

        tickets.add(Ticket(1))
        tickets.add(Ticket(1))
        tickets.add(Ticket(2))
        tickets.add(Ticket(2))
        tickets.add(Ticket(1))
        tickets.add(Ticket(2))

        return tickets
    }

    private fun trainPopular(): ArrayList<Ticket> {
        val tickets = ArrayList<Ticket>()

        tickets.add(Ticket(3))
        tickets.add(Ticket(3))
        tickets.add(Ticket(3))
        tickets.add(Ticket(4))
        tickets.add(Ticket(4))
        tickets.add(Ticket(5))
        tickets.add(Ticket(5))
        tickets.add(Ticket(5))
        tickets.add(Ticket(6))
        tickets.add(Ticket(6))

        return tickets
    }

    private fun trainSpecial(): ArrayList<Ticket> {
        val tickets = ArrayList<Ticket>()

        tickets.add(Ticket(3))
        tickets.add(Ticket(3))
        tickets.add(Ticket(3))
        tickets.add(Ticket(4))
        tickets.add(Ticket(4))
        tickets.add(Ticket(5))
        tickets.add(Ticket(5))
        tickets.add(Ticket(5))

        return tickets
    }
}