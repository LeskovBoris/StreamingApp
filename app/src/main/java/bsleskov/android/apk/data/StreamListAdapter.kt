package bsleskov.android.apk.data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import bsleskov.android.apk.R
import bsleskov.android.apk.model.ArchiveModel
import bsleskov.android.apk.model.StreamUserModel

class StreamListAdapter (var streamList: List<StreamUserModel>):
    RecyclerView.Adapter<StreamListAdapter.StreamHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.overview_item, parent, false)

        return StreamHolder(view)
    }

    override fun onBindViewHolder(holder: StreamHolder, position: Int) {
        val stream = streamList[position]
        if(stream.DeviceOnline == 1)
        {
            holder.apply {
                streamNameTextView.text = stream.Name
            }
        }
    }

    override fun getItemCount(): Int {
        return streamList.size
    }

    class StreamHolder(view: View) : RecyclerView.ViewHolder(view) {
        val streamNameTextView = itemView.findViewById<TextView>(R.id.stream_name_textview)

        init {
//            itemView.setOnClickListener(this)
        }


//        override fun onClick(v: View?) {
//
//
//            itemView.findNavController().navigate(
//                R.id.action_archiveFragment_to_mapsFragment,
//                bundleOf(latitudeKey to latitude, longitudeKey to longitude, numberKey to numberTextView.text, plateCodeKey to plateCodeTextView.text)
//            )
//        }




    }


    @SuppressLint("NotifyDataSetChanged")
    fun setStreamList(list: ArrayList<StreamUserModel>) {
        streamList = list
        notifyDataSetChanged()
    }
}

