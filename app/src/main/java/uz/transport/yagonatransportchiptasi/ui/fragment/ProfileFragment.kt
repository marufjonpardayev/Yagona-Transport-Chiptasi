package uz.transport.yagonatransportchiptasi.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //dark colored status bar text
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        if (requireContext().getSharedPreferences("", Context.MODE_PRIVATE)
                .getBoolean("isReg", false)
        ) {
            binding.nested.visibility = View.VISIBLE
            binding.tvEnter.visibility = View.GONE
        } else {
            binding.nested.visibility = View.GONE
            binding.tvEnter.visibility = View.VISIBLE
        }

        binding.tvEnter.setOnClickListener {
            openRegistrationFragment()
        }
        binding.logOut.setOnClickListener {
            requireContext().getSharedPreferences("", Context.MODE_PRIVATE).edit()
                .putBoolean("isReg", false).commit()
            binding.nested.visibility = View.GONE
            binding.tvEnter.visibility = View.VISIBLE
        }
    }

    private fun openRegistrationFragment() {
        findNavController().navigate(R.id.action_profileFragment_to_registerFragment)
    }
}