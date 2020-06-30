package com.beniezsche.nobelprizehunter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beniezsche.nobelprizehunter.R
import com.beniezsche.nobelprizehunter.models.Laureate
import com.beniezsche.nobelprizehunter.models.Prize
import java.util.*


class PrizeListAdapter(context: Context): RecyclerView.Adapter<PrizeListAdapter.PrizeViewHolder>() {

    var prizeList = ArrayList<Prize>()
    private var context: Context? = null

    init {
        this.context = context
    }



    inner class PrizeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv_year: TextView = itemView.findViewById(R.id.tv_year)
        var tv_category: TextView = itemView.findViewById(R.id.tv_category)
        var tv_motivation: TextView = itemView.findViewById(R.id.tv_motivation)
        var rv_laureates: RecyclerView = itemView.findViewById(R.id.rv_laureates)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrizeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prize,parent,false)

        return PrizeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return prizeList.size
    }

    override fun onBindViewHolder(holder: PrizeViewHolder, position: Int) {

        val prize = prizeList[position]

        holder.tv_year.text = prize.year.toString()
        holder.tv_category.text = prize.category!![0].toUpperCase().toString() + prize.category!!.substring(1)

        holder.tv_motivation.text = prize.laureates!![0].motivation

        setLaureateList(holder,prizeList[position])

    }

    fun setLaureateList(holder: PrizeViewHolder, prize: Prize){

        var adapter = LaureateListAdapter()
        adapter.laureateList = prize.laureates as ArrayList<Laureate>
        holder.rv_laureates.layoutManager = LinearLayoutManager(context)
        holder.rv_laureates.adapter = adapter

        holder.rv_laureates.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener{
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

            }

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val action = e.action
                when (action) {
                    MotionEvent.ACTION_MOVE -> rv.parent
                        .requestDisallowInterceptTouchEvent(true)
                }
                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

            }


        })

    }
}