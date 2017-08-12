package eijenson.braveflontiercarendar.repository.scraping

/**
 * 正規表現処理をまとめたクラス
 */
object RegexUtil {
    private val date = """\d{4}年\d{1,2}月\d{1,2}日\([月火水木金土日]\) (\d{1,2}:\d{1,2}:\d{1,2}|メンテナンス(終了)?後)"""
    private val period = """$date[\s]*～.?$date"""

    /**
     * イベント期間を抽出する
     */
    fun period(input: String): String? {
        val regex = Regex(period)
        return regex.find(input)?.value
    }

    fun date(input: String?): List<String>? {
        if (input == null) return null
        val regex = Regex(date)
        val match = regex.findAll(input)
        return match.toList().map { it.value }
    }

}