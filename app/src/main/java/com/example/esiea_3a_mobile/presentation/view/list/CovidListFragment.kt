package com.example.esiea_3a_mobile.presentation.view.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea_3a_mobile.R
import com.example.esiea_3a_mobile.data.api.CovidAPI
import com.example.esiea_3a_mobile.data.model.CovidStat
import com.example.esiea_3a_mobile.data.model.Source
import com.example.esiea_3a_mobile.presentation.adapter.CovidListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CovidListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = CovidListAdapter(listOf())
    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            whenStarted {
                recyclerView = view.findViewById(R.id.covid_stats_recyclerview)
                val loadingView: ProgressBar = view.findViewById(R.id.covid_loader)
                val errorView: TextView = view.findViewById(R.id.covid_list_error)
                errorView.visibility = View.GONE

                recyclerView.apply {
                    adapter = this@CovidListFragment.adapter
                    layoutManager = this@CovidListFragment.layoutManager
                }

                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://coronavirusapi-france.now.sh/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val covidApi: CovidAPI = retrofit.create(CovidAPI::class.java)

                val response = withContext(Dispatchers.IO) {
                    covidApi.getCovidListFromApi()
                }

                loadingView.visibility = View.GONE

                adapter.updateList(response.allLiveFranceData)
            }
        }



        /*view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }
}


