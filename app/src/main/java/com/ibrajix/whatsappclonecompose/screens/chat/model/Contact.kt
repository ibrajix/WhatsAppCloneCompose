package com.ibrajix.whatsappclonecompose.screens.chat.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*


data class Contact(
    var name: String? = null,
    var hasReadMessage: Boolean? = null,
    @ServerTimestamp
    var date: Date? = null,
    var lastMessage: String? = null,
    var profilePhoto: String? = null
)