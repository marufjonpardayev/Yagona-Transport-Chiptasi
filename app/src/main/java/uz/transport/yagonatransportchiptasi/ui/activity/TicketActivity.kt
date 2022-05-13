package uz.transport.yagonatransportchiptasi.ui.activity

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.github.barteksc.pdfviewer.PDFView
import uz.transport.yagonatransportchiptasi.R

class TicketActivity : AppCompatActivity() {

    var fromMoscow = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        fromMoscow = intent.getStringExtra("fromMoscow")!!
        Log.d("", "onCreate: $fromMoscow")

        val pdfView = findViewById<PDFView>(R.id.pdfViewer)
        if (fromMoscow == "true") {
            pdfView.fromAsset("Moskva_Toshkent_Poyezdi.pdf").load()
        } else {
            pdfView.fromAsset("Toshkent_Samarqand_Afrasiyob_chipta.pdf").load()
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}