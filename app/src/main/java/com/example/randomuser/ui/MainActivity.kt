package com.example.randomuser.ui

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.randomuser.R
import com.example.randomuser.data.repository.RandomUserRepository
import com.example.randomuser.databinding.ActivityMainBinding
import com.example.randomuser.viewmodels.RandomUserApiStatus
import com.example.randomuser.viewmodels.RandomUserViewModel
import com.example.randomuser.viewmodels.RandomUserViewModelFactory

class MainActivity : AppCompatActivity() {

    //Initialize viewModel with delegate property and factory with repository
    private val viewModel: RandomUserViewModel by viewModels {
        val repository = RandomUserRepository()
        RandomUserViewModelFactory(repository)

    }

    //Initialize binding for using viewBinding
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Define Binding an set content view
        //(Remember that the layout must be transformed to layout at xml)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        val view = binding.root
        setContentView(view)

        //Bindings for dataBinding on xml.
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //Observe RandomUserApiStatus
        viewModel.status.observe(this) {
            //If there is an error, handle it
            if (it == RandomUserApiStatus.ERROR) {
                handleError()

            }
        }


    }

    //Show Error Message Toast
    private fun handleError() {
        //Show Toast at the center of the screen
        val toast = Toast.makeText(this, "An Error occurred", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
        toast.show()
    }


}