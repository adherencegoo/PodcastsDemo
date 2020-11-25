package xdd.example.podcastsdemo.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import xdd.example.podcastsdemo.databinding.OverviewFragmentBinding

class OverviewFragment : Fragment() {

    companion object {
        fun newInstance() = OverviewFragment()
    }

    private lateinit var fragmentBinding: OverviewFragmentBinding
    private lateinit var viewModel: OverviewViewModel
    private lateinit var overviewAdapter: OverviewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        fragmentBinding = OverviewFragmentBinding.inflate(inflater, container, false)
        fragmentBinding.lifecycleOwner = this
        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        overviewAdapter = OverviewAdapter(this, viewModel)

        fragmentBinding.overviewRecycler.apply {
            adapter = overviewAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.apply {
            liveOverviewList.observe(this@OverviewFragment, Observer {
                overviewAdapter.setData(it)
            })

            liveErrorMsg.observe(this@OverviewFragment, Observer { msg ->
                Snackbar.make(fragmentBinding.root, msg, Snackbar.LENGTH_LONG)
            })
        }


        viewModel.updateOverviews()
    }

}