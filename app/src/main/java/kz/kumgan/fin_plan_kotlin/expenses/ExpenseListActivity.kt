package kz.kumgan.fin_plan_kotlin.expenses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kz.kumgan.fin_plan_kotlin.R
import kz.kumgan.fin_plan_kotlin.settings.FireBaseSettings

class ExpenseListActivity : AppCompatActivity(),ExpenseLisAdapter.ItemClickListener {
    lateinit var database_ref: DatabaseReference
    lateinit var button_add: Button;
    lateinit var expenses_list:ArrayList<ExpenseModel>;
    lateinit var expenses_list_adapter:ExpenseLisAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.list)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        expenses_list = ArrayList<ExpenseModel>()


        // This will pass the ArrayList to our Adapter
        expenses_list_adapter = ExpenseLisAdapter(expenses_list,this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = expenses_list_adapter
        database_ref= FirebaseDatabase.getInstance().getReference(FireBaseSettings.expense_table);
        button_add=findViewById(R.id.button_add);
        button_add.setOnClickListener(View.OnClickListener {
            val intent= Intent(this, ExpenseFormActivity::class.java);
            startActivity(intent)
        })
        val read_listener=object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                expenses_list.clear();
                val data= snapshot.getValue();
                for (child in snapshot.children){
                    val expense:ExpenseModel=  child.getValue(ExpenseModel::class.java) as ExpenseModel
                    expense.rec_id=child.key.toString();
                    expenses_list.add(expense);
                }
                expenses_list_adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        database_ref.addValueEventListener(read_listener);
    }

    override fun onItemClick(position: Int) {
        val intent= Intent(this, ExpenseFormActivity::class.java);
        intent.putExtra("data",expenses_list.get(position));
        startActivity(intent)
    }

    override fun onLongClick(position: Int) {
        val a=1;
    }
}