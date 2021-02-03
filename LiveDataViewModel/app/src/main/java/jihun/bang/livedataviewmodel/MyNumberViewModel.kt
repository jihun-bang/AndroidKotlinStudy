package jihun.bang.livedataviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType{
    PLUS, MINUS
}

// 데이터의 변경
// 뷰 모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있음
class MyNumberViewModel: ViewModel() {
    // 뮤터블 라이브 데이터 - 수정 가능
    // 라이브 데이터 - 수정 불가

    // 내부에서 설정하는 자료형은 뮤터블
    // 변경 가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()

    // 변경되지 않는 데이터를 가져올 때 이름을 언더스코어 없이 설정
    // 공개적으로 가져오는 변수는 private이 아닌 public으로 외부에서도 접근 가능하도록 설정정
   // 값을 직접 라이브데이터 접근이 아닌 뷰모델을 통해 가져올수 있도록 설정
    val currentValue: LiveData<Int>
        get() = _currentValue

    // 초기 값 설정
    init {
        Log.d("로그", "[MyNumberViewModel][init] Called")

        _currentValue.value = 0
    }

    fun updateValue(actionType: ActionType, input: Int) {
        _currentValue.value = when (actionType) {
            ActionType.PLUS -> _currentValue.value?.plus(input)
            ActionType.MINUS -> _currentValue.value?.minus(input)
        }
    }
}