package com.unlimit.jokes.di

import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules

@Category(CheckModuleTest::class)
class ModuleCheckTest : KoinTest {

    @Test
    fun checkAppModules() = checkModules {
        startKoin { modules(
            appModule, networkModule,
            databaseModule
        ) }

    }
}