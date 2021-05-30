package com.example.esiea_3a_mobile.presentation.view.list

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea_3a_mobile.R
import com.example.esiea_3a_mobile.data.model.CovidStat
import com.example.esiea_3a_mobile.presentation.adapter.CovidListAdapter
import com.example.esiea_3a_mobile.presentation.viewmodel.CovidListViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CovidListFragment: Fragment() {

    private lateinit var adapter: CovidListAdapter

    private val viewModel: CovidListViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_region_list, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loadingView = view.findViewById<ProgressBar>(R.id.covid_loader)
        val errorTextView = view.findViewById<TextView>(R.id.covid_list_error)
        val recyclerView = view.findViewById<RecyclerView>(R.id.covid_stats_recyclerview)
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        adapter = CovidListAdapter(listOf(), ::onClickedRegion)
        recyclerView.apply {
            adapter = this@CovidListFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }

        view.findViewById<Button>(R.id.covid_list_error).setOnClickListener {
            viewModel.reloadList()
        }

        viewModel.getList().observe(viewLifecycleOwner, {
            when(it.status) {
                Status.SUCCESS -> {
                    loadingView.visibility = View.GONE
                    it.data?.let { list -> adapter.updateList(list) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    loadingView.visibility = View.VISIBLE
                    errorTextView.visibility = View.GONE
                }
                Status.ERROR -> {
                    loadingView.visibility = View.GONE
                    errorTextView.visibility = View.VISIBLE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }

            }
        })

        viewModel.reloadList()
    }

    private fun onClickedRegion(item: CovidStat, place: Int) {
        val action = CovidListFragmentDirections.actionNavigateToStats(place, item)
        findNavController().navigate(action)
    }
}


