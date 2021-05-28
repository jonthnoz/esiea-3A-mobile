package com.example.esiea_3a_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea_3a_mobile.R
import com.example.esiea_3a_mobile.data.model.CovidStat
import com.example.esiea_3a_mobile.presentation.view.details.CovidDetailsFragmentArgs
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

        dataSet[position].favoris = dataSet[position].favoris ?:false
        viewHolder.checkBox_fav.isChecked = dataSet[position].favoris!!
        dataSet[position].initialPosition = dataSet[position].initialPosition ?:position

        viewHolder.checkBox_fav.setOnClickListener {
            var list: ArrayList<CovidStat> = dataSet as ArrayList<CovidStat>

            if (it is CheckBox && !it.isChecked) {
                list = delFav(dataSet, position)
            }
            else {
                list = addFav(dataSet, position)
            }
            updateList(list)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


    private fun delFav(dataSet: List<CovidStat>, position: Int): ArrayList<CovidStat> {
        val list: ArrayList<CovidStat> = dataSet as ArrayList<CovidStat>

        dataSet[position].favoris = false

        list.add(dataSet[position].initialPosition!!+nbFav(dataSet), list.removeAt(position))

        return list
    }

    private fun addFav(dataSet: List<CovidStat>, position: Int): ArrayList<CovidStat> {
        val list: ArrayList<CovidStat> = dataSet as ArrayList<CovidStat>

        dataSet[position].favoris = true

        list.add(0, list.removeAt(position))

        return list
    }

    private fun nbFav(list: List<CovidStat>): Int {
        var total = 0

        for (region: CovidStat in list) {
            total += if (region.favoris == true) 1 else 0
        }
        return total
    }
}

