package com.sixt.coding.task.carsmap

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sixt.coding.task.R
import com.sixt.coding.task.api.repo.CarsRepository
import com.sixt.coding.task.base.BaseViewModel
import com.sixt.coding.task.carslist.CarFragment
import com.sixt.coding.task.di.module.OBSERVER_ON
import com.sixt.coding.task.di.module.SUBCRIBER_ON
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named
import com.google.android.gms.maps.*
import com.google.android.gms.maps.SupportMapFragment

/**
 * A ViewModel used for the {@link MapFragment}.
 */
class MapViewModel @Inject constructor(
    private val carRepository: CarsRepository,
    @param:Named(SUBCRIBER_ON) private val subscriberOn: Scheduler,
    @param:Named(OBSERVER_ON) private val observerOn: Scheduler) : BaseViewModel(), OnMapReadyCallback {

    companion object{
        const val zoom: Float = 12.0f
    }

    private lateinit var mMap: GoogleMap
    val isLoading: MutableLiveData<Boolean?> = MutableLiveData()
    private val errorMessage: MutableLiveData<String?> = MutableLiveData()

    init {
        this.isLoading.value = true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.mMap = googleMap
        getCars()
    }


    fun onFloatingActionButton(v: View) {
        val activity = unwrap(v.context)
        val carFragment = CarFragment()
        val fragmentManager: FragmentManager? = activity?.supportFragmentManager
        val transaction = fragmentManager?.beginTransaction()
        transaction?.addToBackStack(null)
        transaction?.replace(R.id.container, carFragment)
        transaction?.commit()
    }

    fun callMap(activity: MapFragment) {
        val mapFragment: SupportMapFragment? =
            (activity.childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)
        mapFragment?.getMapAsync(this)
    }

    private fun getCars() {
        this.disposable.addAll(this.carRepository.getCars()
            .subscribeOn(subscriberOn)
            .observeOn(observerOn)
            .doOnSubscribe {
                this.isLoading.value = true
            }
            .doOnComplete {
                this.isLoading.value = false
            }
            .doOnError {
                this.isLoading.value = false
            }
            .subscribe(
                { carList ->
                    this.isLoading.value = false
                    for (car in carList) {
                        val latLng = LatLng(car.latitude, car.longitude)
                        mMap.addMarker(MarkerOptions().position(latLng).title(car.name).snippet(car.make))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
                    }
                },
                {
                    it.printStackTrace()
                    this.errorMessage.value = it.message
                    this.isLoading.value = false
                }
            ))
    }

    private fun unwrap(context: Context): AppCompatActivity? {
        var context = context
        while (context !is Activity && context is ContextWrapper) {
            context = context.baseContext
        }
        return context as? AppCompatActivity
    }
}