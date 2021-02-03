package jihun.bang.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import jihun.bang.rxjava.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textInput.setOnClickListener {
            Log.d("로그", "[MainActivity][setOnClickListener] Called")
        }

        listTest()

        // Rx Binding으로 Edit Text 옵저버블 생성
        val textInputObsservable = binding.textInput.textChanges()
        // 옵저버블에 연산자 추가
        val subscription: Disposable = textInputObsservable
            // 글자가 입력되고 1초 후 onNext 이벤트 데이터 흘려보내기
            .debounce(1000, TimeUnit.MILLISECONDS)
            // IO Thread 에서 구동
            .subscribeOn(io())
            // 구독을 통해 이벤트 응답 받기
            .subscribeBy(
                onNext = {
                    Log.d("로그", "[MainActivity][onNext] $it")
                    checkTextLength()
                },
                onComplete = {
                    Log.d("로그", "[MainActivity][onComplete] Called")
                    checkTextLengthComplete()
                },
                onError = {
                    Log.d("로그", "[MainActivity][onError] $it")
                }
            )
        compositeDisposable.add(subscription)
    }

    override fun onDestroy() {
        // 모두 삭제
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun checkTextLength() {
        if (binding.textInput.text?.length ?: 0 > 5) {
            Log.d(
                "로그",
                "[MainActivity][checkTextLength] Size over than 5, ${binding.textInput.text}"
            )
        } else {
            Log.d(
                "로그",
                "[MainActivity][checkTextLength] Size less than 5 ${binding.textInput.text}"
            )
        }
    }

    private fun checkTextLengthComplete() {
        if (binding.textInput.text?.get(0) == 'T') {
            Log.d("로그", "[MainActivity][checkTextLengthComplete] is T")
        } else {
            Log.d("로그", "[MainActivity][checkTextLengthComplete] is not T")
        }
    }

    private fun listTest() {
        val list = listOf(1,2,3,4,5)
        val listOb = Observable.fromIterable(list)
        listOb.subscribeBy(
            onNext = {
                Log.d("로그", "[listTest()][onNext] $it")
            },
            onComplete = {
                Log.d("로그", "[listTest()][onComplete] Called")
            },
            onError = {
                Log.d("로그", "[listTest()][onError] $it")
            }
        )
    }
}