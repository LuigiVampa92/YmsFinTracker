package com.luigivampa92.yms.fintracker.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "currency")
class CurrencyEntity (
        @PrimaryKey
        @ColumnInfo(name = "ticker") val ticker: String,
        @ColumnInfo(name = "description") val description: String
)