package eijenson.braveflontiercarendar

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val text = "2017年8月9日(水) 23:59:59まで」から「2017年8月10日(木) 12:59:59まで」に延長いたしました。 いつもブレイブフロンティアをご利用いただき誠にありがとうございます。 7/31(月)メンテナンス後より、4周年を記念して 『マルチメダルキャンペーン』 を開催いたします！ 期間中は全てのマルチクエストでもらえるメダルの枚数が増えるぞ！！ 高いマルチランクほど、もらえるメダルも大量！！ 【 キャンペーン期間 】 2017年7月31日(月) メンテナンス後 ～ 2017年8月10日(木) 12:59:59"
        val str = "2017年7月31日(月) メンテナンス後 ～ 2017年8月10日(木) 12:59:59"
        println(get(text))
        assertEquals(4, 2 + 2)
    }



    private val date1 = """\d{4}年\d{1,2}月\d{1,2}日\([月火水木金土日]\) \d{1,2}:\d{1,2}:\d{1,2}"""
    private val date2 = """\d{4}年\d{1,2}月\d{1,2}日\([月火水木金土日]\) メンテナンス後"""
    private val date3 = """\d{4}年\d{1,2}月\d{1,2}日\([月火水木金土日]\) (\d{1,2}:\d{1,2}:\d{1,2}|メンテナンス後)"""
    //private val period = """$date[\s\S]*～.?$date"""
    private val period = """$date3[\s]*～.?$date3"""

    fun get(detail: String): String? {
        val regex = Regex(period)
        return regex.find(detail)?.value
    }

}
