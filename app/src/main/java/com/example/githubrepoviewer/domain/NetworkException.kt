package com.example.githubrepoviewer.domain

sealed class NetworkException : Exception() {

    object UnAuthorizedException : NetworkException()

    object NoInternetException : NetworkException()

    object TimeoutException : NetworkException()

    object NotFoundException : NetworkException()

    object ApiKeyExpiredException : NetworkException()

    object UnknownException : NetworkException()

}
