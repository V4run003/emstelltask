package com.nxet.emstelltask

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nxet.emstelltask.DataClasses.Clinic
import com.nxet.emstelltask.DataClasses.MainData
import com.nxet.emstelltask.DataClasses.getClinic
import com.nxet.emstelltask.Uils.RetrofitInstance
import com.nxet.emstelltask.databinding.ActivityClinicDetailBinding
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ClinicDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClinicDetailBinding
    private lateinit var response : Response<Clinic>
    private lateinit var responseData : MainData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClinicDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val clinicID = intent.getIntExtra("clinic_id",13)


        lifecycleScope.launchWhenCreated {
            response = try {
                RetrofitInstance.api.getClinic(2, 1, 124, 124, TOKEN, clinicID)
            } catch (e : IOException) {
                Log.e(TAG,e.message.toString())
                return@launchWhenCreated
            } catch (e : HttpException){
                Log.e(TAG,e.message().toString())
                return@launchWhenCreated

            }
            if (response.isSuccessful && response.body() !=null){
                val resp = response.raw().toString()
                Log.e("response",response.body().toString())
                Toast.makeText(this@ClinicDetailActivity,"response : $resp",Toast.LENGTH_SHORT).show()





            }

        }



    }
}