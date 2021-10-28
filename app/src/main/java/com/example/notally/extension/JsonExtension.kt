package com.example.notally.extension

import com.google.gson.Gson
import org.json.JSONObject

inline fun <reified T : Any> T.serializeToJson(): String {
    return Gson().toJson(this)
}

inline fun <reified T : Any, reified K : Any> T.sameAndEqual(n: K): Boolean {
    val firstSerialization = serializeToJson()
    val secondSerialization = n.serializeToJson()
    val isSame = firstSerialization == secondSerialization
    val isEqual = javaClass == n.javaClass
    return isEqual && isSame
}

inline fun <reified T : Any> fromJsonObject(jsonObject: JSONObject): T {
    val jsonString = jsonObject.toString()
    return Gson().fromJson(jsonString, T::class.java)
}
