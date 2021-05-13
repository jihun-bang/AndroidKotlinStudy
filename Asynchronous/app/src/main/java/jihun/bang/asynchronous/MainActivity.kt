package jihun.bang.asynchronous

import android.os.AsyncTask.THREAD_POOL_EXECUTOR
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import jihun.bang.asynchronous.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    }
    private val workingLog = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(binding.btnSync) {
            setOnClickListener {
                reset()
                working()
                calling()
            }
        }
        with(binding.btnAsyncTask) {
            setOnClickListener {
                reset()
                MyAsyncTask().executeOnExecutor(THREAD_POOL_EXECUTOR, ::working)
                MyAsyncTask().executeOnExecutor(THREAD_POOL_EXECUTOR, ::calling)
            }
        }
        with(binding.btnRxKotlin) {
            setOnClickListener {
                reset()
                val working = Observable.create<Unit> {
                    working()
                }
                val calling = Observable.create<Unit> {
                    calling()
                }
                working.subscribeOn(Schedulers.io()).subscribe()
                calling.subscribeOn(Schedulers.io()).subscribe()
            }
        }
        with(binding.btnCoroutine) {
            setOnClickListener {
                val dispatcher = Dispatchers.IO
                reset()
                CoroutineScope(dispatcher).launch {
                    workingWithCoroutine()
                }
                CoroutineScope(dispatcher).launch {
                    callingWithCoroutine()
                }
            }
        }
        with(binding.btnDrinking) {
            setOnClickListener {
                drinking()
            }
        }
        with(binding.txtLog) {
            movementMethod = ScrollingMovementMethod()
            WorkStatus.workingLog.observe(this@MainActivity, {
                text = it.joinToString("\n") { it }
                if (it.toString().contains("퇴근")) {
                    text = "$text\n총 업무 시간: ${System.currentTimeMillis() - WorkStatus.totalTime.value!!} ms"
                }
            })
        }
    }

    private fun reset() {
        workingLog.clear()
        WorkStatus.totalTime.value = System.currentTimeMillis()
    }

    private fun appendLog(log: String) {
        workingLog.add("[${Thread.currentThread().name}] $log")
        CoroutineScope(Dispatchers.Main).launch {
            WorkStatus.workingLog.value = workingLog
        }
    }

    private fun working() {
        appendLog("출근")
        repeat(5) {
            Thread.sleep(300)
            appendLog("이슈 [${it + 1}]번 처리")
        }
        appendLog("퇴근")
    }

    private fun calling() {
        repeat(3) {
            Thread.sleep(250)
            appendLog("업무 [${it + 1}]번 전화")
        }
    }

    private fun drinking() {
        appendLog("물 마시기")
    }

    private suspend fun workingWithCoroutine() {
        appendLog("출근")
        repeat(5) {
            delay(300)
            appendLog("이슈 [${it + 1}]번 처리")
        }
        appendLog("퇴근")
    }

    private suspend fun callingWithCoroutine() {
        repeat(3) {
            delay(250)
            appendLog("업무 [${it + 1}]번 전화")
        }
    }
}