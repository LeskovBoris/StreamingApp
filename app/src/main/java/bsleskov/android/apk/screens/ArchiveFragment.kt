package bsleskov.android.apk.screens

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bsleskov.android.apk.data.ArchiveAdapter
import bsleskov.android.apk.data.ArchiveViewModel
import bsleskov.android.apk.R


class ArchiveFragment: Fragment() {

    private lateinit var  archiveRecyclerView: RecyclerView
    private   var adapter: ArchiveAdapter = ArchiveAdapter(emptyList())
    private lateinit var progressBar: ProgressBar




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_archive, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        archiveRecyclerView = view.findViewById(R.id.archive_recycler_view) as RecyclerView
        archiveRecyclerView.layoutManager = LinearLayoutManager(context)
        archiveRecyclerView.setHasFixedSize(true)
        archiveRecyclerView.adapter = adapter

        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.archive)

        val archiveListViewModel = ViewModelProvider(this).get(ArchiveViewModel::class.java)


        archiveListViewModel.archiveList.observe(viewLifecycleOwner) { archiveList ->

            archiveList?.let { adapter.setArchiveList(it) }
            progressBar.visibility = View.INVISIBLE

        }

        archiveListViewModel.getArchiveList()


        return view
    }


     companion object {
        fun newInstance(): ArchiveFragment {
            return ArchiveFragment()
        }

    }




}
