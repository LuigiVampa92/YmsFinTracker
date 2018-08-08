package com.luigivampa92.yms.fintracker.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity(tableName = "templates")
data class Template(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        @ColumnInfo() val name: String,
        @ColumnInfo() val category: String,
        @ColumnInfo() val income: Boolean,
        @ColumnInfo() var amount: Double,
        @ColumnInfo() val currency: String,
        @ColumnInfo() val pending_time: Int = 0,
        @ColumnInfo() val repeatable: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(category)
        parcel.writeByte(if (income) 1 else 0)
        parcel.writeDouble(amount)
        parcel.writeString(currency)
        parcel.writeInt(pending_time)
        parcel.writeByte(if (repeatable) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Template> {
        override fun createFromParcel(parcel: Parcel): Template {
            return Template(parcel)
        }

        override fun newArray(size: Int): Array<Template?> {
            return arrayOfNulls(size)
        }
    }
}