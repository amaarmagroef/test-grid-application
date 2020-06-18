package evermos.test.utils

import dagger.Module
import dagger.android.ContributesAndroidInjector
import evermos.test.view.MainActivity


/**
 * Created by siapaSAYA on 6/18/2020
 */

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}