package aca.appdong.bicycle_manager

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(var userItem: ArrayList<UserItem>, private val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), Filterable {

    var TAG = "RecyclerViewAdapter"
    var filteredUserItem = ArrayList<UserItem>()
    var itemFilter = ItemFilter()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //        val itemNumber = view.findViewById<TextView>(R.id.tv_number)
//        val itemBlock = view.findViewById<TextView>(R.id.tv_block)
//        val itemUnit = view.findViewById<TextView>(R.id.tv_unit)
//        val itemBarcode = view.findViewById<TextView>(R.id.tv_barcode)
//        val itemReside = view.findViewById<TextView>(R.id.tv_reside)
//        fun bind(item: UserItem) {
//            itemNumber.text = item.number.toString()
//            itemBlock.text = item.block.toString()
//            itemUnit.text = item.unit.toString()
//            itemBarcode.text = item.barcode.toString()
//            itemReside.text = item.reside.toString()
//        }
        lateinit var tv_number: TextView
        lateinit var tv_block: TextView
        lateinit var tv_unit: TextView
        lateinit var tv_barcode: TextView
        lateinit var tv_reside: TextView

        init {
            tv_number = itemView.findViewById(R.id.tv_number)
            tv_block = itemView.findViewById(R.id.tv_block)
            tv_unit = itemView.findViewById(R.id.tv_unit)
            tv_barcode = itemView.findViewById(R.id.tv_barcode)
            tv_reside = itemView.findViewById(R.id.tv_reside)

//            itemView.setOnClickListener {
//                AlertDialog.Builder(context).apply {
//                    var position = adapterPosition
//                    var user = filteredUserItem[position]
//                    setTitle(user.number)
//                    setMessage(user.phoneNumber)
//                    setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
//                        Toast.makeText(context, "OK Button Click", Toast.LENGTH_SHORT).show()
//                    })
//                    show()
//                }
//            }
        }
    }

    init {
        filteredUserItem.addAll(userItem)
    }

    override fun getItemCount(): Int {
        return filteredUserItem.size
    }

    override fun getFilter(): Filter {
        return itemFilter
    }

    inner class ItemFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filterString = charSequence.toString()
            val results = FilterResults()
            Log.d(TAG, "charSequence : $charSequence")

            //검색이 필요없을 경우를 위해 원본 배열을 복제
            val filteredList: ArrayList<UserItem> = ArrayList<UserItem>()
            //공백제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {
                results.values = userItem
                results.count = userItem.size

                return results
                //공백제외 2글자 이인 경우 -> 이름으로만 검색
            } else {
                for (useritem in userItem) {
                    if (useritem.barcode.toString().contains(filterString)) {
                        filteredList.add(useritem)
                    }
                }
                //그 외의 경우(공백제외 2글자 초과) -> 이름/전화번호로 검색
            }
            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
            filteredUserItem.clear()
            filteredUserItem.addAll(filterResults.values as ArrayList<UserItem>)
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userItem: UserItem = filteredUserItem[position]

        holder.tv_number.text = userItem.number.toString()
        holder.tv_block.text = userItem.block.toString()
        holder.tv_unit.text = userItem.unit.toString()
        holder.tv_barcode.text = userItem.barcode.toString()
        holder.tv_reside.text = userItem.reside.toString()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val con = parent.context
        val inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item, parent, false)

        return ViewHolder(view)
    }
}