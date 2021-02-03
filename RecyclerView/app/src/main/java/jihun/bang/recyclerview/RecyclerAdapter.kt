package jihun.bang.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val recyclerViewInterface: RecyclerViewInterface) :
    RecyclerView.Adapter<RecyclerViewHolder>() {
    private val modelList = mutableListOf<UserModel>()

    // View Holder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        // 연결할 레이아웃 설정
        Log.d("로그", "[RecyclerAdapter][onCreateViewHolder] Called")
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false),
            recyclerViewInterface
        )
    }

    // 목록의 아이템 수
    override fun getItemCount(): Int {
        return modelList.size
    }

    // 뷰와 뷰홀더가 묶였을 때
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        Log.d("로그", "[RecyclerAdapter][onBindViewHolder] position = $position")
        holder.bind(this.modelList[position])
    }

    fun submitList(modelList: List<UserModel>) {
        this.modelList.clear()
        this.modelList.addAll(modelList)
    }
}