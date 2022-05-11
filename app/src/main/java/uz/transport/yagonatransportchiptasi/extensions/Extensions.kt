package uz.transport.yagonatransportchiptasi.extensions

import android.content.Context
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.extensions.Extensions.increaseValue
import kotlin.random.Random

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
    fun TextView.isNotEmpty(): Boolean = this.text.isNotEmpty()

    fun loadData(context: Context): String {
        val sharedPreferences =
            context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("region", "Qayerdan?")
        return savedString!!
    }

    fun loadData2(context: Context): String {
        val sharedPreferences =
            context.getSharedPreferences("shared", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("regionto", "Qayerga?")
        return savedString!!
    }

    fun TextView.setDirection(text: String) {
        this.text = text
    }

    fun TextView.setRandom() {
        this.text = Random.nextInt(10, 200).toString()
    }

    fun TextView.setRandomWagonNumber() {
        this.text = Random.nextInt(1, 12).toString()
    }

    fun TextView.getAmount(): Int = this.text.toString().toInt()

    fun ImageView.changeTint(pickedSeats:Int,all:Int) {
        if (pickedSeats<=all){
            this.setColorFilter(R.color.greenn)
        }
    }
}