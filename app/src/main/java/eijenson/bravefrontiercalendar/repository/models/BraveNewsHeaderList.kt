package eijenson.bravefrontiercalendar.repository.models

/**
 * Created by kobayashimakoto on 2017/11/15.
 * ゲームのお知らせヘッダのリストを管理するクラス
 */
class BraveNewsHeaderList(private val braveNewsHeaderList: List<BraveNewsHeader>) {

    fun contains(element: BraveNewsHeader): Boolean {
        braveNewsHeaderList.forEach {
            if (it.title == element.title && it.url == element.url) {
                return true
            }
        }
        return false
    }

    fun contains(element: BraveNews): Boolean {
        braveNewsHeaderList.forEach {
            if (it.title == element.title && it.url == element.url) {
                return true
            }
        }
        return false
    }

    fun forEach(action: (BraveNewsHeader) -> Unit) {
        for (element in braveNewsHeaderList) action(element)
    }
}