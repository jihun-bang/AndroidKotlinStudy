package jihun.bang.lottie

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import jihun.bang.lottie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 애니메이션 커스텀
        // ofFloat(시작지점, 종료지점), setDuration(지속시간)
        binding.btnLikeHeart.setOnClickListener {
            val animator = ValueAnimator.ofFloat(0f, 0.5f).setDuration(300)
            animator.addUpdateListener {
                binding.btnLikeHeart.progress = it.animatedValue as Float
            }
            if (isLiked) {
                animator.reverse()
                isLiked = false
                Log.d("로그", "[MainActivity][onCreate] 좋아요 해제 Clicked")
            } else {
                animator.start()
                isLiked = true
                Log.d("로그", "[MainActivity][onCreate] 좋아요 Clicked")
            }
        }
    }
}