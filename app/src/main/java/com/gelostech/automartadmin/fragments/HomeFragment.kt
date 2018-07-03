package com.gelostech.automartadmin.fragments


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gelostech.automartadmin.R
import com.gelostech.automartadmin.adapters.CarsAdapter
import com.gelostech.automartadmin.commoners.BaseFragment
import com.gelostech.automartadmin.models.Car
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : BaseFragment(), CarsAdapter.OnItemClick {
    private lateinit var carsAdapter: CarsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(v: View) {
        v.rv.setHasFixedSize(true)
        v.rv.layoutManager = LinearLayoutManager(activity)
        v.rv.itemAnimator = DefaultItemAnimator()

        carsAdapter = CarsAdapter(activity!!, this)
        v.rv.adapter = carsAdapter
        v.rv.showShimmerAdapter()

        Handler().postDelayed({
            v.rv.hideShimmerAdapter()
            loadSample()
        }, 2500)
    }

    private fun loadSample() {
        val car1 = Car()
        car1.make = "Subaru"
        car1.model = "Forester"
        car1.price = 700000
        car1.holderAvatar = R.drawable.person
        car1.holderImage = R.drawable.fozzy
        car1.year = 2004
        car1.transmission = "Manual"
        car1.mileage = 123000
        car1.location = "Mombasa rd"
        car1.sellerName = "Skyline Motors"
        car1.time = System.currentTimeMillis()
        carsAdapter.addCar(car1)

        val car2 = Car()
        car2.make = "Subaru"
        car2.model = "Forester"
        car2.price = 700000
        car2.holderAvatar = R.drawable.person
        car2.holderImage = R.drawable.fozzy
        car2.year = 2004
        car2.transmission = "Manual"
        car2.mileage = 123000
        car2.location = "Mombasa rd"
        car2.sellerName = "Skyline Motors"
        car2.time = System.currentTimeMillis()
        carsAdapter.addCar(car2)

        val car3 = Car()
        car3.make = "Subaru"
        car3.model = "Forester"
        car3.price = 700000
        car3.holderAvatar = R.drawable.person
        car3.holderImage = R.drawable.fozzy
        car3.year = 2004
        car3.transmission = "Manual"
        car3.mileage = 123000
        car3.location = "Mombasa rd"
        car3.sellerName = "Skyline Motors"
        car3.time = System.currentTimeMillis()
        carsAdapter.addCar(car3)

        val car4 = Car()
        car4.make = "Subaru"
        car4.model = "Forester"
        car4.price = 700000
        car4.holderAvatar = R.drawable.person
        car4.holderImage = R.drawable.fozzy
        car4.year = 2004
        car4.transmission = "Manual"
        car4.mileage = 123000
        car4.location = "Mombasa rd"
        car4.sellerName = "Skyline Motors"
        car4.time = System.currentTimeMillis()
        carsAdapter.addCar(car4)

        val car5 = Car()
        car5.make = "Subaru"
        car5.model = "Forester"
        car5.price = 700000
        car5.holderAvatar = R.drawable.person
        car5.holderImage = R.drawable.fozzy
        car5.year = 2004
        car5.transmission = "Manual"
        car5.mileage = 123000
        car5.location = "Mombasa rd"
        car5.sellerName = "Skyline Motors"
        car5.time = System.currentTimeMillis()
        carsAdapter.addCar(car5)

        val car6 = Car()
        car6.make = "Subaru"
        car6.model = "Forester"
        car6.price = 700000
        car6.holderAvatar = R.drawable.person
        car6.holderImage = R.drawable.fozzy
        car6.year = 2004
        car6.transmission = "Manual"
        car6.mileage = 123000
        car6.location = "Mombasa rd"
        car6.sellerName = "Skyline Motors"
        car6.time = System.currentTimeMillis()
        carsAdapter.addCar(car6)

        val car7 = Car()
        car7.make = "Subaru"
        car7.model = "Forester"
        car7.price = 700000
        car7.holderAvatar = R.drawable.person
        car7.holderImage = R.drawable.fozzy
        car7.year = 2004
        car7.transmission = "Manual"
        car7.mileage = 123000
        car7.location = "Mombasa rd"
        car7.sellerName = "Skyline Motors"
        car7.time = System.currentTimeMillis()
        carsAdapter.addCar(car7)

        val car8 = Car()
        car8.make = "Subaru"
        car8.model = "Forester"
        car8.price = 700000
        car8.holderAvatar = R.drawable.person
        car8.holderImage = R.drawable.fozzy
        car8.year = 2004
        car8.transmission = "Manual"
        car8.mileage = 123000
        car8.location = "Mombasa rd"
        car8
        car8.time = System.currentTimeMillis()
        carsAdapter.addCar(car8)

        val car9 = Car()
        car9.make = "Subaru"
        car9.model = "Forester"
        car9.price = 700000
        car9.holderAvatar = R.drawable.person
        car9.holderImage = R.drawable.fozzy
        car9.year = 2004
        car9.transmission = "Manual"
        car9.mileage = 123000
        car9.location = "Mombasa rd"
        car9.time = System.currentTimeMillis()
        carsAdapter.addCar(car9)

        val car10 = Car()
        car10.make = "Subaru"
        car10.model = "Forester"
        car10.price = 700000
        car10.holderAvatar = R.drawable.person
        car10.holderImage = R.drawable.fozzy
        car10.year = 2004
        car10.transmission = "Manual"
        car10.mileage = 123000
        car10.location = "Mombasa rd"
        car10.time = System.currentTimeMillis()
        carsAdapter.addCar(car10)

    }

    override fun onItemClick(car: Car) {

    }
}
