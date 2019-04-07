package org.pandec.applicationmeal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidNetworking.initialize(baseContext)

        loadData()
    }

    private fun loadData() {
        AndroidNetworking.get("https://www.themealdb.com/api/json/v1/1/filter.php?c=Chicken")
            .setTag(this)
            .setPriority(Priority.LOW)
            .build()
            .getAsObject(Response::class.java, object : ParsedRequestListener<Response> {
                override fun onError(anError: ANError?) {
                    when(anError?.response?.code()) {

                    }
                    Log.d("Error", anError.toString())
                }

                override fun onResponse(response: Response?) {
                    Log.d("Sukses", response?.meals?.size.toString())

                    response?.meals?.let {
                        val mealsAdapter = MealsAdapter(baseContext, it){
                            Toast.makeText(baseContext, it.idMeal, Toast.LENGTH_SHORT).show()
                        }
                        recyclerView.layoutManager = GridLayoutManager(baseContext, 2)
                        recyclerView.adapter = mealsAdapter
                    }
                }

            })
    }
}
