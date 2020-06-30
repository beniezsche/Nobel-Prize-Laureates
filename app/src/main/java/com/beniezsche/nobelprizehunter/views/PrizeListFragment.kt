package com.beniezsche.nobelprizehunter.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.beniezsche.nobelprizehunter.R
import com.beniezsche.nobelprizehunter.adapter.PrizeListAdapter
import com.beniezsche.nobelprizehunter.models.Prize
import com.beniezsche.nobelprizehunter.viewmodel.PrizeViewModel
import kotlinx.android.synthetic.main.fragment_prize_list.*



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PrizeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PrizeListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var prizeViewModel: PrizeViewModel? = null
    private var prizeListAdapter: PrizeListAdapter? = null

    private var globalCategory = "all"
    private var globalYear = "all"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        prizeViewModel = ViewModelProviders.of(this).get(PrizeViewModel::class.java)
        prizeListAdapter = PrizeListAdapter(context!!)



        return inflater.inflate(R.layout.fragment_prize_list, container, false)
    }

    private fun setSpinners(){

        var categories = ArrayList<String>()

        categories.add("Chemistry")
        categories.add("Physics")
        categories.add("Economics")
        categories.add("Medicine")
        categories.add("Peace")
        categories.add("Literature")

        var categoryAdapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_category.adapter = categoryAdapter


        spinner_category.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                var category = parent!!.getItemAtPosition(position).toString().toLowerCase()
                globalCategory = category

                prizeViewModel!!.getPrizeListData(globalCategory,globalYear).observe(viewLifecycleOwner, Observer {

                    if(it != null){
                        setList(it.prizes!!)
                    }

                })

            }
        })


        var years = ArrayList<String>()

        for((i, x) in (1901 until 2017).withIndex()){

            years.add(i,x.toString())
        }

        var yearAdapter: ArrayAdapter<String> = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, years)
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_year.setAdapter(yearAdapter)

        spinner_year.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                var year = parent!!.getItemAtPosition(position).toString()
                globalYear = year

                prizeViewModel!!.getPrizeListData(globalCategory,globalYear).observe(viewLifecycleOwner, Observer {

                    if(it != null){
                        setList(it.prizes!!)
                    }

                })

            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSpinners()

        rv_prizeList.layoutManager = LinearLayoutManager(context)


        prizeViewModel!!.getPrizeListData(globalCategory,globalYear).observe(viewLifecycleOwner, Observer {

            if(it != null){

                rv_prizeList.adapter = prizeListAdapter
                prizeListAdapter!!.prizeList = it.prizes!! as ArrayList<Prize>
                prizeListAdapter!!.notifyDataSetChanged()
                //setList(it.prizes!!)
            }

        })
    }

    private fun setList(list: List<Prize>){

        prizeListAdapter!!.prizeList.clear()
        prizeListAdapter!!.prizeList = list as ArrayList<Prize>
        prizeListAdapter!!.notifyDataSetChanged()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PrizeListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PrizeListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
