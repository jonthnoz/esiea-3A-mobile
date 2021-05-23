package com.example.esiea_3a_mobile.presentation.view.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea_3a_mobile.R
import com.example.esiea_3a_mobile.data.model.CovidStat
import com.example.esiea_3a_mobile.data.model.Source
import com.example.esiea_3a_mobile.presentation.adapter.CovidListAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CovidListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    //private val adapter = CovidListAdapter(listOf())
    //private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val test1 = CovidStat("Ain", 10, 40, 300, "date", Source("source"))
        val test2 = CovidStat("Aisne", 11, 41, 301, "date", Source("source" ))
        val test3 = CovidStat("Aisne3", 11, 41, 301, "date", Source("source" ))
        val list = ArrayList<CovidStat>().apply {
            add(test1)
            add(test2)
            add(test3)
        }

        this.recyclerView = view.findViewById(R.id.covid_stats_recyclerview)

        this.recyclerView.apply {
            adapter = CovidListAdapter(list) //this@CovidListFragment.adapter
            layoutManager = LinearLayoutManager(context)//this@CovidListFragment.layoutManager
        }


        /*view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }
}


