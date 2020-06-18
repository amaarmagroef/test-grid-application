package evermos.test.utils

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import evermos.test.repo.ApiClient
import evermos.test.viewmodel.MovieViewModel
import javax.inject.Singleton


/**
 * Created by siapaSAYA on 6/18/2020
 */

@Component(
    modules = [
        ApiClient::class,
        MovieViewModel::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class]
)

@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }


    fun inject(appController: AppController)
}