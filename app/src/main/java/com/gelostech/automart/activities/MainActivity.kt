package com.gelostech.automart.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.gelostech.automart.R
import com.gelostech.automart.commoners.AppUtils.setDrawable
import com.gelostech.automart.commoners.BaseActivity
import com.gelostech.automart.fragments.*
import com.gelostech.automart.utils.PagerAdapter
import com.mikepenz.ionicons_typeface_library.Ionicons
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import kotlin.math.log

class MainActivity : BaseActivity(), AHBottomNavigation.OnTabSelectedListener,
        AHBottomNavigation.OnNavigationPositionListener, ViewPager.OnPageChangeListener {

    private var doubleBackToExit = false

    private lateinit var drawer: Drawer
    private lateinit var homeFragment: HomeFragment
    private lateinit var partsFragment: PartsFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var notificationsFragment: NotificationsFragment
    private lateinit var chatFragment: ChatFragment

    companion object {
        private const val HOME = "Automart"
        private const val PARTS = "Spare Parts"
        private const val SEARCH = "Search"
        private const val NOTIFICATIONS = "Notifications"
        private const val CHATS = "Chats"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeFragment = HomeFragment()
        partsFragment = PartsFragment()
        searchFragment = SearchFragment()
        notificationsFragment = NotificationsFragment()
        chatFragment = ChatFragment()

        initViews()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = HOME
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        setupBottomNav()
        setupViewPager()
        setupDrawer()
    }

    //Setup the bottom navigation bar
    private fun setupBottomNav() {
        val homeIcon = setDrawable(this, Ionicons.Icon.ion_ios_home, R.color.secondaryText, 18)
        val partsIcon = setDrawable(this, Ionicons.Icon.ion_gear_a, R.color.secondaryText, 18)
        val searchIcon = setDrawable(this, Ionicons.Icon.ion_search, R.color.secondaryText, 18)
        val notificationIcon = setDrawable(this, Ionicons.Icon.ion_ios_bell, R.color.secondaryText, 18)
        val chatIcon = setDrawable(this, Ionicons.Icon.ion_chatbubbles, R.color.secondaryText, 18)

        bottomNav.addItem(AHBottomNavigationItem(HOME, homeIcon))
        bottomNav.addItem(AHBottomNavigationItem(PARTS, partsIcon))
        bottomNav.addItem(AHBottomNavigationItem(SEARCH, searchIcon))
        bottomNav.addItem(AHBottomNavigationItem(NOTIFICATIONS, notificationIcon))
        bottomNav.addItem(AHBottomNavigationItem(CHATS, chatIcon))

        bottomNav.defaultBackgroundColor = Color.WHITE
        bottomNav.inactiveColor = ContextCompat.getColor(this, R.color.inactiveColor)
        bottomNav.accentColor = ContextCompat.getColor(this, R.color.colorPrimary)
        bottomNav.isBehaviorTranslationEnabled = false
        bottomNav.titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE
        bottomNav.setUseElevation(true, 5f)

        bottomNav.setOnTabSelectedListener(this)
        bottomNav.setOnNavigationPositionListener(this)
    }

    //Setup the view pager
    private fun setupViewPager() {
        val adapter = PagerAdapter(supportFragmentManager, this)

        adapter.addAllFrags(homeFragment, partsFragment, searchFragment, notificationsFragment, chatFragment)
        adapter.addAllTitles(HOME, PARTS, SEARCH, NOTIFICATIONS, CHATS)

        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(this)
        viewPager.offscreenPageLimit = 4
    }

    private fun setupDrawer() {
        val accountHeader = AccountHeaderBuilder().withActivity(this)
                .withSelectionListEnabled(false)
                .withHeaderBackground(R.drawable.fozzy)
                .addProfiles(ProfileDrawerItem()
                        .withName("Tirgei")
                        .withEmail("+254726002063")
                        .withIcon(R.drawable.person))
                .build()

        val default = SecondaryDrawerItem().withIdentifier(0).withName("Home").withIcon(Ionicons.Icon.ion_ios_home)
        val orders = SecondaryDrawerItem().withIdentifier(1).withName("Orders").withIcon(Ionicons.Icon.ion_ios_cart)
        val myOrders = SecondaryDrawerItem().withIdentifier(2).withName("My Orders").withIcon(Ionicons.Icon.ion_ios_cart)
        val myProducts = SecondaryDrawerItem().withIdentifier(4).withName("My Uploads").withIcon(Ionicons.Icon.ion_upload)
        val watchlist = SecondaryDrawerItem().withIdentifier(5).withName("Watchlist").withIcon(Ionicons.Icon.ion_star)
        val sales = SecondaryDrawerItem().withIdentifier(6).withName("My sales").withIcon(Ionicons.Icon.ion_android_list)
        val settings = SecondaryDrawerItem().withIdentifier(8).withName("Settings").withIcon(Ionicons.Icon.ion_ios_gear)
        val support = SecondaryDrawerItem().withIdentifier(9).withName("Help & Support").withIcon(Ionicons.Icon.ion_ios_help)
        val exit = SecondaryDrawerItem().withIdentifier(10).withName("Logout").withIcon(Ionicons.Icon.ion_log_out)

        drawer = DrawerBuilder().withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(accountHeader)
                .addDrawerItems(default, DividerDrawerItem(), orders, myOrders, myProducts, watchlist, sales, DividerDrawerItem(), settings, support, exit)
                .withOnDrawerItemClickListener { _, _, drawerItem ->
                    when(drawerItem) {
                        orders -> launchActivity(OrdersActivity::class.java)
                        myProducts -> launchActivity(MyUploadsActivity::class.java)
                        watchlist -> launchActivity(WatchlistActivity::class.java)
                        settings -> launchActivity(SettingsActivity::class.java)
                        exit -> logOut()
                    }
                    true
                }
                .build()
    }

    private fun launchActivity(intentClass: Class<*>) {
        val intent = Intent(this, intentClass)
        startActivity(intent)
        overridePendingTransition(R.anim.enter_b, R.anim.exit_a)

        Handler().postDelayed({
            drawer.closeDrawer()
            drawer.setSelection(0)
        }, 300)

    }

    private fun logOut() {
        drawer.closeDrawer()
        drawer.setSelection(0)

        alert("Are you sure you want to log out?") {
            title = "Log out"
            positiveButton("LOG OUT") { finish() }
            negativeButton("CANCEL") {}
        }.show()
    }

    override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean {
        viewPager.setCurrentItem(position, true)

        when(position) {
            0 -> supportActionBar?.title = HOME
            1 -> supportActionBar?.title = PARTS
            2 -> supportActionBar?.title = SEARCH
            3 -> supportActionBar?.title = NOTIFICATIONS
            4 -> supportActionBar?.title = CHATS
        }

        return true
    }

    override fun onPositionChange(y: Int) {
        viewPager.setCurrentItem(y, true)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        bottomNav.currentItem = position
    }

    override fun onBackPressed() {
        if (doubleBackToExit) {
            super.onBackPressed()
        } else {
            doubleBackToExit = true
            toast("Tap back again to exit")

            Handler().postDelayed({doubleBackToExit = false}, 1500)
        }
    }

}
