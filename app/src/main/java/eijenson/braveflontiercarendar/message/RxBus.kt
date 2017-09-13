package eijenson.braveflontiercarendar.message

import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject

/**
 * Created by eijenson on 2017/09/10.
 */
object RxBus {
    val bus = ReplaySubject.create<Any>().toSerialized()

    fun send(event: Any) {
        bus.onNext(event)
    }

    inline fun <reified T:Any> listen(): Observable<T> = bus.ofType(T::class.java)
}