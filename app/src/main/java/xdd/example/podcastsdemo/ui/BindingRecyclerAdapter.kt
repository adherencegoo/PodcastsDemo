package xdd.example.podcastsdemo.ui

import android.view.View
import androidx.annotation.UiThread
import androidx.recyclerview.widget.RecyclerView

abstract class BindingRecyclerAdapter<D, VH : BindingRecyclerAdapter.BindingVH<D>> : RecyclerView.Adapter<VH>() {
    abstract class BindingVH<D>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(index: Int, data: D)
    }

    private var dataList = listOf<D>()

    final override fun getItemCount(): Int = dataList.size

    final override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(position, dataList[position])

    @UiThread
    fun setData(data: List<D>) {
        dataList = data

        notifyDataSetChanged()//todo
    }
}