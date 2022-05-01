package com.nxet.emstelltask.Uils

import com.nxet.emstelltask.DataClasses.Clinic
import com.nxet.emstelltask.DataClasses.Doctor
import com.nxet.emstelltask.DataClasses.MainData
import com.nxet.emstelltask.DataClasses.new
import retrofit2.Response
import retrofit2.http.*
import java.io.File

interface DataApi {

    @GET("/mobileapi/master/get_homepage")
    suspend fun getAll(
        @Query("language_id") languageID: Int,
        @Query("token") token: String,
        @Query("user_id") userID: Int
    ): Response<MainData>


    @GET("/starzz.json")
    suspend fun getAll(
       ): Response<new>

    @FormUrlEncoded
    @POST("/mobileapi/master/get_clinic_detail")
    suspend fun getClinic(
        @Field("device_type") deviceType: Int,
        @Field("language_id") languageID: Int,
        @Field("user_id") userID: Int,
        @Field("customer_id") customerID: Int,
        @Field("token") token: String,
        @Field("clinic_id") clinicID: Int
    ): Response<Clinic>

    @FormUrlEncoded
    @POST("/mobileapi/master/get_doctor_details")
    suspend fun getDoctor(
        @Field("device_type") deviceType: Int,
        @Field("language_id") languageID: Int,
        @Field("user_id") userID: Int,
        @Field("customer_id") customerID: Int,
        @Field("token") token: String,
        @Field("doctor_id") doctorID: Int
    ): Response<Doctor>
}