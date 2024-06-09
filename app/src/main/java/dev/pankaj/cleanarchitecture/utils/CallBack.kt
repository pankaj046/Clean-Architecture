package dev.pankaj.cleanarchitecture.utils
sealed class CallBack<out T : Any> {

    data class Success<out T : Any>(val data: T) : CallBack<T>()
    data class Error(val exception: Exception) : CallBack<Nothing>()
    data class Message(val msg: String) : CallBack<Nothing>()
    data class Loading(val isLoading: Boolean) : CallBack<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Error[exception=$isLoading]"
            is Message -> "Error[exception=$msg]"
        }
    }
}