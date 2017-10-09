/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.jakewharton.dex.DexMethods
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

open class DexMethodCount : DefaultTask() {

    @InputFile
    var jarFile: File? = null


    @TaskAction
    fun invoke() {
        val methods = DexMethods.list(jarFile!!)
        val byPackage = methods.groupBy { it.`package` }
        val byClass = methods.groupBy { it.declaringType }
        println("${methods.size}\ttotal")
        val totals = methods.partition { it.declaringType.startsWith("kotlin.") }.let {
            it.first.size to it.second.size
        }
        println("${totals.first.toString().padStart(5,' ')}  total in kotlin.*")
        println("${totals.second.toString().padStart(5,' ')}  other")
        println()
        println("by package:")
        byPackage.forEach { println("${it.value.size.toString().padStart(5,' ')}  ${it.key}") }
        println()
//        println("by class:")
//        byClass.forEach { println("${it.value.size.toString().padStart(5,' ')}  ${it.key}") }
//        println()
    }

}