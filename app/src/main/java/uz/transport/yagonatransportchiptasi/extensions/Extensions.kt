package uz.transport.yagonatransportchiptasi.extensions

import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.extensions.Extensions.increaseValue

object Extensions {
    fun TextView.increaseValue(tvMinus: TextView) {
        this.text = "${this.text.toString().toInt() + 1}"
        tvMinus.setBackgroundResource(R.drawable.rounded_textview_blue)
        tvMinus.setTextColor(resources.getColor(R.color.center_color))
    }

    fun TextView.decreaseValue() {
        this.text = "${this.text.toString().toInt() - 1}"
    }

    fun TextView.changeTextColorAndBackground() {
        this.setBackgroundResource(R.drawable.rounded_textview)
        this.setTextColor(resources.getColor(R.color.black))
    }

    fun CheckBox.selectOneAtLeast(checkBox: CheckBox, checkStatus: Boolean) {
        if (checkBox.isChecked) {
            this.isChecked = checkStatus
        } else {
            this.isChecked = true
        }
    }

    fun EditText.isNotEmpty(): Boolean = this.text.isNotEmpty()
}