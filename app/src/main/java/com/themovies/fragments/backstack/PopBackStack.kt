package com.themovies.fragments.backstack

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.themovies.fragments.Fragment1
import com.themovies.fragments.Fragment2
import com.themovies.fragments.Fragment3
import com.themovies.fragments.R

class PopBackStack : AppCompatActivity(), View.OnClickListener {

    lateinit var fragmentManager: FragmentManager
    private val TAG = "LIFECYCLE "+PopBackStack::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_back_stack)

        val count = findViewById<TextView>(R.id.stackCount)
        fragmentManager = supportFragmentManager
        fragmentManager.addOnBackStackChangedListener {
            count.text = "Count : ${fragmentManager.backStackEntryCount}"

            val stringBuilder = StringBuilder("Current status of Fragment Transaction Back Stack : \n")
            for (index in fragmentManager.backStackEntryCount - 1 downTo 0) {
                val entry = fragmentManager.getBackStackEntryAt(index)
                stringBuilder.append(" ${entry.name}\n")
            }

            Log.d(TAG, "onCreate: $stringBuilder")
        }

    }


    private fun addFragment() {
        var fragment = fragmentManager.findFragmentById(R.id.container)

        fragment = when (fragment) {
            is Fragment1 -> Fragment2()
            is Fragment2 -> Fragment3()
            else -> Fragment1()
        }

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.addToBackStack("Add $fragment")
        fragmentTransaction.commitNow()
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.btnAdd -> {
                addFragment()
            }
            R.id.btnPop -> {
                fragmentManager.popBackStack()
//              fragmentManager.popBackStack(0,FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
            R.id.btnRemove -> {
                remove()
            }
        }
    }

    private fun remove() {
        val fragment = fragmentManager.findFragmentById(R.id.container)
        if (fragment != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(fragment)
            fragmentTransaction.addToBackStack("Remove $fragment")
            fragmentTransaction.commit()
        } else {
            Toast.makeText(this, "No fragment to remove", Toast.LENGTH_SHORT).show()
        }
    }
}