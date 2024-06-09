package dev.pankaj.cleanarchitecture.utils

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions

fun NavController.navigateTo(resId: Int, args: Bundle?, inclusive: Boolean = true) {
    val optionsBuilder = NavOptions.Builder().setPopUpTo(this.graph.findStartDestination().id, inclusive)
    this.navigate(resId, args, optionsBuilder.build())
}

fun NavController.navigateTo(resId: Int, inclusive: Boolean = true) {
    val optionsBuilder = NavOptions.Builder().setPopUpTo(this.graph.findStartDestination().id, inclusive)
    this.navigate(resId, null, optionsBuilder.build())
}

fun NavController.navigateTo(resId: Int) {
    val optionsBuilder = NavOptions.Builder().setPopUpTo(this.graph.findStartDestination().id, false)
    this.navigate(resId, null, optionsBuilder.build())
}
