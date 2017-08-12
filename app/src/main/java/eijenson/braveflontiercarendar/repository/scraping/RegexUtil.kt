package eijenson.braveflontiercarendar.repository.scraping

/**
 * 正規表現処理をまとめたクラス
 */
object RegexUtil {
    private val date = """\d{4}年\d{1,2}月\d{1,2}日\([月火水木金土日]\) (\d{1,2}:\d{1,2}:\d{1,2}|メンテナンス(終了)?後)"""
    private val period = """$date[\s]*～.?$date"""

    fun getReportPeriod(input: String): String? {
        val regex = Regex(period)
        return regex.find(input)?.value
    }

}