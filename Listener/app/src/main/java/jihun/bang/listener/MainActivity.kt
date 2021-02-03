package jihun.bang.listener

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import jihun.bang.listener.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSet.setOnClickListener {
            secondView()
        }
    }

    private fun secondView() {
        Log.d("로그", "[MainActivity][backView] Called")
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}