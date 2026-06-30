package com.learnagentic.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for LearnAgenticAI.
 * @HiltAndroidApp triggers Hilt's code generation to set up the DI graph.
 */
@HiltAndroidApp
class LearnAgenticApp : Application()
