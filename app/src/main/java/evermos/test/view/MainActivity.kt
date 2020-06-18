package evermos.test.view

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import evermos.test.R
import evermos.test.model.ModelKategori
import evermos.test.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var adapters : AdapterImage = AdapterImage()

    private var adapterKategori : AdapterKategori = AdapterKategori()

    private var isLoading = false

    val mLayoutManager : GridLayoutManager = GridLayoutManager(this,3)

    private val viewModel by lazy { ViewModelProvider(this).get(MovieViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)
        configureRecyclerView()
        configureTabLayout(0)
        bindableViewModel()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = mLayoutManager.itemCount
                val lastVisibleItem = mLayoutManager.findLastVisibleItemPosition()
                if((totalItemCount>0) && (totalItemCount <= lastVisibleItem+6) && (!isLoading)){
                    viewModel.loadNextListImages()
                }
            }
        })
        buttonfollow.setOnClickListener {
            if (TextUtils.equals(buttonfollow.text.toString().toLowerCase(Locale.ROOT),
                    "following")){
                buttonfollow.text = "Follow"
                followingscount.text = "34"
            } else if (TextUtils.equals(buttonfollow.text.toString().toLowerCase(Locale.ROOT),
                    "follow")){
                buttonfollow.text = "Following"
                followingscount.text = "35"
            }
        }
        swipeRefresh.setOnRefreshListener {
            viewModel.getImages()
        }
    }

    private fun bindableViewModel(){
        observe(viewModel.images){
            it?.let{
                if (it!=null) {
                    adapters.update(it)
                    if(adapters.itemCount==0){
                        toastMessage("tidak ada data")
                    }
                }
                else {
                    adapters.clear()
                    toastMessage("Terjadi kesalahan")
                }
            }
        }

        observe(viewModel.isLoading){
            isLoading = it!!
        }

        observe(viewModel.isrefresh){
            swipeRefresh.post {
                swipeRefresh.isRefreshing = it!!
            }
        }

        observe(viewModel.totalpage){
            configureTabLayout(it!!)
        }

        observe(viewModel.error){
            if(it!!){
                toastMessage("Terjadi Kesalahan")
                adapters.clear()
            }
        }
    }

    private fun toastMessage(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun configureRecyclerView(){
        recyclerView.apply {
            layoutManager = mLayoutManager
            setHasFixedSize(true)
            isNestedScrollingEnabled = true
            adapter = this@MainActivity.adapters
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
            addItemDecoration(SpacesItemDecoration(
                resources.getDimensionPixelSize(R.dimen.spacing_item_decoration)))
        }
        adapters.clear()

        recyclerCategory.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = true
            adapter = adapterKategori
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        val modelKategori = mutableListOf(
            ModelKategori("Popular", true),
            ModelKategori("Top Rated", false),
            ModelKategori("Upcoming", false)
        )
        adapterKategori.update(modelKategori)
        adapterKategori.setOnClickCategory(object : AdapterKategori.OnClickCategory{
            override fun onUpdate(pos: Int) {
                adapters.clear()
                viewModel.setCategory(pos)
            }

        })

    }

    private fun configureTabLayout(count : Int){
        tabLayout.removeAllTabs()
        tabLayout.addTab(tabLayout.newTab().apply {
            setIcon(R.drawable.ic_photo_camera)
            text = count.toString()
        })
        tabLayout.addTab(tabLayout.newTab().apply {
            setIcon(R.drawable.ic_add_circle_outline)
            text = "0"
        })


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon!!.setTint(Color.parseColor("#232323"))
                if(tab.position == 0){
                    recyclerView.visibility = View.VISIBLE
                } else
                    recyclerView.visibility = View.GONE
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon!!.setTint(Color.parseColor("#4D232323"))
            }
        })
    }

    private inline fun <T> LifecycleOwner.observe(data: LiveData<T>, crossinline block: (T?) -> Unit) {
        data.observe(this, Observer { block(it) })
    }

    class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.left = space
            outRect.top = space

        }

    }
}
