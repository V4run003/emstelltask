package com.nxet.emstelltask.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nxet.emstelltask.*
import com.nxet.emstelltask.Adapters.DoctorsAdapter
import com.nxet.emstelltask.DataClasses.MainData
import com.nxet.emstelltask.Uils.RetrofitInstance
import com.nxet.emstelltask.databinding.FragmentDoctorBinding
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class DoctorsFragment() : Fragment() {

    private lateinit var fragmentDoctorBinding: FragmentDoctorBinding
    private lateinit var doctorsAdapter: DoctorsAdapter
    private lateinit var response : Response<MainData>
    private lateinit var responseData : MainData
    private lateinit var progress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         fragmentDoctorBinding = FragmentDoctorBinding.inflate(inflater,container,false)

       progress = fragmentDoctorBinding.progressDoctor
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            progress.visibility = View.VISIBLE
            response = try {
                RetrofitInstance.api.getAll(1, TOKEN,124)
            } catch (e : IOException) {
                Log.e(TAG,e.message.toString())
                return@launchWhenCreated
            } catch (e : HttpException){
                Log.e(TAG,e.message().toString())
                return@launchWhenCreated

            }
            if (response.isSuccessful && response.body() !=null){
                Log.e("response",response.body().toString())
                responseData = response.body()!!
                doctorsAdapter.doctors = responseData.doctors
                progress.visibility = View.GONE





            }

        }








        return fragmentDoctorBinding.root
    }

    private fun setupRecyclerView() {
        fragmentDoctorBinding.rvDoctors.apply {
            doctorsAdapter = DoctorsAdapter()
            adapter = doctorsAdapter
            layoutManager = LinearLayoutManager(context)


        }
    }

}