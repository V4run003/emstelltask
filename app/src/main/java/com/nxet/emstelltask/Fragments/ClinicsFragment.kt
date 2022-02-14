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
import com.nxet.emstelltask.Adapters.ClinicsAdapter
import com.nxet.emstelltask.DataClasses.MainData
import com.nxet.emstelltask.Uils.RetrofitInstance
import com.nxet.emstelltask.databinding.FragmentClinicsBinding
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ClinicsFragment() : Fragment() {

    private lateinit var clinicsBinding: FragmentClinicsBinding
    private lateinit var response : Response<MainData>
    private lateinit var responseData : MainData
    private lateinit var clinicsAdapter: ClinicsAdapter
    private lateinit var progress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        clinicsBinding = FragmentClinicsBinding.inflate(inflater,container,false)
        progress = clinicsBinding.progressClinic
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
                clinicsAdapter.clinic = responseData.clinics
                progress.visibility = View.GONE





            }

        }


        return clinicsBinding.root
    }

    private fun setupRecyclerView() {
        clinicsBinding.rvClinics.apply {
            clinicsAdapter = ClinicsAdapter()
            adapter = clinicsAdapter
            layoutManager = LinearLayoutManager(context)

        }
    }
}