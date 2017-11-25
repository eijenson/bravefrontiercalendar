package eijenson.bravefrontiercalendar.usecase

import eijenson.bravefrontiercalendar.Application
import eijenson.bravefrontiercalendar.model.LocalData
import eijenson.bravefrontiercalendar.repository.repository.LocalRepository
import javax.inject.Inject

/**
 * Created by kobayashimakoto on 2017/11/23.
 * 端末ごとに保存する値を扱うクラス
 */
class LocalDataUseCase {
    @Inject internal lateinit var repository: LocalRepository
    private val localData: LocalData

    init {
        Application.localComponent.inject(this)
        localData = repository.get()
    }

    fun isFirstStart(): Boolean = localData.isFirstStart

    fun finishFirstStart() {
        localData.isFirstStart = false
        repository.save(localData)
    }
}