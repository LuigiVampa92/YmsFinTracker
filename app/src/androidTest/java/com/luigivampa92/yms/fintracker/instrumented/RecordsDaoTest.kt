package com.luigivampa92.yms.fintracker.instrumented

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.test.runner.AndroidJUnit4
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.utils.createId
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class RecordsDaoTest: DbTest() {

    val walletId = createId()
    val record = Record("1", "Burger", "Food", false, 120.0,
            "RUB", walletId, "06.08.2018", 0)

    @Test
    fun shouldDeleteAllData(){
        db.recordsDao().addRecord(record)
        db.recordsDao().deleteAllRecords()
        Assert.assertEquals(getValue(db.recordsDao().getAllRecordsFromWallet(walletId)).size, 0)
    }

    @Test
    fun shouldAddData(){
        db.recordsDao().addRecord(record)
        Assert.assertEquals(getValue(db.recordsDao().getAllRecordsFromWallet(walletId)).first().name, record.name)
    }


    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)//To change body of created functions use File | Settings | File Templates.
            }

        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }
}