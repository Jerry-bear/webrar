package com.example

import com.example.com.pcd.DB.db.DMDatabase
import com.example.com.pcd.route.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.gson.*
import io.ktor.features.*

fun main(args: Array<String>): Unit = io.ktor.server.tomcat.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    install(ContentNegotiation) {
        gson {
        }
    }

    routing {
        get("/") {
            val patient = DMDatabase.queryTableOfLabelName("patient")
            val images = DMDatabase.queryTableOfLabelName("images")
            val illness = DMDatabase.queryTableOfLabelName("illness")
            call.respond(mapOf("patient" to patient,"images" to images,"illness" to illness))
        }

        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }

        insertPatient()

        queryAllPatient()
        queryPatientByDoctor()
        queryPatientById()
        queryPatientByName()

        insertImages()
        queryAllImages()
        queryImagesById()


        insertIllness()
        queryAllIllness()
        queryIllnessById()

    }
}

data class JsonSampleClass(val hello: String)

