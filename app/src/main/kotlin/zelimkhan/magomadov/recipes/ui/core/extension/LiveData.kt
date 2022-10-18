package zelimkhan.magomadov.recipes.ui.core.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

val <T> MutableLiveData<T>.asLiveData get(): LiveData<T> = this