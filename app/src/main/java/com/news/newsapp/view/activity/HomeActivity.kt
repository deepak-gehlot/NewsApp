package com.news.newsapp.view.activity

import android.animation.Animator
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.LinearLayout

import com.news.newsapp.R
import com.news.newsapp.view.fragment.ContentFragment

import java.util.ArrayList

import io.codetail.animation.ViewAnimationUtils
import yalantis.com.sidemenu.interfaces.Resourceble
import yalantis.com.sidemenu.interfaces.ScreenShotable
import yalantis.com.sidemenu.model.SlideMenuItem
import yalantis.com.sidemenu.util.ViewAnimator


class HomeActivity : AppCompatActivity(), ViewAnimator.ViewAnimatorListener {

    private var drawerLayout: DrawerLayout? = null
    private var drawerToggle: ActionBarDrawerToggle? = null
    private val list = ArrayList<SlideMenuItem>()
    private var contentFragment: ContentFragment? = null
    private var viewAnimator: ViewAnimator<*>? = null
    private var res = R.color.colorAccent
    private var linearLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        contentFragment = ContentFragment.newInstance(R.drawable.content_music)
        supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, contentFragment)
                .commit()
        drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawerLayout!!.setScrimColor(Color.TRANSPARENT)
        linearLayout = findViewById<View>(R.id.left_drawer) as LinearLayout
        linearLayout!!.setOnClickListener { drawerLayout!!.closeDrawers() }

        setActionBar()
        createMenuList()
        viewAnimator = ViewAnimator(this, list, contentFragment, drawerLayout, this)

    }

    private fun createMenuList() {
        val menuItem0 = SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close)
        list.add(menuItem0)
        val menuItem = SlideMenuItem(ContentFragment.BUILDING, R.drawable.icn_1)
        list.add(menuItem)
        val menuItem2 = SlideMenuItem(ContentFragment.BOOK, R.drawable.icn_2)
        list.add(menuItem2)
        val menuItem3 = SlideMenuItem(ContentFragment.PAINT, R.drawable.icn_3)
        list.add(menuItem3)
        val menuItem4 = SlideMenuItem(ContentFragment.CASE, R.drawable.icn_4)
        list.add(menuItem4)
        val menuItem5 = SlideMenuItem(ContentFragment.SHOP, R.drawable.icn_5)
        list.add(menuItem5)
        val menuItem6 = SlideMenuItem(ContentFragment.PARTY, R.drawable.icn_6)
        list.add(menuItem6)
        val menuItem7 = SlideMenuItem(ContentFragment.MOVIE, R.drawable.icn_7)
        list.add(menuItem7)
    }


    private fun setActionBar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        drawerToggle = object : ActionBarDrawerToggle(
                this, /* host Activity */
                drawerLayout, /* DrawerLayout object */
                toolbar, /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state.  */
            override fun onDrawerClosed(view: View?) {
                super.onDrawerClosed(view)
                linearLayout!!.removeAllViews()
                linearLayout!!.invalidate()
            }

            override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                if (slideOffset > 0.6 && linearLayout!!.childCount == 0)
                    viewAnimator!!.showMenuContent()
            }

            /** Called when a drawer has settled in a completely open state.  */
            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
            }
        }
        drawerLayout!!.setDrawerListener(drawerToggle)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle!!.onConfigurationChanged(newConfig)
    }

    private fun replaceFragment(screenShotable: ScreenShotable, topPosition: Int): ScreenShotable {
        this.res = if (this.res == R.color.colorAccent) R.color.colorPrimary else R.color.colorAccent
        val view = findViewById<View>(R.id.content_frame)
        val finalRadius = Math.max(view.width, view.height)
        val animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0f, finalRadius.toFloat())
        animator.interpolator = AccelerateInterpolator()
        animator.duration = ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION.toLong()

        findViewById<View>(R.id.content_overlay).background = BitmapDrawable(resources, screenShotable
                .bitmap)
        animator.start()
        val contentFragment = ContentFragment.newInstance(this.res)
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, contentFragment).commit()
        return contentFragment
    }

    override fun onSwitch(slideMenuItem: Resourceble, screenShotable: ScreenShotable, position: Int): ScreenShotable {
        when (slideMenuItem.name) {
            ContentFragment.CLOSE -> return screenShotable
            else -> return replaceFragment(screenShotable, position)
        }
    }

    override fun disableHomeButton() {
        supportActionBar!!.setHomeButtonEnabled(false)

    }

    override fun enableHomeButton() {
        supportActionBar!!.setHomeButtonEnabled(true)
        drawerLayout!!.closeDrawers()

    }

    override fun addViewToContainer(view: View) {
        linearLayout!!.addView(view)
    }
}