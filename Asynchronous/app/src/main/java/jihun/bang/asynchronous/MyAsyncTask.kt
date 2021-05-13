package jihun.bang.asynchronous

import android.os.AsyncTask

class MyAsyncTask : AsyncTask<() -> Unit, Void, Unit>() {
    override fun doInBackground(vararg action: () -> Unit) {
        action[0]()
    }
}