package com.nxet.emstelltask.DataClasses

data class Doctor(
    val doctor_id: String,
    val clinic_id: String,
    val name: String,
    val location: String,
    val clinicname: String,
    val image: String,
    val favourite: String,
    val rating: String,
    val service_info: List<ServiceInfo>
)