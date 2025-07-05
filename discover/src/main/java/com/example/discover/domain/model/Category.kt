package com.example.discover.domain.model

data class Category(val name: String){
    companion object{
        val ALL = Category("All")
    }
}
