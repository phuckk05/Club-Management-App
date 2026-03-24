package com.app.club_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.club_app.R
import com.app.club_app.databinding.ItemEventVerticalBinding
import com.app.club_app.models.EventModel
import com.app.club_app.models.EventStatus

class EventVerticalAdapter(
    private val context: Context,
    private var eventList: ArrayList<EventModel>
) : RecyclerView.Adapter<EventVerticalAdapter.EventVerticalViewHolder>() {

    private var onItemClick: OnEventVerticalClickListener? = null

    // Setter giống Horizontal
    fun setOnEventVerticalClickListener(listener: OnEventVerticalClickListener) {
        this.onItemClick = listener
    }

    // Interface
    interface OnEventVerticalClickListener {
        fun onEventItemClicked(view: View, position: Int)
        fun onEventStatusBadgeClicked(view: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventVerticalViewHolder {
        val binding = ItemEventVerticalBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return EventVerticalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventVerticalViewHolder, position: Int) {
        holder.pos = position

        val event = eventList[position]

        // Bind data
        holder.binding.tvEventVerticalTitle.text = event.eventTitle
        holder.binding.tvEventVerticalDate.text = event.eventDate
        holder.binding.tvEventVerticalLocation.text = event.eventLocation
        holder.binding.tvEventVerticalType.text = event.eventType
        holder.binding.ivEventVerticalThumbnail.setImageResource(event.eventImageResId)

        // Status badge
        holder.binding.tvEventVerticalStatusBadge.text = event.eventStatus.displayLabel

        val badgeBgRes = when (event.eventStatus) {
            EventStatus.UPCOMING -> R.drawable.bg_badge_status_upcoming
            EventStatus.ATTENDING -> R.drawable.bg_badge_status_attending
            EventStatus.FINISHED -> R.drawable.bg_badge_status_finished
        }

        holder.binding.tvEventVerticalStatusBadge.setBackgroundResource(badgeBgRes)
    }

    inner class EventVerticalViewHolder(
        val binding: ItemEventVerticalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        var pos: Int = 0

        private val onClick = View.OnClickListener { view ->
            when (view.id) {
                R.id.tvEventVerticalStatusBadge -> {
                    onItemClick?.onEventStatusBadgeClicked(view, pos)
                }
                else -> {
                    onItemClick?.onEventItemClicked(view, pos)
                }
            }
        }

        init {
            binding.root.setOnClickListener(onClick)
            binding.tvEventVerticalStatusBadge.setOnClickListener(onClick)
        }
    }

    override fun getItemCount(): Int = eventList.size

    // Update list giống style bạn đang dùng
    fun updateEventList(newList: ArrayList<EventModel>) {
        eventList = newList
        notifyDataSetChanged()
    }
}