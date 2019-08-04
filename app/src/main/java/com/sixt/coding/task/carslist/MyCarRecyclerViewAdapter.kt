package com.sixt.coding.task.carslist

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.sixt.coding.task.R
import com.sixt.coding.task.databinding.FragmentCarBinding
import com.sixt.coding.task.model.Car
import com.bumptech.glide.request.RequestOptions

/**
 * [RecyclerView.Adapter] that can display a [Car]
 */
class MyCarRecyclerViewAdapter : RecyclerView.Adapter<MyCarRecyclerViewAdapter.ViewHolder>() {

    lateinit var context: Context
    private lateinit var carList: List<Car>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FragmentCarBinding>(layoutInflater, R.layout.fragment_car, parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = carList[position]
        holder.binding.tvMake.text = item.make
        holder.binding.tvName.text = item.name

        val sharedOptions = RequestOptions().optionalFitCenter()
            .placeholder(R.drawable.ic_car_placeholder)

        Glide.with(context)
            .load(item.carImageUrl)
            .apply(sharedOptions)
            .into(holder.binding.ivCar)
    }

    override fun getItemCount(): Int {
        return if (::carList.isInitialized) carList.size else 0
    }

    inner class ViewHolder(val binding: FragmentCarBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateCarList(carList: List<Car>) {
        this.carList = carList
        notifyDataSetChanged()
    }
}
