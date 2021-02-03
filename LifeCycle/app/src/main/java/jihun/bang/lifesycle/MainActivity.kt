package jihun.bang.lifesycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val TAG = "BJH_LOG"

    // Activity 생성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Layout 설정
        setContentView(R.layout.activity_main)

        Log.d(TAG, "[MainActivity][onCreate] called")
    }

    private fun onButtonClick() {
        Log.d(TAG, "[MainActivity][onButtonClick] called")
    }

    override fun onStart() {
        // super: 기존 onStart 로직 가져오기
        super.onStart()

        Log.d(TAG, "[MainActivity][onStart] called")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "[MainActivity][onResume] called")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "[MainActivity][onPause] called")
    }

    override fun onStop() {
        super.onStop()

        Log.d(TAG, "[MainActivity][onStop] called")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "[MainActivity][onDestroy] called")
    }
}