package kz.kumgan.fin_plan_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kz.kumgan.fin_plan_kotlin.expenses.ExpenseFormActivity
import kz.kumgan.fin_plan_kotlin.expenses.ExpenseLisAdapter
import kz.kumgan.fin_plan_kotlin.expenses.ExpenseListActivity
import kz.kumgan.fin_plan_kotlin.expenses.ExpenseModel
import kz.kumgan.fin_plan_kotlin.income.IncomeListActivity
import kz.kumgan.fin_plan_kotlin.settings.FireBaseSettings

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button_expense:Button=findViewById(R.id.expenses)
        button_expense.setOnClickListener(View.OnClickListener {
            val intent=Intent(this,ExpenseListActivity::class.java)
            startActivity(intent)
        })

        val button_income:Button=findViewById(R.id.incomes)
        button_income.setOnClickListener(View.OnClickListener {
            val intent=Intent(this,IncomeListActivity::class.java)
            startActivity(intent)
        })


    }

}