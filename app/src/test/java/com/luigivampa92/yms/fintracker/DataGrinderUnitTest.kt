package com.luigivampa92.yms.fintracker

import com.luigivampa92.yms.fintracker.calculations.DataGrinder
import com.luigivampa92.yms.fintracker.model.Category
import com.luigivampa92.yms.fintracker.model.Record
import com.luigivampa92.yms.fintracker.utils.convertStringToDate
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test

class DataGrinderUnitTest : MainUnitTest() {

    val record6 = Record("", "Burger", "Еда",
            false, 126.0, "RUB", walletId, "05.08.2018",
            0)

    val categories = arrayListOf(Category(0L, "Еда", false),
            Category(1L, "Зарплата", true), Category(2L, "Развлечения", false))

    //Считаем сумму по категориям
    @Test
    fun testSplitCategories(){
        records.add(record6)
        val hashMap = DataGrinder.splitCategories(records)
        Assert.assertEquals(hashMap, hashMapOf("Зарплата" to 150.0, "Еда" to 126.0))

    }

    //Данные для графика, дельта 0.1 тоже пойдет
    @Test
    fun testGetSumForPieChart(){
        Assert.assertEquals(DataGrinder.getSumForPieChart(records), 778.01, 0.1)
        records.add(record6)
        Assert.assertEquals(DataGrinder.getSumForPieChart(records), 778.01 + 126.0, 0.1)
    }
}