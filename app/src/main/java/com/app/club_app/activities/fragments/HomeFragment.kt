
package com.app.club_app.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.club_app.R

import com.app.club_app.adapters.EventHorizontalAdapter
import com.app.club_app.adapters.EventVerticalAdapter

import com.app.club_app.databinding.FragmentHomeBinding
import com.app.club_app.models.EventModel
import com.app.club_app.models.EventStatus

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var eventHorizontalAdapter: EventHorizontalAdapter
    private lateinit var eventVerticalAdapter: EventVerticalAdapter
    private lateinit var upcomingEventList: ArrayList<EventModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // init data
        upcomingEventList = listUpcomingEvent()

        // setup UI
        setEventHorizontal()
        setEventVertical()

        return binding.root
    }

    private fun setEventHorizontal() {
        eventHorizontalAdapter = EventHorizontalAdapter(requireContext(), listEvent())

        binding.rvHorizontalEvents.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvHorizontalEvents.adapter = eventHorizontalAdapter
    }

    private fun setEventVertical() {
        eventVerticalAdapter = EventVerticalAdapter(requireContext(), upcomingEventList)

        binding.rvVerticalEvents.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rvVerticalEvents.adapter = eventVerticalAdapter
    }

    // DATA
    private fun listEvent(): ArrayList<EventModel> = arrayListOf(
        EventModel(1,"Hội thảo Công nghệ","25 Th10","Hội trường",
            R.drawable.ic_launcher_background, EventStatus.UPCOMING,"Hội thảo",true),
        EventModel(2,"Hội thảo Công nghệ","25 Th10","Hội trường",
            R.drawable.ic_launcher_background, EventStatus.UPCOMING,"Hội thảo",true),
        EventModel(3,"Hội thảo Công nghệ","25 Th10","Hội trường",
            R.drawable.ic_launcher_background, EventStatus.UPCOMING,"Hội thảo",true)

    )

    private fun listUpcomingEvent(): ArrayList<EventModel> = arrayListOf(
        EventModel(2,"Workshop UX","05 Th11","Phòng 402",R.drawable.ic_launcher_background,EventStatus.UPCOMING,"Hội thảo")
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}