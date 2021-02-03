package jihun.bang.livedataviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import jihun.bang.livedataviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewModel: MyNumberViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)
        viewModel.currentValue.observe(this, Observer {
            Log.d("로그", "[MainActivity][onCreate] viewModel.currentValue = $it")
            binding.textResult.text = it.toString()
        })

        binding.btnPlus.setOnClickListener(this)
        binding.btnMinus.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val userInput = binding.textInput.text.toString().toInt()
        when (view) {
            binding.btnPlus -> viewModel.updateValue(ActionType.PLUS, userInput)
            binding.btnMinus -> viewModel.updateValue(ActionType.MINUS, userInput)
        }
    }
}