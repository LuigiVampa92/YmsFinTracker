package com.luigivampa92.yms.fintracker.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "records")
data class Record(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        @ColumnInfo() val name: String,
        @ColumnInfo() val category: String,
        @ColumnInfo() val income: Boolean,
        @ColumnInfo() val amount: Double,
        @ColumnInfo() val currency: String,
        @ColumnInfo() val wallet_id: String,
        @ColumnInfo() val date: String,
        @ColumnInfo() val pending_time: Long = 0L,
        @ColumnInfo() val repeatable: Boolean = false
)