package eijenson.braveflontiercarendar.repository.scraping

import java.text.SimpleDateFormat
import java.util.*

/**
 * 正規表現処理をまとめたクラス
 */
object RegexUtil {
    private val maintenance = """メンテナンス(終了)?後"""
    private val date = """\d{4}年\d{1,2}月\d{1,2}日\([月火水木金土日]\)"""
    private val time = """\d{1,2}:\d{1,2}:\d{1,2}"""
    private val dateTime = """$date $time"""
    private val eventDate = """$date ($time|$maintenance)"""
    private val period = """$eventDate[\s]*～.?$eventDate"""

    private val dateTimeFormatter = SimpleDateFormat("yyyy年MM月dd日(E) HH:mm:ss", Locale.JAPAN)
    private val dateFormatter = SimpleDateFormat("yyyy年MM月dd日(E)", Locale.JAPAN)
    /**
     * イベント期間を抽出する
     */
    fun period(input: String): String? {
        val regex = Regex(period)
        return regex.find(input)?.value
    }

    /**
     * イベント日時を取得する
     */
    fun eventDate(input: String): List<String>? {
        val regex = Regex(eventDate)
        val match = regex.findAll(input)
        return match.toList().map { it.value }
    }

    /**
     * 日時を取得する
     */
    fun dateTime(inqut: String): List<Date?>? {
        val inputList = eventDate(inqut)
        val regexDateTime = Regex(dateTime)
        val regexDate = Regex(date)
        return inputList?.map {
            var value = parseDateTime(regexDateTime.find(it)?.value)
            if (value == null) value = parseDate(regexDate.find(it)?.value)
            value
        }
    }

    private fun parseDate(source: String?): Date? {
        if (source == null) return null
        return dateFormatter.parse(source)
    }

    private fun parseDateTime(source: String?): Date? {
        if (source == null) return null
        return dateTimeFormatter.parse(source)
    }

    fun formatDateTime(date: Date?): String? {
        if (date == null) return null;
        return dateTimeFormatter.format(date)
    }
}