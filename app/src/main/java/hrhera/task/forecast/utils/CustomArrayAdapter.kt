package com.window.wndo.utils.arrayadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import hrhera.task.forecast.R

class CustomArrayAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int= android.R.layout.simple_list_item_1,
    @IdRes private val textViewResourceId: Int = 0,
    private val values: List<ModelDisplayName>
) :
    ArrayAdapter<ModelDisplayName>(context, layoutResource, values) {

    override fun getItem(position: Int): ModelDisplayName = values[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val view = createViewFromResource(convertView, parent, layoutResource)
        val view = createViewFromResource(convertView, parent, layoutResource)
        bindData(values[position], view)
//        return bindData(getItem(position), view)
//        return bindData(values[position], view)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = createViewFromResource(convertView, parent, android.R.layout.simple_spinner_dropdown_item)

//        return bindData(getItem(position), view)
        return bindData(values[position], view)
    }

    private fun createViewFromResource(convertView: View?, parent: ViewGroup, layoutResource: Int): TextView {
        val context = parent.context
        val view = convertView ?: LayoutInflater.from(context).inflate(layoutResource, parent, false)
        return try {
            if (textViewResourceId == 0) view as TextView
            else {
                view.findViewById(textViewResourceId) ?: throw RuntimeException(
                    "Failed to find view with ID " +
                            "${context.resources.getResourceName(textViewResourceId)} in item layout"
                )
            }
        } catch (ex: ClassCastException) {
            throw IllegalStateException(
                "ArrayAdapter requires the resource ID to be a TextView", ex
            )
        }
    }

    private fun bindData(value: ModelDisplayName, view: TextView): TextView {
        view.text = value.name
        return view
    }
}