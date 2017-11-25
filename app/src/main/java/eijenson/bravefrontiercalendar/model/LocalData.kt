package eijenson.bravefrontiercalendar.model

/**
 * Created by kobayashimakoto on 2017/11/23.
 * 端末ごとに保存する値を管理するクラス
 */
data class LocalData(
        var isFirstStart: Boolean = true,
        var isAllView: Boolean = false
)