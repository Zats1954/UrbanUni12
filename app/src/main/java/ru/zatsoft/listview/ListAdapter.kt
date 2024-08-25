package ru.zatsoft.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(private val context: Context, private val dataList: List<User> ): BaseAdapter()
{
    override fun getCount(): Int {
      return dataList.size
    }

    override fun getItem(position: Int): User{
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View{
        var view = view ?: LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        val data = getItem(position)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvAge = view.findViewById<TextView>(R.id.tvAge)
        tvName.text = data.name
        tvAge.text = data.age.toString()
        return view
    }
}