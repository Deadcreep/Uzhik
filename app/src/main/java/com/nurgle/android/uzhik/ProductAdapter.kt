package com.nurgle.android.uzhik

import android.net.sip.SipSession
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import android.content.Context;

/**
 * Created by Nurgle on 01.03.2018.
 */

class ProductAdapter(
        private val products: List<String>
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun getItemCount() = products.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProductViewHolder {
        var i = 1
        val context = parent!!.context
        val view = ProductView(context)
        view.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150)
        return ProductViewHolder(view).apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val name = products[position]
                    Toast.makeText(context, name, Toast.LENGTH_LONG)
                    Log.d("CLICK", "CLICK CLICK number $i on item $name" )
                    i+=1

                }
            }
        }
        }

    override fun onBindViewHolder(holder: ProductViewHolder?, position: Int) {
        if (holder == null) return
        holder.view.text = products[position]
    }

}