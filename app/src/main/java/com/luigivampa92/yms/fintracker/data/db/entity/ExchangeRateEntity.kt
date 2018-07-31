package com.luigivampa92.yms.fintracker.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "exchange_rate")
class ExchangeRateEntity (
        @ColumnInfo(name = "from")val from: String,
        @ColumnInfo(name = "to") val to: String,
        @ColumnInfo(name = "value") val value: Double,
        @ColumnInfo(name = "update_time") val updateTime: Date
) {
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true) var id: Long = 0
}
