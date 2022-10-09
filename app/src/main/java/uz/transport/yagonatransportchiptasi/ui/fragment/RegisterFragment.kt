package uz.transport.yagonatransportchiptasi.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        roleSpinner()

        binding.apply {
            btnSave.setOnClickListener {
                requireContext().getSharedPreferences("", Context.MODE_PRIVATE).edit()
                    .putBoolean("isReg", true).commit()
                requireActivity().onBackPressed()
            }
        }
    }

    private fun roleSpinner() {
        val type: Array<String> = arrayOf("Oddiy foydalanuvchi", "Guide", "Transport owner")
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, type)
        val editTextFilledExposedDropdown = binding.edtJob

        editTextFilledExposedDropdown.setAdapter(adapter)
    }
}