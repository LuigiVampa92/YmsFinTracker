package com.luigivampa92.yms.fintracker.data

interface Mapper<in F, out T> {
    fun map(value: F) : T
}