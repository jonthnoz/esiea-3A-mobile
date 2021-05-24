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

    //private val viewModel: CovidListViewModel by viewModels()
    val args: CovidDetailsFragmentArgs by navArgs()

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

        initElements(view, args.item)

        //val imgView = view.findViewById<ImageView>(R.id.imageview_map)
        /*Picasso.get()
            .load("https://github.com/jonthnoz/esiea-3A-mobile/tree/master/images/160_1.png")
            .fit()
            .into(imgView)
        if (img)
        val url = "https://github.com/jonthnoz/esiea-3A-mobile/blob/master/images/"+imageId+".png?raw=true"
        val ivBasicImage = view.findViewById(R.id.imageview_map) as ImageView
        Picasso.get().load(imageUri).fit().into(ivBasicImage)*/
    }

    fun initElements(view: View, region: CovidStat) {
        val rea_tw = view.findViewById<TextView>(R.id.textview_detail_reanimation)
        val nom_tw = view.findViewById<TextView>(R.id.textview_detail_nom)
        val date_tw = view.findViewById<TextView>(R.id.textview_detail_date)
        val source_tw = view.findViewById<TextView>(R.id.textview_detail_source)
        val hosp_tw = view.findViewById<TextView>(R.id.textview_detail_hospitalisation)
        val gueri_tw = view.findViewById<TextView>(R.id.textview_detail_gueris)
        val deces_tw = view.findViewById<TextView>(R.id.textview_detail_deces)
        val carte_iv = view.findViewById<ImageView>(R.id.imageview_map)

        val url = "https://github.com/jonthnoz/esiea-3A-mobile/blob/master/images/" + args.place.toString() + ".png?raw=true"

        rea_tw.text = "Réanimation: " + region.reanimation.toString()
        nom_tw.text = region.nom
        date_tw.text = region.date
        source_tw.text = region.source.nom
        hosp_tw.text = "Hospitalisés: " + region.hospitalises.toString()
        gueri_tw.text = "Guéris: " + region.gueris.toString()
        deces_tw.text = "Décès: " + region.deces.toString()
        rea_tw.text = "Réanimation: " + region.reanimation.toString()

        Picasso.get().load(url).fit().into(carte_iv)
    }
}