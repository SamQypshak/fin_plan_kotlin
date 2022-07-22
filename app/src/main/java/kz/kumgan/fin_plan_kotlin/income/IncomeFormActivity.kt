package kz.kumgan.fin_plan_kotlin.income

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kz.kumgan.fin_plan_kotlin.R
import kz.kumgan.fin_plan_kotlin.settings.FireBaseSettings
import java.util.*

class IncomeFormActivity : AppCompatActivity() {
    lateinit var button_save: Button;
    lateinit var database_ref: DatabaseReference

    lateinit var name_et:EditText;
    lateinit var sum_et:EditText;

    var item: IncomeModel? =null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_form)
        button_save=findViewById(R.id.button_save)
        database_ref= FirebaseDatabase.getInstance().getReference(FireBaseSettings.income_table);
        name_et=findViewById(R.id.name);
        sum_et=findViewById(R.id.sum);
        button_save.setOnClickListener(View.OnClickListener {
            saveData()

        })
        initForm()

    }


    private fun initForm(){
        if(intent.hasExtra("data")){
            item= intent.getSerializableExtra("data") as IncomeModel
            item?.let {
                name_et.setText(it.name);
                sum_et.setText(it.sum.toString());
            }

                   }
    }

    private fun saveData(){
        val name=name_et.text.toString();
        val sum=sum_et.text.toString().toDouble();
        val new_id=UUID.randomUUID().toString();
        var rec_id=""
        var id=""
        if (item!=null){
             rec_id=item!!.rec_id;
             id=item!!.id;

        }
        if (rec_id.isEmpty()){
            database_ref.push().setValue(IncomeModel(new_id,name,sum))
        }else{
            database_ref.child(rec_id).setValue(IncomeModel(id,name,sum));
        }

        finish();
    }
}