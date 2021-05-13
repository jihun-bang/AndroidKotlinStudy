package jihun.bang.asynchronous

import android.os.AsyncTask
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val dispatcher = Dispatchers.IO
        val start = System.currentTimeMillis()
        workingLog.clear()
        val time = measureTimeMillis {
            val job1 = CoroutineScope(dispatcher).launch {
                workingWithCoroutine()
            }
            val job2 = CoroutineScope(dispatcher).launch {
                callingWithCoroutine()
            }
            joinAll(job1, job2)
        }
        println("총 소요 시간: $time ms")
    }
}

val workingLog = mutableListOf<String>()

private fun appendLog(log: String) {
    println("[${Thread.currentThread().name}] $log")
}

suspend fun workingWithCoroutine() {
    appendLog("출근")
    repeat(5) {
        delay(100)
        appendLog("이슈 [${it + 1}]번 처리")
    }
    appendLog("퇴근")
}

suspend fun callingWithCoroutine() {
    repeat(3) {
        delay(100)
        appendLog("업무 [${it + 1}]번 전화")
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