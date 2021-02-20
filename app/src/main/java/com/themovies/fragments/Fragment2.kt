package com.themovies.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


/**
 * onAttach > OnCreate > onCreateView > onViewCreated > onStart > onResume
 *                          ||
 * > onPause > onStop > onDestroyView > onDestroy > onDetach
 */

private const val STATIC_FRAGMENT = "Fragment2"

class Fragment2 : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logMsg("$STATIC_FRAGMENT onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logMsg("$STATIC_FRAGMENT onCreate()")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logMsg("$STATIC_FRAGMENT onCreateView()")
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logMsg("$STATIC_FRAGMENT onViewCreated()")
    }

    override fun onStart() {
        super.onStart()
        logMsg("$STATIC_FRAGMENT onStart()")
    }

    override fun onResume() {
        super.onResume()
        logMsg("$STATIC_FRAGMENT onResume()")
    }

    override fun onPause() {
        super.onPause()
        logMsg("$STATIC_FRAGMENT onResume()")
    }

    override fun onStop() {
        super.onStop()
        logMsg("$STATIC_FRAGMENT onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logMsg("$STATIC_FRAGMENT onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logMsg("$STATIC_FRAGMENT onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        logMsg("$STATIC_FRAGMENT onDetach()")
    }

    override fun toString(): String {
        return Fragment2::class.simpleName.toString()
    }
}