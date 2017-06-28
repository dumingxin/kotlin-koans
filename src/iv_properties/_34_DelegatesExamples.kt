package iv_properties

import util.TODO
import util.doc34
import kotlin.properties.Delegates

class LazyPropertyUsingDelegates(val initializer: () -> Int) {
    val lazyValue: Int by Delegate(initializer)
}

fun todoTask34(): Lazy<Int> = TODO(
    """
        Task 34.
        Read about delegated properties and make the property lazy by using delegates.
    """,
    documentation = doc34()
)

class Delegate(val initializer: () -> Int):Lazy<Int>{
    private var isInit=false
    private var privateValue=0
    override val value: Int
        get() {
            if (!isInit){
                isInit=true
                privateValue=initializer()
                return privateValue
            }
            return privateValue
        }
    override fun isInitialized(): Boolean {
        return isInit
    }

}