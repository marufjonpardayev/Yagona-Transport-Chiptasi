package uz.transport.yagonatransportchiptasi.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.adapter.FromAdapter
import uz.transport.yagonatransportchiptasi.ui.fragment.SearchFragment

class FromActivity : AppCompatActivity() {

    lateinit var recyclerFrom: RecyclerView
    lateinit var edtFrom: EditText
    val fromRegions = ArrayList<String>()
    val items = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_from)
        initViews()
    }

    private fun initViews() {
        recyclerFrom = findViewById(R.id.rvFrom)
        edtFrom = findViewById(R.id.edtFrom)
        getRegions()
        refreshAdapter(fromRegions)

        edtFrom.addTextChangedListener(object : TextWatcher {
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
            refreshAdapter(fromRegions)
        }

        items.clear()
        for (region in fromRegions) {
            if (region.lowercase().startsWith(keyWord.lowercase()))
                items.add(region)
        }

        refreshAdapter(items)
    }

    private fun getRegions() {
        fromRegions.add("Toshkent")
        fromRegions.add("Samarqand")
        fromRegions.add("Sirdaryo")
        fromRegions.add("Buxoro")
        fromRegions.add("Qashqadaryo")
        fromRegions.add("Xorazm")
        fromRegions.add("Qoraqalpogiston")
        fromRegions.add("Andijon")
        fromRegions.add("Namangan")
        fromRegions.add("Fargona")
        fromRegions.add("Jizzax")
        fromRegions.add("Surxandaryo")
        fromRegions.add("Moskva")
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
            putString("region", insertedRegion)
        }.apply()
    }
}