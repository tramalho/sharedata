package com.example.tramalho.sharedata

import androidx.fragment.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tramalho.sharedata.databinding.DialogFragmentBinding

class MyDialogFragment : DialogFragment() {

    lateinit var viewModel : DialogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var dataBinding = DataBindingUtil.inflate<DialogFragmentBinding>(
            LayoutInflater.from(context),
            R.layout.dialog_fragment,
            container,
            false
        )

        viewModel = ViewModelProviders.of(activity!!).get(ActivityViewModel::class.java)

        dataBinding.viewModel = viewModel

        return dataBinding.root

    }


    companion object {

        fun new(num: Int): MyDialogFragment {

            val f = MyDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putInt("num", num)
            f.setArguments(args)

            return f
        }
    }

}