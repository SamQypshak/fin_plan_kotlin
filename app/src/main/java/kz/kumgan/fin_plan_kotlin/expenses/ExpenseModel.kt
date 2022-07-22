package kz.kumgan.fin_plan_kotlin.expenses

import java.io.Serializable

class ExpenseModel:Serializable {
     var id:String=""
     var name: String=""
     var sum:Double=0.0;
     var rec_id="";

    constructor(id:String,name: String,sum:Double){
        this.id=id;
        this.name=name;
        this.sum=sum;
    }

    constructor()

}

