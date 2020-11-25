package xdd.example.podcastsdemo.ui

import android.view.View
import androidx.annotation.UiThread
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

abstract class BindingRecyclerAdapter<D, VH : BindingRecyclerAdapter.BindingVH<D>>(lifecycleOwner: LifecycleOwner)
    : RecyclerView.Adapter<VH>() {

    abstract class BindingVH<D>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(index: Int, data: D)
    }

    private var dataList = listOf<D>()
    private val weakOwner = WeakReference(lifecycleOwner)
    protected val lifecycleOwner: LifecycleOwner?
        get() = weakOwner.get()

    final override fun getItemCount(): Int = dataList.size

    final override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(position, dataList[position])

    @UiThread
    fun setData(data: List<D>) {
        dataList = data

        notifyDataSetChanged()//todo
    }
}