package com.luigivampa92.yms.fintracker.instrumented

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.test.runner.AndroidJUnit4
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.model.Template
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.utils.createId
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class TemplatesDaoTest : DbTest() {

    val template = Template(0L, "Template", "Food", false, 120.0, "USD",
            0, false)

    @Test
    fun shouldAddData() {
        db.templatesDao().addTemplate(template)
        Assert.assertEquals(getValue(db.templatesDao().getAllTemplates()).first().name, template.name)
    }

    @Test
    fun shouldDeleteData(){
        db.templatesDao().addTemplate(template)
        db.templatesDao().deleteTemplate(template)
        db.templatesDao().addTemplate(template)
        Assert.assertEquals(getValue(db.templatesDao().getAllTemplates()).first().name, template.name)
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