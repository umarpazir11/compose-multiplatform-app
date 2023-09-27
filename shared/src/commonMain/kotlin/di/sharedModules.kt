package di

import di.coreModule
import di.platformModule

val sharedModules = arrayOf(
    coreModule,
    platformModule
)
