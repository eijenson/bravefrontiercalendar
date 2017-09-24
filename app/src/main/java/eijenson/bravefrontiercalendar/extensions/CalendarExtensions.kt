package eijenson.bravefrontiercalendar.extensions

import java.util.*

/**
 * Created by eijenson on 2017/08/19.
 * カレンダーの追加関数
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

fun Calendar.set(year: Int, month: Int, date: Int, hourOfDay: Int, minute: Int, second: Int, milliSecond: Int) {
    set(year, month, date, hourOfDay, minute, second)
    set(Calendar.MILLISECOND, milliSecond)
}

fun Calendar.truncationTime() {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}

fun Calendar.getDay(): String {
    return "" + getYear() + "/" + (getMonth() + 1) + "/" + getDate()
}

fun Calendar.printlnDate() {
    println("" + getYear() + "/" + (getMonth() + 1) + "/" + getDate())
}