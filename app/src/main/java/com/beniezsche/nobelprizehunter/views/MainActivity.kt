package com.beniezsche.nobelprizehunter.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.beniezsche.nobelprizehunter.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var pagerAdapter = DemoCollectionPagerAdapter(supportFragmentManager)
        pager.adapter = pagerAdapter

    }
}

class DemoCollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        if(position == 0){
            val prizeListFragment = PrizeListFragment()
            return prizeListFragment
        }
        else if(position == 1){
            val laureatesWithMoreThan4PrizesListFragment = LaureatesWithMoreThan4PrizesListFragment()
            return laureatesWithMoreThan4PrizesListFragment
        }

        return PrizeListFragment()
    }

    override fun getCount(): Int  = 2

    override fun getPageTitle(position: Int): CharSequence {
        if(position == 0){
            return "Prize List"
        }
        else if(position == 1){
            return "Laureates with >1 win"
        }

        return "Nothing"
    }

}

