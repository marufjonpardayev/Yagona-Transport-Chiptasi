package uz.transport.yagonatransportchiptasi.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.appcompat.widget.AppCompatSpinner
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.databinding.ItemOneIdDialogBinding
import uz.transport.yagonatransportchiptasi.model.OneID

class DialogView(private var onEnterClick: ((OneID) -> Unit)) {

    fun showOneIDLoginDialog(activity: Activity?) {

        val binding = ItemOneIdDialogBinding.inflate(LayoutInflater.from(activity))
        val dialog = Dialog(activity!!)
        val resources = activity.resources

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(pxFromDp(activity, 300).toInt(), pxFromDp(activity, 350).toInt())
        dialog.setCancelable(true)

        fun setAdapterToSpinner(entries: Array<String>, spinner: AppCompatSpinner) {
            val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                activity, R.layout.item_spinner,
                entries
            )

            spinnerArrayAdapter.setDropDownViewResource(R.layout.item_spinner)
            spinner.adapter = spinnerArrayAdapter
        }

        setAdapterToSpinner(resources.getStringArray(R.array.privileges), binding.spinnerPrivilege)

        binding.apply {
            checkboxPrivilege.setOnClickListener {
                if (checkboxPrivilege.isChecked) {
                    spinnerPrivilege.visibility = View.VISIBLE
                } else {
                    spinnerPrivilege.visibility = View.GONE
                }
            }
        }

        binding.btnEnter.setOnClickListener {
            val login = binding.tvOneIDLogin.text
            val password = binding.tvOneIDPassword.text



            if (login!!.isNotEmpty() && password!!.isNotEmpty()) {
                val oneID = if (binding.checkboxPrivilege.isChecked) {
                    OneID(
                        login.toString(),
                        password.toString(),
                        binding.spinnerPrivilege.selectedItem.toString()
                    )
                } else {
                    OneID(
                        login.toString(),
                        password.toString()
                    )
                }
                onEnterClick.invoke(oneID)
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    private fun pxFromDp(context: Context, dp: Int): Float {
        return dp * context.resources.displayMetrics.density
    }
}