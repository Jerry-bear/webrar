package com.example.com.pcd.route

import com.example.com.pcd.DB.dao.impl.PatientDaoImpl_DM
import com.example.com.pcd.entity.Patient
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/10/16-14:41
 */
fun Route.queryAllPatient(){

    get("/allPatient"){
        val allPatient = PatientDaoImpl_DM().queryAllPatient()
        val mutableMapOf = mutableMapOf<Int, Patient>()

        for (item in allPatient.withIndex()){
            mutableMapOf[item.index] = item.value
        }

        call.respond(mutableMapOf("patients" to mutableMapOf))
    }
}

fun Route.insertPatient(){
    post("/addPatient"){
        val patient = call.receive<Patient>()
        val isInsertSuccess = PatientDaoImpl_DM().insertPatient(patient)

        call.respond(mutableMapOf(isInsertSuccess?.first to isInsertSuccess?.second))

    }
}

fun Route.queryPatientByName(){
    get("/queryPatientByName") {
        val name = call.request.queryParameters["name"]
        val patientByName = PatientDaoImpl_DM().queryPatientByName(name.toString())

        val mutableMapOf = mutableMapOf<Int, Patient>()

        for (item in patientByName.withIndex()){
            mutableMapOf[item.index] = item.value
        }

        call.respond(mutableMapOf("patients" to mutableMapOf))

    }
}

fun Route.queryPatientByDoctor(){
    get("/queryPatientByDoctor") {
        val doctor = call.request.queryParameters["doctor"]
        val patientByName = PatientDaoImpl_DM().queryPatientByDoctor(doctor.toString())

        val mutableMapOf = mutableMapOf<Int, Patient>()

        for (item in patientByName.withIndex()){
            mutableMapOf[item.index] = item.value
        }

        call.respond(mutableMapOf("patients" to mutableMapOf))

    }
}


fun Route.queryPatientById(){

    get("/queryPatientById") {
        val id = call.request.queryParameters["id"]
        val patientByName = PatientDaoImpl_DM().queryPatientById(id!!.toInt())

        val mutableMapOf = mutableMapOf<Int, Patient>()

        for (item in patientByName.withIndex()){
            mutableMapOf[item.index] = item.value
        }

        call.respond(mutableMapOf("patients" to mutableMapOf))

    }
}