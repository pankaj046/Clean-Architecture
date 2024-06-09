package dev.pankaj.cleanarchitecture.utils

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions

fun NavController.navigateTo(
    resId: Int,
    args: Bundle? = null,
    inclusive: Boolean = true
) {
    this.navigate(
        resId,
        args,
        NavOptions.Builder()
            .setPopUpTo(this.graph.findStartDestination().id, inclusive)
            .build()
    )
}

fun NavController.navigateTo(
    resId: Int, inclusive: Boolean = true
) {
    this.navigate(
        resId,
        null,
        NavOptions.Builder()
            .setPopUpTo(this.graph.findStartDestination().id, inclusive)
            .build()
    )
}

fun NavController.navigateTo(
    resId: Int
) {
    this.navigate(
        resId,
        null,
        NavOptions.Builder()
            .setPopUpTo(this.graph.findStartDestination().id, false)
            .build()
    )
}