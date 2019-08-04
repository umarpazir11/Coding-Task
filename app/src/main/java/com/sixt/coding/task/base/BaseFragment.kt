package com.sixt.coding.task.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sixt.coding.task.R
import javax.inject.Inject
import dagger.android.support.AndroidSupportInjection

/**
 * File Description
 *
 *
 * Author: Umer
 */
abstract class BaseFragment<V : ViewModel, D : ViewDataBinding> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: V

    lateinit var binding: D

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected abstract fun getViewModel(): Class<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    fun toolBar(toolBar: Toolbar,title: String,backButton: Boolean){
        if(activity is AppCompatActivity?){
            (activity as? AppCompatActivity)?.setSupportActionBar(toolBar)
            (activity as? AppCompatActivity)?.supportActionBar!!.title = title
        }
        if(backButton)
            toolBar.setNavigationIcon(R.drawable.ic_back_button)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity!!.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
