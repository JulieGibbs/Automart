package com.gelostech.automart.fragments


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gelostech.automart.R
import com.gelostech.automart.adapters.NotificationsAdapter
import com.gelostech.automart.commoners.BaseFragment
import com.gelostech.automart.models.Notification
import kotlinx.android.synthetic.main.fragment_notifications.view.*


class NotificationsFragment : BaseFragment() {
    private lateinit var notificationsAdapter: NotificationsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(v: View) {
        v.rv.setHasFixedSize(true)
        v.rv.layoutManager = LinearLayoutManager(activity!!)
        v.rv.itemAnimator = DefaultItemAnimator()

        notificationsAdapter = NotificationsAdapter()
        v.rv.adapter = notificationsAdapter
        v.rv.showShimmerAdapter()

        Handler().postDelayed({
            v.rv.hideShimmerAdapter()
            loadSample()
        }, 2500)
    }

    private fun loadSample() {
        val notif1 = Notification()
        notif1.actionType = "Test drive"
        notif1.summary = "@Mike Otis has booked a test drive for Friday"
        notif1.time = System.currentTimeMillis()
        notif1.avatar = R.drawable.person
        notificationsAdapter.addNotif(notif1)

        val notif2 = Notification()
        notif2.actionType = "Test drive"
        notif2.summary = "@Mike Otis has booked a test drive for Friday"
        notif2.time = System.currentTimeMillis()
        notif2.avatar = R.drawable.person
        notificationsAdapter.addNotif(notif2)

        val notif3 = Notification()
        notif3.actionType = "Test drive"
        notif3.summary = "@Mike Otis has booked a test drive for Friday"
        notif3.time = System.currentTimeMillis()
        notif3.avatar = R.drawable.person
        notificationsAdapter.addNotif(notif3)

        val notif4 = Notification()
        notif4.actionType = "Test drive"
        notif4.summary = "@Mike Otis has booked a test drive for Friday"
        notif4.time = System.currentTimeMillis()
        notif4.avatar = R.drawable.person
        notificationsAdapter.addNotif(notif4)

        val notif5 = Notification()
        notif5.actionType = "Test drive"
        notif5.summary = "@Mike Otis has booked a test drive for Friday"
        notif5.time = System.currentTimeMillis()
        notif5.avatar = R.drawable.person
        notificationsAdapter.addNotif(notif5)

        val notif6 = Notification()
        notif6.actionType = "Test drive"
        notif6.summary = "@Mike Otis has booked a test drive for Friday"
        notif6.time = System.currentTimeMillis()
        notif6.avatar = R.drawable.person
        notificationsAdapter.addNotif(notif6)
    }

}
