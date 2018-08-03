package com.luigivampa92.yms.fintracker.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "wallets")
data class Wallet (
        @PrimaryKey() var id: String,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "balance") var balance: Double
)