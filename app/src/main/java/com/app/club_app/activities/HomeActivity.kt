package com.app.club_app.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.club_app.R
import com.app.club_app.adapters.EventHorizontalAdapter
import com.app.club_app.adapters.EventVerticalAdapter
import com.app.club_app.databinding.ActivityHomeBinding
import com.app.club_app.models.EventModel
import com.app.club_app.models.EventStatus

class HomeActivity : AppCompatActivity(),
    EventHorizontalAdapter.OnEventHorizontalClickListener,
    EventVerticalAdapter.OnEventVerticalClickListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var eventHorizontalAdapter: EventHorizontalAdapter
    private lateinit var eventVerticalAdapter: EventVerticalAdapter

    /** Toàn bộ danh sách sự kiện sắp tới (dùng để lọc theo tab) */
    private val allUpcomingEventList: ArrayList<EventModel> by lazy { buildUpcomingEventList() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()
        setupFeaturedEventRecyclerView()
        setupUpcomingEventRecyclerView()
        setupFilterTabListeners()
        setupBottomNavigation()
    }
    //Setup action bar
    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayShowCustomEnabled(true)
            setDisplayShowTitleEnabled(false)

            val view = layoutInflater.inflate(R.layout.item_actrion_bar, null)
            setCustomView(view)
        }
    }

    // ========== SETUP ==========

    private fun setupFeaturedEventRecyclerView() {
        val featuredList = buildFeaturedEventList()

        eventHorizontalAdapter = EventHorizontalAdapter(this, featuredList)
        val layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvHorizontalEvents.layoutManager = layoutManager
        binding.rvHorizontalEvents.adapter = eventHorizontalAdapter


        eventHorizontalAdapter.setOnMyItemClickListener(object :
            EventHorizontalAdapter.OnEventHorizontalClickListener {
            override fun onEventCardClicked(
                view: View,
                position: Int
            ) {
                TODO("Not yet implemented")
            }

        }
        )
    }

    private fun setupUpcomingEventRecyclerView() {
        val featuredList = buildFeaturedEventList()

        eventVerticalAdapter = EventVerticalAdapter(this, featuredList)
        val layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvVerticalEvents.layoutManager = layoutManager
        binding.rvVerticalEvents.adapter = eventVerticalAdapter

        eventVerticalAdapter.setOnEventVerticalClickListener(object : EventVerticalAdapter.OnEventVerticalClickListener {
            override fun onEventItemClicked(view: View, position: Int) {
                TODO("Not yet implemented")
            }

            override fun onEventStatusBadgeClicked(view: View, position: Int) {
                TODO("Not yet implemented")
            }
        }
        )
     }

    private fun setupFilterTabListeners() {
        binding.tvTabAll.setOnClickListener { selectTab(TabFilter.ALL) }
        binding.tvTabUpcoming.setOnClickListener { selectTab(TabFilter.UPCOMING) }
        binding.tvTabAttending.setOnClickListener { selectTab(TabFilter.ATTENDING) }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> true
                R.id.nav_events -> true
                R.id.nav_notifications -> true
                R.id.nav_profile -> true
                else -> false
            }
        }
    }

    // ========== TAB FILTER ==========

    private enum class TabFilter { ALL, UPCOMING, ATTENDING }

    private fun selectTab(selectedTab: TabFilter) {
        val tabViews = listOf(binding.tvTabAll, binding.tvTabUpcoming, binding.tvTabAttending)
        val tabFilters = listOf(TabFilter.ALL, TabFilter.UPCOMING, TabFilter.ATTENDING)

        tabViews.forEachIndexed { index, tabView ->
            val isSelected = tabFilters[index] == selectedTab
            tabView.setBackgroundResource(
                if (isSelected) R.drawable.bg_tab_selected else R.drawable.bg_tab_unselected
            )
            tabView.setTextAppearance(
                if (isSelected) R.style.TextAppearance_App_TabSelected
                else R.style.TextAppearance_App_TabUnselected
            )
        }

        val filteredList = when (selectedTab) {
            TabFilter.ALL -> allUpcomingEventList
            TabFilter.UPCOMING -> ArrayList(allUpcomingEventList.filter {
                it.eventStatus == EventStatus.UPCOMING
            })
            TabFilter.ATTENDING -> ArrayList(allUpcomingEventList.filter {
                it.eventStatus == EventStatus.ATTENDING
            })
        }
        eventVerticalAdapter.updateEventList(filteredList)
    }


    // ========== SAMPLE DATA ==========

    private fun buildFeaturedEventList(): ArrayList<EventModel> = arrayListOf(
        EventModel(
            eventId = 1,
            eventTitle = "Hội thảo Công nghệ Thường niên",
            eventDate = "25 Th10",
            eventLocation = "Hội trường chính",
            eventImageResId = R.drawable.ic_launcher_background,
            eventStatus = EventStatus.UPCOMING,
            eventType = "Hội thảo",
            isHot = true
        ),
        EventModel(
            eventId = 2,
            eventTitle = "Cuộc thi Lập trình mở rộng",
            eventDate = "28 Th10",
            eventLocation = "Lab Kỹ thuật A",
            eventImageResId = R.drawable.ic_launcher_background,
            eventStatus = EventStatus.UPCOMING,
            eventType = "Cuộc thi",
            isHot = false
        ),
        EventModel(
            eventId = 3,
            eventTitle = "Triển lãm Sản phẩm Sinh viên",
            eventDate = "01 Th11",
            eventLocation = "Sảnh A",
            eventImageResId = R.drawable.ic_launcher_background,
            eventStatus = EventStatus.UPCOMING,
            eventType = "Triển lãm",
            isHot = true
        )
    )

    private fun buildUpcomingEventList(): ArrayList<EventModel> = arrayListOf(
        EventModel(
            eventId = 4,
            eventTitle = "Workshop Thiết kế UX",
            eventDate = "05 Th11 • 14:00",
            eventLocation = "Phòng 402, Khu thiết kế",
            eventImageResId = R.drawable.ic_launcher_background,
            eventStatus = EventStatus.UPCOMING,
            eventType = "Hội thảo"
        ),
        EventModel(
            eventId = 5,
            eventTitle = "Marathon Lập trình",
            eventDate = "Hôm nay • 09:00 – 21:00",
            eventLocation = "Lab Kỹ thuật A",
            eventImageResId = R.drawable.ic_launcher_background,
            eventStatus = EventStatus.ATTENDING,
            eventType = "Thực hành"
        ),
        EventModel(
            eventId = 6,
            eventTitle = "Cà phê & Kết nối",
            eventDate = "20 Th10 • 19:00",
            eventLocation = "Campus Cafe",
            eventImageResId = R.drawable.ic_launcher_background,
            eventStatus = EventStatus.FINISHED,
            eventType = "Giao lưu"
        ),
        EventModel(
            eventId = 7,
            eventTitle = "Buổi chia sẻ kỹ năng mềm",
            eventDate = "10 Th11 • 08:30",
            eventLocation = "Hội trường B2",
            eventImageResId = R.drawable.ic_launcher_background,
            eventStatus = EventStatus.UPCOMING,
            eventType = "Kỹ năng"
        ),
        EventModel(
            eventId = 8,
            eventTitle = "Ngày hội Tuyển dụng Mùa thu",
            eventDate = "15 Th11 • 08:00 – 17:00",
            eventLocation = "Sân vận động trường",
            eventImageResId = R.drawable.ic_launcher_background,
            eventStatus = EventStatus.UPCOMING,
            eventType = "Nghề nghiệp"
        )
    )

    override fun onEventCardClicked(
        view: View,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun onEventItemClicked(view: View, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onEventStatusBadgeClicked(view: View, position: Int) {
        TODO("Not yet implemented")
    }


}