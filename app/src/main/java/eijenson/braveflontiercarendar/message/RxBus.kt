package eijenson.braveflontiercarendar.message

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by eijenson on 2017/09/10.
 */
object RxBus {
    // 講読開始した時に今までの通知を全部受け取る
    //val bus = ReplaySubject.create<Any>().toSerialized()
    val bus = PublishSubject.create<Any>().toSerialized()
    fun send(event: Any) {
        bus.onNext(event)
    }

    inline fun <reified T:Any> listen(): Observable<T> = bus.ofType(T::class.java)
}