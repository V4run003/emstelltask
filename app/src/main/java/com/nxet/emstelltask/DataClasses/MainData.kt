package com.nxet.emstelltask.DataClasses


data class MainData(
    val status: String,
    val clinics: List<Clinic>,
    val clinic_count: Int,
    val doctors: List<Doctor>,
    val doctor_count: Int


)