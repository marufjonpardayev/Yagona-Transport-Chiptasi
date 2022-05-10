package uz.transport.yagonatransportchiptasi.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView
import uz.transport.yagonatransportchiptasi.R

class TicketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        val pdfView = findViewById<PDFView>(R.id.pdfViewer)
        pdfView.fromAsset("ticket.pdf").load()
    }
}