package com.mic.spinnerproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mic.spinnerproject.viewmodel.TownShipViewModel
import kotlinx.android.synthetic.main.fragment_s_p.*


class SPFragment : Fragment() {
    lateinit var townshipviemodel:TownShipViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_s_p, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        townshipviemodel= ViewModelProvider(this).get(TownShipViewModel::class.java)
        townshipviemodel.readTownship()

        townshipviemodel.getTownship().observe(
            viewLifecycleOwner, Observer {
                //township object data
                var data: MutableList<String> = ArrayList()
                //township arraylist ko  for each nae loop
                it.townships.forEach {
                    data.add(0, it.township_name)
                    sp_text.adapter = context?.let {
                        ArrayAdapter<String>(
                            it,
                            R.layout.support_simple_spinner_dropdown_item,
                            data  //data arraylist ko spinner mar bind
                        )
                    }
                }


            }
        )

        //for spinner item click

        sp_text.onItemSelectedListener=object  :AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(context,sp_text.selectedItem.toString(),Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(context,sp_text.selectedItem.toString(),Toast.LENGTH_LONG).show()
            }

        }
    }
}