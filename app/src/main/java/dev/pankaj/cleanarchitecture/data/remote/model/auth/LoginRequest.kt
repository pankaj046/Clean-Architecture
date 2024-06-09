package dev.pankaj.cleanarchitecture.data.remote.model.auth

data class LoginRequest(
    val username: String,
    val password: String,
)
