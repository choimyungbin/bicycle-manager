package aca.appdong.bicycle_manager

import aca.appdong.bicycle_manager.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.util.Log
import android.widget.*
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*

class MainActivity : AppCompatActivity() {
    lateinit var btnScan: Button
    lateinit var searchInput: EditText
    lateinit var userItem: UserItem
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    val datas = mutableListOf<UserItem>()
//    lateinit var string: String
    var activityMainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding!!.root)
        initRecycler()
        //userItem.(UserItem(1,101,101,123456, true))
        //더미
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
    private fun initRecycler() {
        recyclerViewAdapter = RecyclerViewAdapter(this)
        mainRecyclerview.adapter = recyclerViewAdapter

        datas.apply {
            add(UserItem(1,101,101,123456, true))

            recyclerViewAdapter.datas = datas
            recyclerViewAdapter.notifyDataSetChanged()
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