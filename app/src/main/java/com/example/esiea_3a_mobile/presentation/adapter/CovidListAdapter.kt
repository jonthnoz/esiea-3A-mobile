package com.example.esiea_3a_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea_3a_mobile.R
import com.example.esiea_3a_mobile.data.model.CovidStat
import javax.xml.transform.ErrorListener

class CovidListAdapter (var dataSet: List<CovidStat>, var listener: ((CovidStat, Int) -> Unit)? = null) :
        RecyclerView.Adapter<CovidListAdapter.ViewHolder>() {

    fun updateList(newList: List<CovidStat>) {
        dataSet = newList
        notifyDataSetChanged()
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView_nom: TextView
        val checkBox_fav: CheckBox

        init {
            // Define click listener for the ViewHolder's View.
            textView_nom = view.findViewById(R.id.nom_textView)
            checkBox_fav = view.findViewById(R.id.check_favorite)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.region_stats_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and
        // add onclicklistener on it
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(dataSet[position], position)
        }

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView_nom.text = dataSet[position].nom
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}
