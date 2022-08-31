package aca.appdong.bicycle_manager

import aca.appdong.bicycle_manager.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    lateinit var btnScan: Button
    lateinit var searchInput: EditText
//    lateinit var string: String
    var activityMainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding!!.root)

        btnScan = activityMainBinding!!.buttonScan
        btnScan.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setPrompt("바코드를 읽어주세요")
            integrator.setCameraId(0)
            integrator.setBeepEnabled(true)
            integrator.setOrientationLocked(false)
            integrator.initiateScan()   //스캔
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        searchInput = activityMainBinding!!.searchInput
        Log.d("bar","바코드 확인끝")

        if (result != null){
            if (result.contents == null){
                Toast.makeText(this, "인식 실패", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "스캔완료: "+result.contents, Toast.LENGTH_SHORT).show()
                searchInput.setText(result.contents)
                Log.d("bar", "바코드:${result.contents}")
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}