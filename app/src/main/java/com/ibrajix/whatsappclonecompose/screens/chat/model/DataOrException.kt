package com.ibrajix.whatsappclonecompose.screens.chat.model

data class DataOrException<T, E : Exception>(
    var data: T? = null,
    var e: E? = null
)