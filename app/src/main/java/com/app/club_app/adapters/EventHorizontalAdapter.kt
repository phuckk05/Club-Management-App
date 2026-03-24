package com.app.club_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.club_app.R
import com.app.club_app.databinding.ItemEventHorizontalBinding
import com.app.club_app.models.EventModel

class EventHorizontalAdapter(
    private val context: Context,
    private val eventList: ArrayList<EventModel>,
) : RecyclerView.Adapter<EventHorizontalAdapter.EventHorizontalViewHolder>() {

    private var onItemClick : OnEventHorizontalClickListener? = null
    //b3 dinh nghia setter cho thuoc tinh
    fun setOnMyItemClickListener(onMyItemClickListener: OnEventHorizontalClickListener) {
        this.onItemClick = onMyItemClickListener
    }
     // Interface định nghĩa các hành động người dùng trên sự kiện nổi bật (horizontal).
    interface OnEventHorizontalClickListener {
        fun onEventCardClicked(view: View, position: Int)
    }

    // Khởi tạo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHorizontalViewHolder {
        val binding = ItemEventHorizontalBinding.inflate(
            LayoutInflater.from(context),parent,false
        )
        return EventHorizontalViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: EventHorizontalViewHolder,
        position: Int,
    ) {
        holder.pos = position

        val event = eventList.get(position)
        //Đổ dữ liệu vào item
        holder.binding.tvEventHorizontalTitle.text = event.eventTitle
        holder.binding.tvEventHorizontalDate.text = event.eventDate
        holder.binding.tvEventHorizontalLocation.text = event.eventLocation
        holder.binding.ivEventHorizontalImage.setImageResource(event.eventImageResId)
        holder.binding.tvEventHorizontalBadgeHot.text = event.eventType

        //Set nếu sự kiện hot
        holder.binding.tvEventHorizontalBadgeHot.visibility =
            if (event.isHot) View.VISIBLE else View.GONE

    }

    inner class EventHorizontalViewHolder(
        public val binding: ItemEventHorizontalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        var pos: Int = 0

        val onclick = View.OnClickListener {
                view: View? ->
            when {
                view?.id == R.id.cardEventHorizontal  -> {
                    onItemClick?.onEventCardClicked(view,pos)
                }
            }
        }
        init {
            binding.cardEventHorizontal.setOnClickListener(onclick)
        }
    }




    override fun getItemCount(): Int = eventList.size
}
