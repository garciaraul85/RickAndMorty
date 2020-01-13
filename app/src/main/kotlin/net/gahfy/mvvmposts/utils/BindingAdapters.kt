package net.gahfy.mvvmposts.utils

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import net.gahfy.mvvmposts.utils.extension.getParentActivity
import android.R
import android.support.v7.widget.DividerItemDecoration
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
    val itemDecoration = DividerItemDecoration(view.context, LinearLayout.VERTICAL)
    view.addItemDecoration(itemDecoration)
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View,  visibility: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView,  text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

// important code for loading image here
@BindingAdapter("avatar")
fun loadImage(imageView: ImageView, imageURL: String) {
    Glide.with(imageView.getContext())
            .setDefaultRequestOptions(RequestOptions()
                    .circleCrop())
            .load(imageURL)
            .placeholder(R.drawable.ic_btn_speak_now)
            .into(imageView)
}