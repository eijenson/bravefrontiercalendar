package eijenson.braveflontiercarendar.repository.models

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Setter
import com.github.gfx.android.orma.annotation.Table

@Table
data class BraveNews(
        @Setter("id") @PrimaryKey(autoincrement = true) var id: Long = 0,
        @Setter("title") @Column var title: String,
        @Setter("detail") @Column var detail: String,
        @Setter("period") @Column var period: String?
)