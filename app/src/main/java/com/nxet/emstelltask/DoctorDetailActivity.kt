package com.nxet.emstelltask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.nxet.emstelltask.Uils.RetrofitInstance
import com.nxet.emstelltask.databinding.ActivityDoctorDetailBinding
import com.nxet.emstelltask.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

class DoctorDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val doctorID = intent.getIntExtra("doctor_id",60)


        lifecycleScope.launchWhenCreated {
           val response = try {
                RetrofitInstance.api.getDoctor(2, 1, 124, 124, TOKEN, doctorID)
            } catch (e : IOException) {
                Log.e(TAG,e.message.toString())
                return@launchWhenCreated
            } catch (e : HttpException){
                Log.e(TAG,e.message().toString())
                return@launchWhenCreated

            }
            if (response.isSuccessful && response.body() !=null){
                val resp = response.raw().toString()
                Log.e("response",response.raw().toString())
                Toast.makeText(this@DoctorDetailActivity,"response : $resp", Toast.LENGTH_SHORT).show()
            }

        }
    }
}