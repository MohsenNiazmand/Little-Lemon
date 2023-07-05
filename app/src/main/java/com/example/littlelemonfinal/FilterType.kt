package com.example.littlelemonfinal

sealed class FilterType {
    object All : FilterType()
    object Appetizers : FilterType()
    object Salads  : FilterType()
    object Beverages : FilterType()
}
