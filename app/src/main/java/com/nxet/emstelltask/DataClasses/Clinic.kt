package com.nxet.emstelltask.DataClasses

data class Clinic(
    val clinic_id: String,
    val name: String,
    val location: String,
    val hour: String,
    val image: String,
    val favourite: String,
    val rating: String,
    val service_info: List<ServiceInfo>
)