package uz.transport.yagonatransportchiptasi.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.adapter.FromAdapter

class ToActivity : AppCompatActivity() {
    lateinit var recyclerFrom: RecyclerView
    val toRegions = ArrayList<String>()
    val items = ArrayList<String>()
    lateinit var edtTo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to)
        initViews()
    }


    private fun initViews() {
        recyclerFrom = findViewById(R.id.rvFrom)
        edtTo = findViewById(R.id.edtTo)
        getRegions()
        refreshAdapter(toRegions)

        edtTo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val keyWord = s.toString().trim()
                regionsByKeyWord(keyWord)
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }

    private fun regionsByKeyWord(keyWord: String) {
        if (keyWord.isEmpty()) {
            refreshAdapter(toRegions)
        }

        items.clear()
        for (region in toRegions) {
            if (region.lowercase().startsWith(keyWord.lowercase()))
                items.add(region)
        }
        refreshAdapter(items)
    }

    private fun getRegions() {
        toRegions.add("Tashkent")
        toRegions.add("Samarqand")
        toRegions.add("Sirdaryo")
        toRegions.add("Buxoro")
        toRegions.add("Qashqadaryo")
        toRegions.add("Xorazm")
        toRegions.add("Qoraqalpogiston")
        toRegions.add("Andijon")
        toRegions.add("Namangan")
        toRegions.add("Fargona")
        toRegions.add("Tashkent")
        toRegions.add("Jizzax")
        toRegions.add("Surxandaryo")
    }

    private fun refreshAdapter(regions: ArrayList<String>) {
        val adapter = FromAdapter(regions)
        recyclerFrom.adapter = adapter
        adapter.setOnItemClickListener(object : FromAdapter.RecyclerClickListener {
            override fun onClick(position: Int) {
                saveData(regions[position])
                finish()
            }

        })
    }

    private fun saveData(region: String) {
        val insertedRegion: String = region
        val sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("regionto", insertedRegion)
        }.apply()
    }
}