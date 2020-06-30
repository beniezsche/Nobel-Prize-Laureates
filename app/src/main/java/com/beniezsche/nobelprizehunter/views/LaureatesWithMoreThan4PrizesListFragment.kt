package com.beniezsche.nobelprizehunter.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.beniezsche.nobelprizehunter.R
import com.beniezsche.nobelprizehunter.adapter.LaureateListAdapter
import com.beniezsche.nobelprizehunter.adapter.PrizeListAdapter
import com.beniezsche.nobelprizehunter.models.Laureate
import com.beniezsche.nobelprizehunter.models.Prize
import com.beniezsche.nobelprizehunter.viewmodel.PrizeViewModel
import kotlinx.android.synthetic.main.fragment_laureate_more_4_prize_list.*
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
class LaureatesWithMoreThan4PrizesListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private var prizeViewModel: PrizeViewModel? = null
    private var laureateListAdapter: LaureateListAdapter? = null


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
        laureateListAdapter = LaureateListAdapter()

        return inflater.inflate(R.layout.fragment_laureate_more_4_prize_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_laureateList.layoutManager = LinearLayoutManager(context)


        prizeViewModel!!.getLaureateList().observe(viewLifecycleOwner, Observer {

            if(it != null){

                rv_laureateList.adapter = laureateListAdapter
                laureateListAdapter!!.laureateList = getMoreThanOneWinner(it.laureates as ArrayList<Laureate>)
                laureateListAdapter!!.notifyDataSetChanged()
                //setList(it.prizes!!)
            }

        })


    }

    private fun getMoreThanOneWinner(list: ArrayList<Laureate>): ArrayList<Laureate>{

        var finalList = ArrayList<Laureate>()

        for(laureate in list){
            if(laureate.prizes!!.size > 1){
                finalList.add(laureate)
            }
        }

        return finalList

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
