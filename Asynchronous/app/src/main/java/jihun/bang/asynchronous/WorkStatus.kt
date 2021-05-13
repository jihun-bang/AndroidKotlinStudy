package jihun.bang.asynchronous

import androidx.lifecycle.MutableLiveData

object WorkStatus {
    val totalTime = MutableLiveData<Long>()
    val workingLog = MutableLiveData<MutableList<String>>()

    init {
        totalTime.value = 0
        workingLog.value = mutableListOf()
    }
}