package uz.transport.yagonatransportchiptasi.ui.fragment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.FragmentPassengerDetailEntryBinding
import uz.transport.yagonatransportchiptasi.model.PassengerDetail
import uz.transport.yagonatransportchiptasi.utils.DialogView
import java.util.*


class PassengerDetailEntryFragment : Fragment() {

    lateinit var binding: FragmentPassengerDetailEntryBinding
    private var mDateSetListener: OnDateSetListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_detail_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPassengerDetailEntryBinding.bind(view)

        binding.apply {
            edtBirthDate.setOnClickListener {
                showCalendarDialog()
            }

            btnRegisterOneId.setOnClickListener {
                showOneIDLoginDialog()
            }

            btnAddPassenger.setOnClickListener {
                val surname = edtSurname.text.toString()
                val name = edtName.text.toString()
                val middleName = edtMiddleName.text.toString()
                val dateOfBirth = edtBirthDate.text.toString()
                val gender = if (rbMale.isChecked) 'M' else 'F'
                val documentNumber = edtDocumentNumber.text.toString()
                val privilege =
                    if (checkboxPrivilege.isChecked) spinnerPrivilege.selectedItem.toString() else null

                val passengerDetail = PassengerDetail(
                    surname,
                    name,
                    middleName,
                    dateOfBirth,
                    gender,
                    documentNumber,
                    privilege
                )

                openPassengerDetailFragment(passengerDetail)
            }
        }

        val resources = requireActivity().resources

        setAdapterToSpinner(
            resources.getStringArray(R.array.documents),
            binding.spinnerDocumentType
        )

        setAdapterToSpinner(
            resources.getStringArray(R.array.countries),
            binding.spinnerCountry
        )
        setAdapterToSpinner(resources.getStringArray(R.array.regions), binding.spinnerCity)

        setAdapterToSpinner(resources.getStringArray(R.array.privileges), binding.spinnerPrivilege)

        controlPrivilege()
    }

    private fun openPassengerDetailFragment(passengerDetail: PassengerDetail) {
        findNavController().navigate(
            R.id.action_passengerDetailEntryFragment_to_passengerDetailFragment,
            bundleOf(
                "passengers" to arguments?.get("passengers")
            )
        )
    }

    private fun controlPrivilege() {
        binding.checkboxPrivilege.setOnClickListener {
            if (binding.checkboxPrivilege.isChecked) {
                binding.spinnerPrivilege.visibility = View.VISIBLE
            } else {
                binding.spinnerPrivilege.visibility = View.GONE
            }
        }
    }

    //setting adapter to any spinner
    private fun setAdapterToSpinner(entries: Array<String>, spinner: AppCompatSpinner) {
        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireActivity(), R.layout.item_spinner,
            entries
        )

        spinnerArrayAdapter.setDropDownViewResource(R.layout.item_spinner)
        spinner.adapter = spinnerArrayAdapter
    }

    private fun showCalendarDialog() {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            mDateSetListener,
            year, month, day
        )

        datePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        datePickerDialog.show()

        getSelectedDate()
    }

    private fun getSelectedDate() {
        mDateSetListener = OnDateSetListener { _, year, month, day ->
            val date = "$day/${month + 1}/$year"
            binding.edtBirthDate.setText(date)
        }
    }

    private fun showOneIDLoginDialog() {
        val dialogView = DialogView { oneID ->
            //send request to server to get data combined with oneID
        }

        dialogView.showOneIDLoginDialog(requireActivity())
    }
}