package com.luigivampa92.yms.fintracker.instrumented

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.test.runner.AndroidJUnit4
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.utils.createId
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class WalletsDaoTest : DbTest() {

    val walletId = createId()
    val wallet = Wallet(walletId, "Wallet", 150.0, "USD")

    @Test
    fun shouldDeleteAllData() {
        db.walletsDao().addWallet(wallet)
        db.walletsDao().deleteAllWallets()
        Assert.assertEquals(getValue(db.walletsDao().getAllWallets()).size, 0)
    }

    @Test
    fun shouldAddData() {
        db.walletsDao().addWallet(wallet)
        Assert.assertEquals(getValue(db.walletsDao().getWallet(walletId)).name, wallet.name)
    }

    @Test
    fun shouldUpdateData() {
        db.walletsDao().addWallet(wallet)
        db.walletsDao().updateWalletBalance(walletId, 20.0)
        Assert.assertEquals(getValue(db.walletsDao().getWallet(walletId)).balance, 20.0, 0.002)
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