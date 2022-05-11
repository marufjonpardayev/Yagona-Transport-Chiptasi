package uz.transport.yagonatransportchiptasi.ui.activity

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.github.barteksc.pdfviewer.PDFView
import uz.transport.yagonatransportchiptasi.R

class TicketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        val pdfView = findViewById<PDFView>(R.id.pdfViewer)
        pdfView.fromAsset("ticket.pdf").load()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}