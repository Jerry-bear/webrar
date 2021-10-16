package com.example.com.pcd.route

import com.example.com.pcd.DB.dao.impl.IllnessDaoImpl_DM
import com.example.com.pcd.DB.dao.impl.ImagesDaoImpl_DM
import com.example.com.pcd.entity.Illness
import com.example.com.pcd.entity.Images
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/10/16-16:12
 */

fun Route.insertIllness(){
    post("/addIllness") {
        val illness = call.receive<Illness>()
        val isInsertSuccess = IllnessDaoImpl_DM().insertIllness(illness)
        call.respond(mutableMapOf(isInsertSuccess?.first to isInsertSuccess?.second))
    }
}

fun Route.queryAllIllness(){
    get("/allIllness"){
        val queryAllImages = IllnessDaoImpl_DM().queryAllIllness()

        val mutableMapOf = mutableMapOf<Int, Illness>()

        for (item in queryAllImages.withIndex()){
            mutableMapOf[item.index] = item.value
        }

        call.respond(mutableMapOf("images" to mutableMapOf))
    }
}

fun Route.queryIllnessById(){

    get("/IllnessById"){

        val id = call.request.queryParameters["id"]
        val queryAllImages = IllnessDaoImpl_DM().queryIllnessById(id!!.toInt())

        val mutableMapOf = mutableMapOf<Int, Illness>()

        for (item in queryAllImages.withIndex()){
            mutableMapOf[item.index] = item.value
        }

        call.respond(mutableMapOf("images" to mutableMapOf))
    }

}
