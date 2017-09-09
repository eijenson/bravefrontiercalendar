package eijenson.braveflontiercarendar.message

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by eijenson on 2017/09/10.
 */
object RxBus {
    val bus = PublishSubject.create<Any>().toSerialized()

    fun send(event: Any) {
        bus.onNext(event)
    }

    inline fun <reified T:Any> listen(): Observable<T> = bus.ofType(T::class.java)
}