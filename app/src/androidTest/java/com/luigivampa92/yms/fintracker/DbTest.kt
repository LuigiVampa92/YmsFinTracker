package com.luigivampa92.yms.fintracker

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.luigivampa92.yms.fintracker.db.database.FinanceTrackerDatabase
import org.junit.After
import org.junit.Before

abstract class DbTest {
    private lateinit var _db: FinanceTrackerDatabase
    val db: FinanceTrackerDatabase
        get() = _db

    @Before
    fun initDb() {
        _db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                FinanceTrackerDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
        _db.close()
    }
}