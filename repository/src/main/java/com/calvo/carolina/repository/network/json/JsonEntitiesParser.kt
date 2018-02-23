package com.calvo.carolina.repository.network.json

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

internal open class JsonEntitiesParser
{
    open val mapper = jacksonObjectMapper()

    inline fun <reified T: Any>parse(json: String): T {
        return this.mapper.readValue(json)
    }
}