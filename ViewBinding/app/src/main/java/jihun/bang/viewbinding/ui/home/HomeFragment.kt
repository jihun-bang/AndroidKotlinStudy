package jihun.bang.viewbinding.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import jihun.bang.viewbinding.R
import jihun.bang.viewbinding.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    // 뷰가 사라질 때 메모리에서 삭제되도록 따로 빼두기
    private var fragmentHomeBinding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        // 뷰 바인딩 가져오기
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val textView: TextView = binding.textHome
        fragmentHomeBinding = binding
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        fragmentHomeBinding?.textHome?.setOnClickListener {
            Log.d("로그", "[HomeFragment][onCreateView] clicked $count")
            count ++
        }
        return binding.root
    }

    override fun onDestroyView() {
        fragmentHomeBinding = null
        super.onDestroyView()
    }
}