package com.mistershorr.loginandregistration
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date


@Parcelize
data class Sleep(
    var wakeMillis: Long = Date().time,
    var bedMillis: Long = Date().time,
    var sleepDateMillis: Long = Date().time,
    var quality: Int = 1,
    var notes: String ?= null,
    var ownerId: String ?= null,
    var objectId: String ?= null
) : Parcelable
