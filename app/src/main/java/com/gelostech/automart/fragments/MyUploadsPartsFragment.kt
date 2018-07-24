package com.gelostech.automart.fragments


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gelostech.automart.R
import com.gelostech.automart.activities.PartActivity
import com.gelostech.automart.adapters.PartsAdapter
import com.gelostech.automart.callbacks.PartCallback
import com.gelostech.automart.commoners.AppUtils
import com.gelostech.automart.commoners.BaseFragment
import com.gelostech.automart.commoners.K
import com.gelostech.automart.models.Part
import com.gelostech.automart.utils.RecyclerFormatter
import com.gelostech.automart.utils.hideView
import com.gelostech.automart.utils.showView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_my_parts.*
import kotlinx.android.synthetic.main.fragment_my_parts.view.*
import timber.log.Timber

class MyUploadsPartsFragment : BaseFragment(), PartCallback {
    private lateinit var partsAdapter: PartsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_parts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)

        loadParts()
    }

    private fun loadParts() {
        getFirestore().collection(K.PARTS)
                //.orderBy(K.TIMESTAMP, Query.Direction.DESCENDING)
                .whereEqualTo("sellerId", getUid())
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    if (firebaseFirestoreException != null) {
                        Timber.e("Error fetching parts $firebaseFirestoreException")
                        noParts()
                    }

                    if (querySnapshot == null || querySnapshot.isEmpty) {
                        noParts()
                    } else {
                        hasParts()

                        for (docChange in querySnapshot.documentChanges) {

                            when(docChange.type) {
                                DocumentChange.Type.ADDED -> {
                                    val part = docChange.document.toObject(Part::class.java)
                                    partsAdapter.addPart(part)
                                }

                                DocumentChange.Type.MODIFIED -> {

                                }

                                DocumentChange.Type.REMOVED -> {

                                }

                            }

                        }

                    }
                }
    }

    private fun initViews(v: View) {
        rv.setHasFixedSize(true)
        rv.layoutManager = GridLayoutManager(activity!!, 2)
        rv.itemAnimator = DefaultItemAnimator()
        rv.addItemDecoration(RecyclerFormatter.GridItemDecoration(activity!!, 2, 10))

        partsAdapter = PartsAdapter(this)
        rv.adapter = partsAdapter
        rv.showShimmerAdapter()

        Handler().postDelayed({
            v.rv.hideShimmerAdapter()
        }, 2500)
    }

    private fun hasParts() {
        rv.hideShimmerAdapter()
        empty.hideView()
        rv.showView()
    }

    private fun noParts() {
        rv.hideShimmerAdapter()
        rv.hideView()
        empty.showView()
    }

    override fun onClick(v: View, part: Part) {
        val i = Intent(activity, PartActivity::class.java)
        i.putExtra(K.PART, part)
        startActivity(i)
        AppUtils.animateFadein(activity!!)
    }
}
