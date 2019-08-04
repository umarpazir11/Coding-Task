package com.sixt.coding.task.carslist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import com.sixt.coding.task.R
import com.sixt.coding.task.base.BaseFragment
import com.sixt.coding.task.databinding.FragmentCarListBinding

/**
 * A fragment representing a list of Cars.
 */
class CarFragment : BaseFragment<CarsListViewModel, FragmentCarListBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_car_list

    override fun getViewModel(): Class<CarsListViewModel> {
        return CarsListViewModel::class.java
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolBar(toolBar = binding.toolbar,title = getString(R.string.title_fragment_list),backButton = true)

        binding.lifecycleOwner = this
        val layoutManager = LinearLayoutManager(context)
        binding.list.hasFixedSize()
        binding.list.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        binding.viewModel = viewModel
    }

}
