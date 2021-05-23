package com.example.esiea_3a_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea_3a_mobile.R
import com.example.esiea_3a_mobile.data.model.CovidStat
import javax.xml.transform.ErrorListener

class CovidListAdapter (private var dataSet: List<CovidStat>, var listener: ((CovidStat) -> Unit)? = null) :
        RecyclerView.Adapter<CovidListAdapter.ViewHolder>() {

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView_nom: TextView
            val textView_deces: TextView
            val textView_reanimation: TextView
            val textView_gueris: TextView


            init {
                // Define click listener for the ViewHolder's View.
                textView_nom = view.findViewById(R.id.nom_textView)
                textView_deces = view.findViewById(R.id.deces_textView)
                textView_gueris = view.findViewById(R.id.gueris_textView)
                textView_reanimation = view.findViewById(R.id.reanimation_textView)
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

            viewHolder.itemView.setOnClickListener {
                listener?.invoke(dataSet[position])
            }
            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.textView_nom.text = dataSet[position].nom
            viewHolder.textView_deces.text = "décès: " + dataSet[position].deces.toString()
            viewHolder.textView_reanimation.text = "réanimations: " + dataSet[position].reanimation.toString()
            viewHolder.textView_gueris.text = "guéris: " + dataSet[position].gueris.toString()
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size

        fun updateList(newList: List<CovidStat>) {
            dataSet = newList
            notifyDataSetChanged()
        }

}
