package kz.kumgan.fin_plan_kotlin.expenses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.kumgan.fin_plan_kotlin.R
import kz.kumgan.fin_plan_kotlin.income.IncomeModel

class ExpenseLisAdapter(private val list: List<ExpenseModel>, val mItemClickListener:ItemClickListener): RecyclerView.Adapter<ExpenseLisAdapter.ViewHolder>() {

    interface ItemClickListener{
        fun onItemClick(position: Int)
        fun onLongClick(position: Int)
    }
        /// create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.expense_list_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = list[position]


        holder.name_tv.text = ItemsViewModel.name;

        holder.sum_tv.text = ItemsViewModel.sum.toString();

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    inner class  ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name_tv: TextView = itemView.findViewById(R.id.name)
        val sum_tv: TextView = itemView.findViewById(R.id.sum)

        init {
            ItemView.setOnClickListener{
                mItemClickListener.onItemClick(adapterPosition)
            }
            ItemView.setOnLongClickListener{
                mItemClickListener.onLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
        }
    }
}