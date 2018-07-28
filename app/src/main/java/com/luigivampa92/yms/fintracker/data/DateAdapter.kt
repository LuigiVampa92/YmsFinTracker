package com.luigivampa92.yms.fintracker.data

import android.annotation.SuppressLint
import com.google.gson.*
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateAdapter: JsonSerializer<Date>, JsonDeserializer<Date> {

    @SuppressLint("SimpleDateFormat")
    private var dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")

    override fun serialize(src: Date, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        TODO("Implement function: serialize")
    }

    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext) =  dateFormat.parse(json.asString)

}