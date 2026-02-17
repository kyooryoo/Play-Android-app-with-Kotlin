package com.test.shared

expect fun platform(): String
expect fun randomUUID(): String

interface Platform { val name: String }
expect fun getPlatform(): Platform
expect fun getFakePlatform(): Platform

fun setupApp(platform: Platform) {
    // logic
}