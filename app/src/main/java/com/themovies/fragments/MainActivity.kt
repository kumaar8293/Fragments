package com.themovies.fragments

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.themovies.fragments.backstack.BackStackActivity
import com.themovies.fragments.backstack.PopBackStack


/**
 * OnCreate > onStart > onResume > onPause > onStop > onDestroy
 */
const val TAG = "LIFECYCLE"
const val ACTIVITY_NAME = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logMsg("$ACTIVITY_NAME onCreate()")

        val count = findViewById<TextView>(R.id.stackCount)
        fragmentManager = supportFragmentManager
        fragmentManager.addOnBackStackChangedListener {
            count.text = "Count : ${fragmentManager.backStackEntryCount}"
        }

        findViewById<Button>(R.id.addFragment).setOnClickListener {
            // addFragment()  // Step-1
            //  addFragmentNew()  // Step-1
            // addFragmentWithStack()  //Step - 2
//             addDifferentFragmentWithBackStack()  //Step - 3
//              addSameFragmentOnBackPress()  //Step - 4  check OnBackPress
            addSameFragmentOnBackPress2()  //Step - 5  check OnBackPress
        }

        findViewById<Button>(R.id.openSecondActivity).setOnClickListener {
//            startActivity(Intent(this, BackStackActivity::class.java))
            startActivity(Intent(this, PopBackStack::class.java))
        }
    }

    private fun addFragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, StaticFragment())
//      fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }


    var count = 0
    private fun addFragmentNew() {
        val fragment = if (count == 0) {
            count++
            StaticFragment()
        } else {
            Fragment1()
        }

        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.add(R.id.container, fragment)
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }

    private fun addFragmentWithStack() {
        val fragment = if (count == 0) {
            count++
            StaticFragment()
        } else {
            Fragment1()
        }
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
//        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun addDifferentFragmentWithBackStack() {

        val fragment = when (fragmentManager.backStackEntryCount) {
            1 -> Fragment2()
            2 -> Fragment3()
            else -> Fragment1()
        }

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        //Back stack is important when we want to inflate multiple fragment on the same container
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun addSameFragmentOnBackPress() {

        val fragment = when (fragmentManager.backStackEntryCount) {
            1 -> Fragment2()
            2 -> Fragment3()
            else -> Fragment1()
        }

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, fragment)
//        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }

    private fun addSameFragmentOnBackPress2() {
        var fragment = fragmentManager.findFragmentById(R.id.container)

        fragment = when (fragment) {
            is Fragment1 -> Fragment2()
            is Fragment2 -> Fragment3()
            else -> Fragment1()
        }

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
//        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.addToBackStack(null) //BAWAL
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        val fragment = fragmentManager.findFragmentById(R.id.container)
        if (fragment != null) {
            val ft = fragmentManager.beginTransaction()
            ft.remove(fragment)
            ft.addToBackStack(null)
            ft.commit()
        } else {
            super.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        logMsg("$ACTIVITY_NAME onStart()")
    }

    override fun onResume() {
        super.onResume()
        logMsg("$ACTIVITY_NAME onResume()")
    }

    override fun onPause() {
        super.onPause()
        logMsg("$ACTIVITY_NAME onPause()")
    }

    override fun onStop() {
        super.onStop()
        logMsg("$ACTIVITY_NAME onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        logMsg("$ACTIVITY_NAME onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logMsg(" $ACTIVITY_NAME onDestroy()")
    }
}

fun logMsg(msg: String) {
    Log.d(TAG, msg)
}