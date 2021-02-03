package jihun.bang.recyclerview

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(
    itemView: View,
    private val recyclerViewInterface: RecyclerViewInterface
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val textView: TextView = itemView.findViewById(R.id.textView)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    init {
        Log.d("로그", "[RecyclerViewHolder][init] Called")
        itemView.setOnClickListener(this)
    }

    // 데이터와 뷰를 묶음
    fun bind(model: UserModel) {
        Log.d("로그", "[RecyclerViewHolder][bind] Called")
        textView.text = model.name
    }

    override fun onClick(p0: View?) {
        Log.d("로그", "[RecyclerViewHolder][onClick] position = $adapterPosition")
        this.recyclerViewInterface.onItemClicked(adapterPosition)
    }
}