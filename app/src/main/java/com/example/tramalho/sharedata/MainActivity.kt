package com.example.tramalho.sharedata

import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tramalho.sharedata.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        viewModel.activityData.observe(this, Observer<String> {

            val textview = TextView(this)
            textview.text = it
            container.addView(textview)
        })

        viewModel.close.observe(this, Observer<Int> {

            val dialogsTag = arrayListOf<String>(
                MyDialogFragment::class.java.simpleName,
                EditHourDialogFragment::class.java.simpleName
            )

            dialogsTag.forEach {
                supportFragmentManager.findFragmentByTag(it)?.let {
                        (it as DialogFragment).dismiss()
                    }
            }

            binding.executePendingBindings()
        })

        viewModel.navigate.observe(this, Observer<Int> {

            val result = when(it) {
                1 ->  MyDialogFragment.new(it)
                else -> EditHourDialogFragment.new(it)
            }

            result.show(supportFragmentManager, result::class.java.simpleName)
        })

        viewModel.refreshAct.observe(this, Observer<Int> {
            binding.executePendingBindings()
        })

        binding.viewModel = viewModel

        viewModel.init()
    }

    override fun onResume() {
        super.onResume()
        binding.executePendingBindings()
    }
}
