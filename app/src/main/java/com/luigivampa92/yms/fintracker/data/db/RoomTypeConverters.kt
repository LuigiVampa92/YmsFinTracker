package com.luigivampa92.yms.fintracker.data.db

import android.arch.persistence.room.TypeConverter
import java.util.*

object RoomTypeConverters {

    @TypeConverter
    @JvmStatic fun utsToDate(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    @JvmStatic fun dateToUts(value: Date?): Long? = if (value == null) null else value.time
}