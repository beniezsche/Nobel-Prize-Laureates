package com.beniezsche.nobelprizehunter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beniezsche.nobelprizehunter.R
import com.beniezsche.nobelprizehunter.models.Laureate
import java.util.ArrayList


class LaureateListAdapter: RecyclerView.Adapter<LaureateListAdapter.LaureateViewHolder>() {

    var laureateList = ArrayList<Laureate>()




    inner class LaureateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv_id: TextView = itemView.findViewById(R.id.tv_id)
        var tv_firstname: TextView = itemView.findViewById(R.id.tv_firstname)
        var tv_surname: TextView = itemView.findViewById(R.id.tv_surname)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaureateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_laureate,parent,false)

        return LaureateViewHolder(view)
    }

    override fun getItemCount(): Int {
        return laureateList.size
    }

    override fun onBindViewHolder(holder: LaureateViewHolder, position: Int) {

        holder.tv_id.text = laureateList[position].id.toString()
        holder.tv_firstname.text = laureateList[position].firstname.toString()
        holder.tv_surname.text = laureateList[position].surname.toString()

    }
}