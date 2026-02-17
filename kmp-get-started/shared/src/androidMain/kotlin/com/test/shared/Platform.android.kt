package com.test.shared

import android.os.Build
import java.util.UUID

actual fun platform() = "Android ${Build.VERSION.SDK_INT}"

class AndroidPlatform: Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}
actual fun getPlatform(): Platform = AndroidPlatform()

class AndroidFakePlatform: Platform {
    override val name: String = "Android 999"
}
actual fun getFakePlatform(): Platform = AndroidFakePlatform()

actual fun randomUUID(): String = UUID.randomUUID().toString()