package jihun.bang.koin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myModdule = module {
            // 싱글톤으로 생성해서 의존성주입 (App 수명주기 동안 단일 인스턴스)
            single { User() }

            // 요청(Inject, get) 시점마다 새로운 인스턴스를 생성(Dagger의 Provider 개념)
            // BB 객체 생성자 파라미터로 get() 사용, (위에서 선언한 싱글톤의 User를 자동으로 의존성주입)
            factory {
                Developer(get())
            }
        }
    }
}

class User {
    fun name() {
        Log.d("로그", "[User][name] Called")
    }
}

class Developer(user: User) {
    fun name() {
        Log.d("로그", "[Developer][name] Called")
    }
}