package jihun.bang.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import jihun.bang.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RecyclerViewInterface {
    private lateinit var binding: ActivityMainBinding

    private val modelList = mutableListOf<UserModel>()

    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("로그", "[MainActivity][onCreate] Called")

        for (i in 0 until 10) {
            UserModel(name = "방지훈$i", profileImage = "test$i").let {
                modelList.add(it)
            }
        }

        // Adapter 인스턴스 생성
        recyclerAdapter = RecyclerAdapter(this)
        recyclerAdapter.submitList(modelList = modelList)

        // RecyclerView 설정
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = recyclerAdapter
        }
    }

    override fun onItemClicked(position: Int) {
        Log.d("로그", "[MainActivity][onItemClicked] position = $position")
    }
}