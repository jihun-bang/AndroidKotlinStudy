package jihun.bang.anrstudy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jihun.bang.anrstudy.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    }
    private var clickCount = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI Thread
        binding.buttonUi.setOnClickListener {
            clickCount++
            val start = System.currentTimeMillis()
            var result = ""
            repeat(2) { result = plus() }
            binding.txtResult.text = "Click Count: $clickCount, Result: $result"
            println("[UI] 소요 시간: ${System.currentTimeMillis() - start} ms")
        }

        // IO Thread
        binding.buttonMain.setOnClickListener {
            clickCount++
            val start = System.currentTimeMillis()
            var result = ""
            CoroutineScope(Dispatchers.IO).launch {
                repeat(2) { result = plus() }
                withContext(Dispatchers.Main) {
                    binding.txtResult.text = "Click Count: $clickCount, Result: $result"
                    println("[IO] 소요 시간: ${System.currentTimeMillis() - start} ms")
                }
            }
        }
    }

    fun plus(): String {
        var number = 0
        for (i in Int.MIN_VALUE + clickCount..Int.MAX_VALUE - clickCount) {
            number = i
        }
        return number.toString()
    }
}