package com.dicoding.submissionmade.core.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors @VisibleForTesting constructor(
    private val diskIO: Executor
) {

    companion object {
        private const val THREAD_COUNT = 3
    }

    constructor() : this(
        Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor = diskIO
}