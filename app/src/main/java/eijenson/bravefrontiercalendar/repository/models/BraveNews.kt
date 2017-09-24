package eijenson.bravefrontiercalendar.repository.models

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Setter
import com.github.gfx.android.orma.annotation.Table
import java.util.*

/**
 * ゲームのお知らせ情報
 */
@Table
data class BraveNews(
        @Setter("id") @PrimaryKey(autoincrement = true) var id: Long = 0,
        @Setter("title") @Column var title: String,
        @Setter("detail") @Column var detail: String,
        @Setter("period") @Column(indexed = true) var period: String?,
        @Setter("url") @Column var url: String,
        @Setter("startTime") @Column(indexed = true) var startTime: Date? = null,
        @Setter("endTime") @Column var endTime: Date? = null
)