package kz.kumgan.fin_plan_kotlin.income

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kz.kumgan.fin_plan_kotlin.R
import kz.kumgan.fin_plan_kotlin.expenses.ExpenseFormActivity
import kz.kumgan.fin_plan_kotlin.expenses.ExpenseLisAdapter
import kz.kumgan.fin_plan_kotlin.expenses.ExpenseModel
import kz.kumgan.fin_plan_kotlin.settings.FireBaseSettings

class IncomeListActivity : AppCompatActivity(),IncomeLisAdapter.ItemClickListener {
    lateinit var database_ref: DatabaseReference
    lateinit var button_add: Button;
    lateinit var income_list:ArrayList<IncomeModel>;
    lateinit var list_adapter: IncomeLisAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_list)
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.list)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        income_list = ArrayList<IncomeModel>()


        // This will pass the ArrayList to our Adapter
        list_adapter = IncomeLisAdapter(income_list,this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = list_adapter
        database_ref= FirebaseDatabase.getInstance().getReference(FireBaseSettings.income_table);
        button_add=findViewById(R.id.button_add);
        button_add.setOnClickListener(View.OnClickListener {
            val intent= Intent(this, IncomeFormActivity::class.java);
            startActivity(intent)
        })
        val read_listener=object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                income_list.clear();
                val data= snapshot.getValue();
                for (child in snapshot.children){
                    val item: IncomeModel =  child.getValue(IncomeModel::class.java) as IncomeModel
                    item.rec_id=child.key.toString();
                    income_list.add(item);
                }
                list_adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        database_ref.addValueEventListener(read_listener);
    }

    override fun onItemClick(position: Int) {
        val intent= Intent(this, IncomeFormActivity::class.java);
        intent.putExtra("data",income_list.get(position));
        startActivity(intent)
    }

    override fun onLongClick(position: Int) {
        val a=1;
    }
}