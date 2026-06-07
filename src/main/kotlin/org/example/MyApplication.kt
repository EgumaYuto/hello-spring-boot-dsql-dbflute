package org.example

import org.example.dbflute.allcommon.DBFluteBeansJavaConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

// DBFluteBeansJavaConfig (generated) registers the DBFlute runtime beans and
// component-scans the Behaviors (org.example.dbflute.exbhv). It binds to the
// Spring-managed DataSource bean named "dataSource".
@SpringBootApplication
@Import(DBFluteBeansJavaConfig::class)
class MyApplication

fun main(args: Array<String>) {
    @Suppress("SpreadOperator")
    runApplication<MyApplication>(*args)
}
