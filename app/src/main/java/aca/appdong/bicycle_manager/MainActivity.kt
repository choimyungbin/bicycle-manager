package aca.appdong.bicycle_manager

import aca.appdong.bicycle_manager.databinding.ActivityMainBinding
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*

class MainActivity : AppCompatActivity() {
    lateinit var btnScan: Button
    lateinit var searchInput: EditText
    lateinit var userItem: ArrayList<UserItem>
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var search_view: SearchView
    lateinit var mainRecyclerview: RecyclerView
//    lateinit var string: String
    var activityMainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding!!.root)

        mainRecyclerview = findViewById(R.id.mainRecyclerview)
        search_view = findViewById(R.id.search_view)
        search_view.setOnQueryTextListener(searchViewTextListener)

        userItem = tempUserItem()
        setAdapter()
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

    var searchViewTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            //텍스트 입력/수정시에 호출
            override fun onQueryTextChange(s: String): Boolean {
                recyclerViewAdapter.filter.filter(s)
                Log.d(TAG, "SearchVies Text is changed : $s")
                return false
            }
        }
    fun setAdapter(){
        //리사이클러뷰에 리사이클러뷰 어댑터 부착
        mainRecyclerview.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter(userItem, this)
        mainRecyclerview.adapter = recyclerViewAdapter
    }
    fun tempUserItem(): ArrayList<UserItem> {
        var tempUserItem = ArrayList<UserItem>()
        tempUserItem.add(UserItem(1,101,101,121212,true))
        tempUserItem.add(UserItem(2,201,102,131313,true))
        tempUserItem.add(UserItem(3,301,103,242424,true))
        tempUserItem.add(UserItem(4,401,104,353535,true))
        tempUserItem.add(UserItem(5,501,105,767676,true))
        return tempUserItem
    }
//    private fun initRecycler() {
//        recyclerViewAdapter = RecyclerViewAdapter(this)
//        mainRecyclerview.adapter = recyclerViewAdapter
//
//        datas.apply {
//            add(UserItem(1,101,101,123456, true))
//
//            recyclerViewAdapter.datas = datas
//            recyclerViewAdapter.notifyDataSetChanged()
//        }
//
//    }

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