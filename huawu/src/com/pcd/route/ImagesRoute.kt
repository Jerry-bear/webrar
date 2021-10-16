package com.example.com.pcd.route

import com.example.com.pcd.DB.dao.impl.ImagesDaoImpl_DM
import com.example.com.pcd.entity.Images
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

/**
 * @Description TODO
 * @author peichendong
 * @date 2021/10/16-15:57
 */
fun Route.insertImages(){
    post("/addImages"){
        val images = call.receive<Images>()
        val isInsertSuccess = ImagesDaoImpl_DM().insertImages(images)

        call.respond(mutableMapOf(isInsertSuccess?.first to isInsertSuccess?.second))
    }
}

fun Route.queryAllImages(){

    get("/allImages"){
        val queryAllImages = ImagesDaoImpl_DM().queryAllImages()

        val mutableMapOf = mutableMapOf<Int, Images>()

        for (item in queryAllImages.withIndex()){
            mutableMapOf[item.index] = item.value
        }

        call.respond(mutableMapOf("images" to mutableMapOf))
    }
}

fun Route.queryImagesById(){
    get("/ImagesById"){
        val id = call.request.queryParameters["id"]
        val queryAllImages = ImagesDaoImpl_DM().queryImageById(id!!.toInt())

        val mutableMapOf = mutableMapOf<Int, Images>()

        for (item in queryAllImages.withIndex()){
            mutableMapOf[item.index] = item.value
        }

        call.respond(mutableMapOf("images" to mutableMapOf))
    }
}