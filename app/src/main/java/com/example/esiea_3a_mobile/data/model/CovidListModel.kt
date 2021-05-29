package com.example.esiea_3a_mobile.presentation.view.list

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class CovidListModel<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): CovidListModel<T> {
            return CovidListModel(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): CovidListModel<T> {
            return CovidListModel(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): CovidListModel<T> {
            return CovidListModel(Status.LOADING, data, null)
        }
    }
}