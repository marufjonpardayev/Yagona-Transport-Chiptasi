package uz.transport.yagonatransportchiptasi.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import uz.transport.yagonatransportchiptasi.databinding.ItemOneIdDialogBinding
import uz.transport.yagonatransportchiptasi.model.OneID

class DialogView(private var onEnterClick: ((OneID) -> Unit)) {

    fun showOneIDLoginDialog(activity: Activity?) {

        val binding = ItemOneIdDialogBinding.inflate(LayoutInflater.from(activity))
        val dialog = Dialog(activity!!)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(pxFromDp(activity, 300).toInt(), pxFromDp(activity, 270).toInt())
        dialog.setCancelable(true)

        binding.btnEnter.setOnClickListener {
            val login = binding.tvOneIDLogin.text
            val password = binding.tvOneIDPassword.text
            if (login!!.isNotEmpty() && password!!.isNotEmpty()) {
                val oneID = OneID(login.toString(), password.toString())
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