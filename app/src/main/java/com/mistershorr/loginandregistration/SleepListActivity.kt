package com.mistershorr.loginandregistration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.mistershorr.loginandregistration.databinding.ActivitySleepListBinding
import java.util.Date

class SleepListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySleepListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySleepListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrieveStuff()

        val sleep = Sleep()

        binding.floatingActionButton2.setOnClickListener{
            val intent = Intent(this, SleepDetailActivity::class.java).apply {
                putExtra(SleepDetailActivity.EXTRA_SLEEP, sleep)
            }
            this.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        retrieveStuff()
    }

    fun retrieveStuff() {
        val userId = Backendless.UserService.CurrentUser().userId!!
        // need the ownerId to match the objectId of the user
        val whereClause = "ownerId = '$userId'"
        val queryBuilder = DataQueryBuilder.create()
        queryBuilder.whereClause = whereClause
        Backendless.Data.of(Sleep::class.java).find(queryBuilder, object : AsyncCallback<List<Sleep>?> {
            override fun handleResponse(sleepList: List<Sleep>?) {
                Log.d("SleepListActivity", "handleResponse: $sleepList")
                if (sleepList != null) {
                    val adapter = SleepAdapter(sleepList as MutableList<Sleep>)
                    binding.SleepListSleep.layoutManager = LinearLayoutManager(this@SleepListActivity)
                    binding.SleepListSleep.adapter = adapter
                }
            }

            override fun handleFault(fault: BackendlessFault) {
                Log.d("SleepListActivity", "handleResponse: ${fault.message}")
            }
        })
    }
}