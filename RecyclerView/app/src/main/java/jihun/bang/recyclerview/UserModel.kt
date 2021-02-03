package jihun.bang.recyclerview

import android.util.Log

data class UserModel(
    var name: String? = null,
    var profileImage: String? = null
) {
    init {
        Log.d("로그", "[UserModel][init] Called ")
    }
}
