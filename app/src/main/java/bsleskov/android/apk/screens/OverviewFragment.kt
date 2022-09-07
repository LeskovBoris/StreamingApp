package bsleskov.android.apk.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bsleskov.android.apk.R
import bsleskov.android.apk.data.ArchiveAdapter
import bsleskov.android.apk.data.ArchiveViewModel
import bsleskov.android.apk.data.StreamListAdapter
import bsleskov.android.apk.data.StreamListViewModel

class OverviewFragment : Fragment() {

    private lateinit var  streamListRecyclerView: RecyclerView
    private   var adapter: StreamListAdapter = StreamListAdapter(emptyList())
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.overview)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        progressBar = view.findViewById(R.id.progressBarStream)
        progressBar.visibility = View.VISIBLE
        streamListRecyclerView = view.findViewById(R.id.overview_recycler_view) as RecyclerView
        streamListRecyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        //streamListRecyclerView.layoutManager = LinearLayoutManager(context)
        streamListRecyclerView.setHasFixedSize(true)
        streamListRecyclerView.adapter = adapter

        val streamListViewModel = ViewModelProvider(this).get(StreamListViewModel::class.java)
        streamListViewModel.streamList.observe(viewLifecycleOwner) { streamList ->

            streamList?.let { adapter.setStreamList(it) }
            progressBar.visibility = View.INVISIBLE

        }
        streamListViewModel.getStreamList()


        return view
    }


}
