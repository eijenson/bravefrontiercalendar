package eijenson.bravefrontiercalendar.model

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
        @Setter("url") @Column(indexed = true) var url: String,
        @Setter("startTime") @Column(indexed = true) var startTime: Date? = null,
        @Setter("endTime") @Column var endTime: Date? = null,
        @Setter("createTime") @Column(indexed = true) var createTime: Date? = Date(),
        @Setter("isViewingSite") @Column(indexed = true) var isViewingSite: Boolean? = true
) {
    fun getHeader(): BraveNewsHeader = BraveNewsHeader(title, url)

    fun isBetween(): Boolean {
        if (startTime == null || endTime == null) return true
        val now = Calendar.getInstance().time
        return now.before(startTime) && now.after(endTime)
    }
}