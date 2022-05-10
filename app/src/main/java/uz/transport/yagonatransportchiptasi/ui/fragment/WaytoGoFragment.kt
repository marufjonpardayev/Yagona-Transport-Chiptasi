package uz.transport.yagonatransportchiptasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentWaytoGoBinding

class WaytoGoFragment : Fragment() {
    lateinit var binding: FragmentWaytoGoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wayto_go, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWaytoGoBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            checkbox.setOnClickListener {
                if (checkbox.isChecked){
                    cardTaxi.visibility = View.VISIBLE
                    tvMoney.text = "200000 so'm"
                }else{
                    cardTaxi.visibility = View.GONE
                    tvMoney.text = "150000 so'm"
                }
            }
        }
    }
}