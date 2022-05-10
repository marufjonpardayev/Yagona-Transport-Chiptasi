package uz.transport.yagonatransportchiptasi.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.transport.yagonatransportchiptasi.R
import uz.transport.yagonatransportchiptasi.adapter.FromAdapter

class ToActivity : AppCompatActivity() {
    lateinit var recyclerFrom: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to)
        initViews()
    }


    private fun initViews() {
        recyclerFrom = findViewById(R.id.rvFrom)
        recyclerFrom.layoutManager = GridLayoutManager(this, 1)
        refreshAdapter(getRegions())

    }

    private fun getRegions(): ArrayList<String> {
        val regions = ArrayList<String>()
        regions.add("Tashkent")
        regions.add("Samarqand")
        regions.add("Sirdaryo")
        regions.add("Buxoro")
        regions.add("Qashqadaryo")
        regions.add("Xorazm")
        regions.add("Qoraqalpogiston")
        regions.add("Andijon")
        regions.add("Namangan")
        regions.add("Fargona")
        regions.add("Tashkent")
        regions.add("Jizzax")
        regions.add("Surxandaryo")
        return regions

    }

    private fun refreshAdapter(regions: ArrayList<String>) {
        val adapter = FromAdapter(regions)
        recyclerFrom.adapter = adapter
        adapter.setOnItemClickListener(object :FromAdapter.RecyclerClickListener{
            override fun onClick(position: Int) {
                saveData(regions[position])
                finish()
            }

        })
    }

    private fun saveData(region:String) {
        val insertedRegion:String=region
        val sharedPreferences=getSharedPreferences("shared", Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.apply{
            putString("regionto",insertedRegion)
        }.apply()
    }
}