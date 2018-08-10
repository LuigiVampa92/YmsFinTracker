package com.luigivampa92.yms.fintracker.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "currencies")
data class Currency(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        @ColumnInfo() var name: String,
        @ColumnInfo() var usd_ratio: Double = 60.0)