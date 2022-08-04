package uz.transport.yagonatransportchiptasi.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.transport.yagonatransportchiptasi.databinding.ActivityTicketBinding

class TicketActivity : AppCompatActivity() {
    lateinit var binding: ActivityTicketBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.pdfViewer.fromAsset("ticket.pdf").load()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}