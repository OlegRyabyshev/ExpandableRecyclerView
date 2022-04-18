package com.olegryabyshev.myapplication.data

data class OuterClass(
    val textId: String,
    val array: List<InnerClass> = emptyList(),
    var isOpen: Boolean = true
)