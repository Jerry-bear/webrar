package com.example.com.pcd.DB.dao


import com.example.com.pcd.entity.Patient

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/10/16-14:24
 */
abstract class PatientDao {

    abstract fun insertPatient(patient: Patient):Pair<String, String>?

    abstract fun queryAllPatient():List<Patient>

    abstract fun queryPatientById(id:Int):List<Patient>

    abstract fun queryPatientByName(patientName:String):List<Patient>

    abstract fun queryPatientByDoctor(doctor:String): List<Patient>

}