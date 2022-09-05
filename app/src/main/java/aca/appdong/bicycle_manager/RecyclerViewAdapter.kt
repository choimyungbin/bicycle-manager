package aca.appdong.bicycle_manager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var datas = mutableListOf<UserItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val itemNumber = view.findViewById<TextView>(R.id.tv_number)
        val itemBlock = view.findViewById<TextView>(R.id.tv_block)
        val itemUnit = view.findViewById<TextView>(R.id.tv_unit)
        val itemBarcode = view.findViewById<TextView>(R.id.tv_barcode)
        val itemReside = view.findViewById<TextView>(R.id.tv_reside)


        fun bind(item: UserItem) {
            itemNumber.text = item.number.toString()
            itemBlock.text = item.block.toString()
            itemUnit.text = item.unit.toString()
            itemBarcode.text = item.barcode.toString()
            itemReside.text = item.reside.toString()
        }
    }


}