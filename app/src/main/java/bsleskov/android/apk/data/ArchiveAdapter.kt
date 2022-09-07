package bsleskov.android.apk.data

import android.annotation.SuppressLint
import android.transition.ArcMotion
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import bsleskov.android.apk.R
import bsleskov.android.apk.model.ArchiveModel
import kotlinx.coroutines.selects.select

const val latitudeKey = "latitude key"
const val longitudeKey = "longitude key"
const val numberKey = "number key"
const val plateCodeKey = "plate code key"


class ArchiveAdapter(var archiveList: List<ArchiveModel>):
    RecyclerView.Adapter<ArchiveAdapter.ArchiveHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.archive_item, parent, false)

        return ArchiveHolder(view)
    }

    override fun onBindViewHolder(holder: ArchiveHolder, position: Int) {
        val archive = archiveList[position]
        holder.apply {
            timeTextView.text = archive.Time
            numberTextView.text = archive.Number
            plateCodeTextView.text = archive.PlateCode
            latitude = archive.Latitude
            longitude = archive.Longitude
        }



    }

    override fun getItemCount(): Int {
        return archiveList.size
    }

    class ArchiveHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var archiveModel: ArchiveModel
        val timeTextView = itemView.findViewById<TextView>(R.id.time_textview)
        val numberTextView = itemView.findViewById<TextView>(R.id.number_textview)
        val plateCodeTextView = itemView.findViewById<TextView>(R.id.plateCode_textview)
        var  latitude:Double = 0.0;
        var longitude: Double = 0.0

        init {
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {


            itemView.findNavController().navigate(R.id.action_archiveFragment_to_mapsFragment,
            bundleOf(latitudeKey to latitude, longitudeKey to longitude, numberKey to numberTextView.text, plateCodeKey to plateCodeTextView.text))
        }




    }



    @SuppressLint("NotifyDataSetChanged")
    fun setArchiveList(list: ArrayList<ArchiveModel>) {
        archiveList = list
        notifyDataSetChanged()
    }
}
