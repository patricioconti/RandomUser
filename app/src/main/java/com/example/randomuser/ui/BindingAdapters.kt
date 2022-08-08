/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.randomuser.ui

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.Group
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.example.randomuser.R
import com.example.randomuser.viewmodels.RandomUserApiStatus


//BindingAdapter annotation indicates data binder to execute this binding adapter when a View element
// has an imageUrl attribute
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            //Set image while loading and at error
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}


/**
 * This binding adapter displays the [RandomUserApiStatus] of the network request in an image view.
 * When the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: RandomUserApiStatus?) {
    when (status) {
        RandomUserApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        RandomUserApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        RandomUserApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        else -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

/**
 * This binding adapter displays user Data Group or not depending on [RandomUserApiStatus]
 * When the request is loading, or done user Data Group is displayed. If the request has an error,
 * user Data Group is not visible
 *
 */
@BindingAdapter("userDataStatus")
fun bindUserDataStatus(userDataGroup: Group, status: RandomUserApiStatus?) {
    when (status) {
        RandomUserApiStatus.LOADING -> {
            userDataGroup.visibility = View.INVISIBLE

        }
        RandomUserApiStatus.DONE -> {
            userDataGroup.visibility = View.VISIBLE
        }
        RandomUserApiStatus.ERROR -> {
            userDataGroup.visibility = View.INVISIBLE
        }
        else -> {
            userDataGroup.visibility = View.VISIBLE
        }
    }
}


