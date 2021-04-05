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

    // Activity 실행
    override fun onStart() {
        // super: 기존 onStart 로직 가져오기
        super.onStart()
        Log.d(TAG, "[MainActivity][onStart] called")
    }

    // 수행 후 Activity 화면 출력
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "[MainActivity][onResume] called")
    }

    // Activity 전환되어 화면에 보이지 않음.
    // Activity 백그라운드 상태
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "[MainActivity][onPause] called")
    }

    // Activity 중단
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "[MainActivity][onStop] called")
    }

    // Activity 재실행
    // 백그라운드의 Activity 화면 출력
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "[MainActivity][onStop] called")
    }

    // Activity 종료
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "[MainActivity][onDestroy] called")
    }
}