package com.example.com.pcd.DB.dao.impl

import com.example.com.pcd.DB.dao.PatientDao
import com.example.com.pcd.DB.db.DMDatabase
import com.example.com.pcd.entity.Patient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.PreparedStatement
import java.sql.SQLException

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/10/16-14:29
 */
class PatientDaoImpl_DM: PatientDao() {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java.name)


    override fun insertPatient(patient: Patient): Pair<String, String>? {

        val execute: Boolean?
        try {
            val connection = DMDatabase.connection()
            val prepareStatement = connection?.prepareStatement("insert into xinchuang.\"patient\" values(?,?,?,?,?,?)")
            patient.id?.let { prepareStatement?.setInt(1, it) }
            patient.name?.let { prepareStatement?.setString(2,it) }
            patient.doctor?.let { prepareStatement?.setString(3,it) }
            patient.age?.let { prepareStatement?.setInt(4,it) }
            patient.sex?.let { prepareStatement?.setString(5,it) }
            patient.illness?.let { prepareStatement?.setString(6,it) }
            execute = prepareStatement?.execute()
        }catch (e:SQLException){
            logger.info("插入数据错误"+e.message)
            return Pair<String,String>("添加失败",e.message.toString())
        }

        var pair:Pair<String,String>? = null
        if (execute == false){
            pair = Pair("添加成功","没什么好说你 你是对的")
        }

        return pair
    }

    override fun queryAllPatient(): List<Patient> {

        val connection = DMDatabase.connection()
        val prepareStatement = connection?.prepareStatement("select * from xinchuang.\"patient\" ")


        return queryPatient(prepareStatement)
    }

    override fun queryPatientById(id: Int): List<Patient> {

        val connection = DMDatabase.connection()
        val prepareStatement = connection?.prepareStatement("select * from xinchuang.\"patient\" where \"id\" = ? ")
        prepareStatement?.setInt(1,id)


        return queryPatient(prepareStatement)
    }

    private fun queryPatient(prepareStatement: PreparedStatement?): List<Patient> {
        val resultSet = prepareStatement?.executeQuery()

        val mutableListOf = mutableListOf<Patient>()
        while (resultSet?.next() == true) {
            val id = resultSet.getInt(1)
            val name = resultSet.getString(2)
            val doctor = resultSet.getString(3)
            val age = resultSet.getInt(4)
            val sex = resultSet.getString(5)
            val illness = resultSet.getString(6)

            val patient = Patient(id, name, doctor, age, sex, illness)
            mutableListOf.add(patient)
        }
        return mutableListOf
    }

    override fun queryPatientByName(patientName: String): List<Patient> {
        val connection = DMDatabase.connection()
        val prepareStatement = connection?.prepareStatement("select * from xinchuang.\"patient\" where \"name\" = ? ")
        prepareStatement?.setString(1,patientName)


        return queryPatient(prepareStatement)
    }

    override fun queryPatientByDoctor(doctor: String): List<Patient> {
        val connection = DMDatabase.connection()
        val prepareStatement = connection?.prepareStatement("select * from xinchuang.\"patient\" where \"doctor\" = ? ")
        prepareStatement?.setString(1,doctor)


        return queryPatient(prepareStatement)
    }
}