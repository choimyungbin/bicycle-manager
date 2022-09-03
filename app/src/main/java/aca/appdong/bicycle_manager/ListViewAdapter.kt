package aca.appdong.bicycle_manager

import aca.appdong.bicycle_manager.databinding.ItemBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class ListViewAdapter (private val context: Context, val itemList: ArrayList<ListViewItem>): BaseAdapter(){
    override fun getCount(): Int = itemList.size

    override fun getItem(p0: Int): ListViewItem = itemList[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item, null)

        val itemNumber = view.findViewById<TextView>(R.id.tv_number)
        val itemBlock = view.findViewById<TextView>(R.id.tv_block)
        val itemUnit = view.findViewById<TextView>(R.id.tv_unit)
        val itemBarcode = view.findViewById<TextView>(R.id.tv_barcode)
        val itemReside = view.findViewById<TextView>(R.id.tv_reside)


        val data = itemList[p0]
        itemNumber.text = data.number.toString()
        itemBlock.text = data.block.toString()
        itemUnit.text = data.unit.toString()
        itemBarcode.text = data.barcode.toString()
        itemReside.text = data.reside.toString()

        return view
    }

}