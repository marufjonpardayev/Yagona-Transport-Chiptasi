package uz.transport.yagonatransportchiptasi.ui.fragment

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.adapter.PassengerDetailAdapter
import uz.transport.yagonatransportchiptasi.databinding.FragmentPassengerDetailBinding
import uz.transport.yagonatransportchiptasi.extensions.Extensions.isNotEmpty
import uz.transport.yagonatransportchiptasi.extensions.Extensions.loadData
import uz.transport.yagonatransportchiptasi.extensions.Extensions.loadData2
import uz.transport.yagonatransportchiptasi.model.PassengerDetail
import uz.transport.yagonatransportchiptasi.model.PassengerStatus
import uz.transport.yagonatransportchiptasi.utils.DialogView
import java.util.*
import kotlin.collections.ArrayList

class PassengerDetailFragment : Fragment() {

    private lateinit var bottomSheet: View
    private lateinit var passengerDetailAdapter: PassengerDetailAdapter
    private lateinit var binding: FragmentPassengerDetailBinding
    private lateinit var passengers: ArrayList<PassengerStatus>
    private lateinit var sheetBehavior: BottomSheetBehavior<View>
    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null
    private lateinit var pickedSeatsList: ArrayList<Int>
    private lateinit var passengerDetail: PassengerDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments?.get("passengers") != null) {
            passengers = arguments?.get("passengers") as ArrayList<PassengerStatus>
        }

        pickedSeatsList = arguments?.get("pickedSeatsList") as ArrayList<Int>
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPassengerDetailBinding.bind(view)

        bottomSheet = view.findViewById(R.id.bottomSheet)

        initViews()
    }

    private fun initViews() {

        if (arguments?.get("fromMoscow").toString() == "true"){
            binding.tvDirection.text =
                "Toshkent-${loadData2(requireContext())}"

        }else{
            binding.tvDirection.text =
                "${loadData(requireContext())}-${loadData2(requireContext())}"

        }

        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        sheetBehavior = BottomSheetBehavior.from(bottomSheet)
        hideBottomSheet()

        passengerDetailAdapter = PassengerDetailAdapter { position ->
            if (sheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN) {
                showBottomSheet()
            } else {
                hideBottomSheet()
            }
            controlBottomSheetActions(position)
        }

        binding.btnNext.setOnClickListener {
            openPaymentFragment()
        }

        passengerDetailAdapter.submitPickedSeatsList(pickedSeatsList)

        refreshAdapter(passengers)
    }

    private fun openPaymentFragment() {
        findNavController().navigate(
            R.id.action_passengerDetailFragment_to_paymentTypeFragment,
            bundleOf(
                "passengerDetails" to passengerDetailAdapter.passengerDetails,
                "departureDate" to arguments?.get("departureDate"),
                "fromMoscow" to arguments?.get("fromMoscow").toString()
            )
        )
    }

    private fun showBottomSheet() {
        sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun hideBottomSheet() {
        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun controlBottomSheetActions(position: Int) {
        val ivClose = bottomSheet.findViewById<ImageView>(R.id.ivClose)
        val edtSurname = bottomSheet.findViewById<EditText>(R.id.edtSurname)
        val edtName = bottomSheet.findViewById<EditText>(R.id.edtName)
        val edtMiddleName = bottomSheet.findViewById<EditText>(R.id.edtMiddleName)
        val edtBirthDate = bottomSheet.findViewById<EditText>(R.id.edtBirthDate)
        val edtDocumentNumber = bottomSheet.findViewById<EditText>(R.id.edtDocumentNumber)
        val btnRegisterOneId = bottomSheet.findViewById<Button>(R.id.btnRegisterOneId)
        val btnAddPassenger = bottomSheet.findViewById<Button>(R.id.btnAddPassenger)
        val rbMale = bottomSheet.findViewById<RadioButton>(R.id.rbMale)
        val rbFemale = bottomSheet.findViewById<RadioButton>(R.id.rbFemale)
        val spinnerDocumentType =
            bottomSheet.findViewById<AppCompatSpinner>(R.id.spinnerDocumentType)
        val spinnerCountry = bottomSheet.findViewById<AppCompatSpinner>(R.id.spinnerCountry)
        val spinnerCity = bottomSheet.findViewById<AppCompatSpinner>(R.id.spinnerCity)

        val resources = requireActivity().resources

        setAdapterToSpinner(
            resources.getStringArray(R.array.documents),
            spinnerDocumentType
        )

        setAdapterToSpinner(
            resources.getStringArray(R.array.countries),
            spinnerCountry
        )

        setAdapterToSpinner(resources.getStringArray(R.array.regions), spinnerCity)

        edtBirthDate.setOnClickListener {
            showCalendarDialog(edtBirthDate)
        }

        btnRegisterOneId.setOnClickListener {
            val dialogView = DialogView { oneID ->
                passengerDetail =
                    PassengerDetail(
                        "Shukurov",
                        "Murodulla",
                        "Sulaymon o'g'li",
                        "06/07/2000",
                        'M',
                        "AC1023456"
                    )
                edtSurname.setText(passengerDetail.surname)
                edtName.setText(passengerDetail.name)
                edtMiddleName.setText(passengerDetail.middleName)
                edtBirthDate.setText(passengerDetail.dateOfBirth)
                edtDocumentNumber.setText(passengerDetail.documentNumber)
                if (passengerDetail.gender == 'M') rbMale.isChecked = true
                else rbFemale.isChecked = true
            }

            dialogView.showOneIDLoginDialog(requireActivity())
        }

        fun clearEdittextAreas() {
            clearEdittextArea(edtSurname)
            clearEdittextArea(edtName)
            clearEdittextArea(edtMiddleName)
            clearEdittextArea(edtBirthDate)
            clearEdittextArea(edtDocumentNumber)
        }

        btnAddPassenger.setOnClickListener {

            val surname = edtSurname.text.toString()
            val name = edtName.text.toString()
            val middleName = edtMiddleName.text.toString()
            val dateOfBirth = edtBirthDate.text.toString()
            val gender = if (rbMale.isChecked) 'M' else 'F'
            val documentNumber = edtDocumentNumber.text.toString()

            val passengerDetail = PassengerDetail(
                surname,
                name,
                middleName,
                dateOfBirth,
                gender,
                documentNumber
            )

            addToPassengerDetails(passengerDetail, position)

            clearEdittextAreas()
            hideBottomSheet()
        }

        ivClose.setOnClickListener {
            hideBottomSheet()
            clearEdittextAreas()
        }
    }

    private fun addToPassengerDetails(passengerDetail: PassengerDetail, position: Int) {
        passengerDetailAdapter.submitDataToDetails(position, passengerDetail)
    }

    private fun clearEdittextArea(editText: EditText) {
        editText.text.clear()
    }

    private fun setAdapterToSpinner(entries: Array<String>, spinner: AppCompatSpinner) {
        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireActivity(), R.layout.item_spinner,
            entries
        )

        spinnerArrayAdapter.setDropDownViewResource(R.layout.item_spinner)
        spinner.adapter = spinnerArrayAdapter
    }

    private fun showCalendarDialog(edtBirthDate: EditText) {
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

        getSelectedDate(edtBirthDate)
    }

    private fun getSelectedDate(edtBirthDate: EditText) {
        mDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val date = "$day/${month + 1}/$year"
            edtBirthDate.setText(date)
        }
    }

    private fun refreshAdapter(passengers: ArrayList<PassengerStatus>) {
        passengerDetailAdapter.submitData(passengers)
        binding.rvPassengerDetail.adapter = passengerDetailAdapter
    }
}