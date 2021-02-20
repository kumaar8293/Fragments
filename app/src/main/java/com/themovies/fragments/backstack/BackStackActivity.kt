package com.themovies.fragments.backstack

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.themovies.fragments.Fragment1
import com.themovies.fragments.Fragment2
import com.themovies.fragments.Fragment3
import com.themovies.fragments.R
import com.themovies.fragments.logMsg

/**
 * Transaction Back-stack is not the Back-stack of Fragments
 * Transaction Back-stack is Back-stack of Transaction
 */

const val ACTIVITY_NAME = "BackStackActivity"

class BackStackActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    private val TAG = BackStackActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_stack)
        logMsg(" $ACTIVITY_NAME onCreate()")

        val count = findViewById<TextView>(R.id.stackCount)
        fragmentManager = supportFragmentManager
        fragmentManager.addOnBackStackChangedListener {
            count.text = "Count : ${fragmentManager.backStackEntryCount}"

            val stringBuilder = StringBuilder("Current status of Fragment Transaction Back Stack : \n")
            for (index in fragmentManager.backStackEntryCount - 1 downTo 0) {
                Log.d(TAG, "onCreate: $index")

                val entry = fragmentManager.getBackStackEntryAt(index)
                stringBuilder.append(" ${entry.name}\n")
            }

            Log.d(TAG, "onCreate: $stringBuilder")
        }

        findViewById<Button>(R.id.addFragment).setOnClickListener {
            addSameFragmentOnBackPress2()
        }
    }


    private fun addSameFragmentOnBackPress2() {
        var fragment = fragmentManager.findFragmentById(R.id.container)

        fragment = when (fragment) {
            is Fragment1 -> Fragment2()
            is Fragment2 -> Fragment3()
            else -> Fragment1()
        }

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, fragment)
        fragmentTransaction.addToBackStack("Add $fragment")
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        val fragment = fragmentManager.findFragmentById(R.id.container)
        if (fragment != null) {
            fragmentManager.popBackStack()
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.remove(fragment)
//          fragmentTransaction.addToBackStack("Remove $fragment")
//            fragmentTransaction.commit()
        } else
            super.onBackPressed()
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