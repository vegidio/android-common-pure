package io.vinicius.androidcommon.custom

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

open class BaseFragment : Fragment()
{
    val navigation get() = NavHostFragment.findNavController(this)
    private var isBoundViewModel = false

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        // To avoid the clicks on the current fragment to trigger other views underneath
        view?.isFocusable = true

        // Binding the views to the view model
        if(!isBoundViewModel) bindViewModel()
    }

    open fun bindViewModel()
    {
        isBoundViewModel = true
    }

    /*
     * Methods
     */

    fun dismissKeyboard()
    {
        activity?.currentFocus?.let {
            val inputMethod = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethod.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}