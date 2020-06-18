package evermos.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.Module
import evermos.test.BuildConfig
import evermos.test.model.ModelImage
import evermos.test.repo.ApiClient
import evermos.test.repo.ApiRoute
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


/**
 * Created by siapaSAYA on 6/18/2020
 */

@Module
class MovieViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    private val _images = MutableLiveData<List<ModelImage.Result>>()
    val images: LiveData<List<ModelImage.Result>>
        get() = _images


    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _isrefresh = MutableLiveData<Boolean>()
    val isrefresh: LiveData<Boolean>
        get() = _isrefresh

    private val _lastpage = MutableLiveData<Int>()
    val lastpage : LiveData<Int>
        get() = _lastpage

    private val _currentpage = MutableLiveData<Int>()
    val currentpage : LiveData<Int>
        get() = _currentpage

    private val _category = MutableLiveData<Int>()
    val category : LiveData<Int>
        get() = _category

    private val _totalpage = MutableLiveData<Int>()
    val totalpage : LiveData<Int>
        get() = _totalpage


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private var disposables : Disposable? = null
    private var mApiRoute : ApiRoute? = null
    private var mApiKey = BuildConfig.API_KEY_MOVIE_DB


    init {
        setApiConfig()
        _category.value = 0
        getImages()
    }

    @Inject
    fun setApiConfig () {
        mApiRoute = ApiClient.GetClient(BuildConfig.BASE_URL)!!.create(ApiRoute::class.java)
    }

    fun setCategory(pos : Int){
        _category.value = pos
        getImages()
    }

    @Inject
    fun setObservable(page : Int): Observable<ModelImage?> {
        when(_category.value){
            0 -> {
                return mApiRoute!!.getPopular(mApiKey, page)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            1 -> {
                return mApiRoute!!.getTopRated(mApiKey, page)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            2 -> {
                return mApiRoute!!.getUpcoming(mApiKey, page)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            else -> {
                return mApiRoute!!.getPopular(mApiKey, page)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

    }

    @Singleton
    fun getImages(){
        _isLoading.value = true
        _currentpage.value = 1
        _lastpage.value = 1
        _isrefresh.value = true

        val dataObservable: Observable<ModelImage?> = setObservable(_currentpage.value!!)
        disposables?.dispose()

        launch {
            disposables = dataObservable.subscribe({
                _isLoading.value = false
                _isrefresh.value = false
                _error.value = false
                _images.value = it?.results
                _lastpage.value = it?.totalPages!!
                _totalpage.value = it.totalResults

            },{
                _isLoading.value = false
                _isrefresh.value = false
                _error.value = true
                _lastpage.value = 1
                _totalpage.value = 0
                _currentpage.value = 1

            },{

            })
        }
    }

    fun loadNextListImages(){
        if(_currentpage.value!!<_lastpage.value!!) {
            _isLoading.value = true
            val nextPage = _currentpage.value!! + 1
            _currentpage.value = nextPage
            val dataObservable: Observable<ModelImage?> = setObservable(_currentpage.value!!)
            disposables?.dispose()
            launch {
                disposables = dataObservable.subscribe({
                    _isLoading.value = false
                    _error.value = false
                    _images.value = it?.results
                    _lastpage.value = it?.totalPages!!

                }, {
                    _isLoading.value = false
                    _error.value = true
                }, {

                })
            }
        } else
            return
    }

}