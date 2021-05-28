package com.example.esiea_3a_mobile.presentation.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.esiea_3a_mobile.R
import com.example.esiea_3a_mobile.data.model.CovidStat
import com.example.esiea_3a_mobile.presentation.viewmodel.CovidListViewModel
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CovidDetailsFragment() : Fragment() {

    private val args: CovidDetailsFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_back_to_list).setOnClickListener {
            findNavController().navigate(R.id.action_BackToList)
        }

        initElements(view)
    }

    private fun initElements(view: View) {
        val region: CovidStat = args.item
        var newId: Int = region.initialPosition!!
        if (newId < 94) {
            newId += 1
        } else { newId = 0 }
        if (newId < 20){
            newId -= 1
        }
        val twRea = view.findViewById<TextView>(R.id.textview_detail_reanimation)
        val twNom = view.findViewById<TextView>(R.id.textview_detail_nom)
        val twDate = view.findViewById<TextView>(R.id.textview_detail_date)
        val twSource = view.findViewById<TextView>(R.id.textview_detail_source)
        val twHosp = view.findViewById<TextView>(R.id.textview_detail_hospitalisation)
        val twGueri = view.findViewById<TextView>(R.id.textview_detail_gueris)
        val twDeces = view.findViewById<TextView>(R.id.textview_detail_deces)
        val iwCarte = view.findViewById<ImageView>(R.id.imageview_map)

        val url = "https://github.com/jonthnoz/esiea-3A-mobile/blob/master/images/$newId.png?raw=true"

        twRea.text = region.reanimation.toString()
        twNom.text = region.nom
        twDate.text = region.date.substring(0, 11)
        twSource.text = region.source.sourceNom
        twHosp.text = "Hospitalisés: " + region.hospitalises.toString()
        twGueri.text = "Guéris: " + region.gueris.toString()
        twDeces.text = "Décès: " + region.deces.toString()
        twRea.text = "Réanimation: " + region.reanimation.toString()

        Picasso.get()
            .load(url)
            .fit()
            .into(iwCarte)
    }
}