package jihun.bang.listener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun onBackButtonClicked(view: View) {
        Log.d("로그", "[SecondActivity][onBackButtonClicked] Called")
        finish()
    }
}