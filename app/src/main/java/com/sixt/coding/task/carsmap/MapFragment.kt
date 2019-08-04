package com.sixt.coding.task.carsmap

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.sixt.coding.task.R
import com.sixt.coding.task.base.BaseFragment
import com.sixt.coding.task.databinding.MapFragmentBinding

class MapFragment : BaseFragment<MapViewModel, MapFragmentBinding>(), LifecycleOwner  {

    override val layoutRes: Int
        get() = R.layout.map_fragment

    override fun getViewModel(): Class<MapViewModel> {
        return MapViewModel::class.java
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolBar(toolBar = binding.toolbar,title = getString(R.string.title_fragment_maps),backButton = false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.callMap(this)
    }

}
