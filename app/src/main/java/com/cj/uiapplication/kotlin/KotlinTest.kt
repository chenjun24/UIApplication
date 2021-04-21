package com.cj.uiapplication.kotlin

fun main() {
   Person().apply {
      // this.test()
       test()
   }
    Person().apply1(::myTest)
}
fun<T> T.apply(block:T.()->Unit):T{
    block()
    return this
}

fun<T> T.apply1(block:()->Unit):T{
    block()
    return this
}

fun myTest(){

}