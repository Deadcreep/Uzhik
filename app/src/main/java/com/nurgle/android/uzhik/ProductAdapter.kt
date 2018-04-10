package com.nurgle.android.uzhik

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.nurgle.android.uzhik.database.Product
import com.squareup.picasso.Picasso


class ProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun getItemCount() = products.size

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder? {
       val context = parent!!.context
       val view = ProductView(context)
       view.layoutParams = RecyclerView.LayoutParams(MATCH_PARENT, 150)
       return ProductViewHolder(view).apply {
           itemView.setOnClickListener {
               val position = adapterPosition
               if (position != RecyclerView.NO_POSITION) {


               }
           }
       }
   }

    override fun onBindViewHolder(holder: ProductViewHolder?, position: Int) {
        if (holder == null) return
        holder.productView.product= products[position]
        //Picasso.get().load(products[position].image).into(holder.imageView);
    }

}