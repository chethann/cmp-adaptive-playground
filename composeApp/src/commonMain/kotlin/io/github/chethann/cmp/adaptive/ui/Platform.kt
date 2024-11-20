package io.github.chethann.cmp.adaptive.ui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform