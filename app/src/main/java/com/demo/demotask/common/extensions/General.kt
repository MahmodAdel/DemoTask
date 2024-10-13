package com.demo.demotask.common.extensions

fun Any?.isNull() = this == null
fun Any?.isNotNull() = this != isNull()