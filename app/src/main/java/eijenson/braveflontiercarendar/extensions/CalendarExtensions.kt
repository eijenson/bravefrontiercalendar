package eijenson.braveflontiercarendar.extensions

import java.util.*

/**
 * Created by eijenson on 2017/08/19.
 */

fun Calendar.getYear(): Int {
    return get(Calendar.YEAR)
}

fun Calendar.getMonth(): Int {
    return get(Calendar.MONTH)
}

fun Calendar.getDate(): Int {
    return get(Calendar.DATE)
}

fun Calendar.getDay():String{
    return "" + getYear() + "/" + (getMonth() + 1) + "/" + getDate()
}

fun Calendar.printlnDate(){
    println("" + getYear() + "/" + (getMonth() + 1) + "/" + getDate())
}