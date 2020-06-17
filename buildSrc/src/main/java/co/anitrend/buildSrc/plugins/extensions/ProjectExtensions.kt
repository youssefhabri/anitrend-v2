/*
 * Copyright (C) 2020  AniTrend
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package co.anitrend.buildSrc.plugins.extensions

import com.android.build.gradle.*
import org.gradle.api.Project
import com.android.build.gradle.api.AndroidBasePlugin
import com.android.build.gradle.api.BaseVariantOutput
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.internal.plugins.DefaultArtifactPublicationSet
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.reporting.ReportingExtension
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.testing.internal.KotlinTestsRegistry


internal fun Project.baseExtension() =
    extensions.getByType<BaseExtension>()

internal fun Project.baseAppExtension() =
    extensions.getByType<BaseAppModuleExtension>()

internal fun Project.libraryExtension() =
    extensions.getByType<LibraryExtension>()

internal fun Project.dynamicFeatureExtension() =
    extensions.getByType<BaseAppModuleExtension>()

internal fun Project.extraPropertiesExtension() =
    extensions.getByType<ExtraPropertiesExtension>()

internal fun Project.defaultArtifactPublicationSet() =
    extensions.getByType<DefaultArtifactPublicationSet>()

internal fun Project.reportingExtension() =
    extensions.getByType<ReportingExtension>()

internal fun Project.sourceSetContainer() =
    extensions.getByType<SourceSetContainer>()

internal fun Project.javaPluginExtension() =
    extensions.getByType<JavaPluginExtension>()

internal fun Project.variantOutput() =
    extensions.getByType<BaseVariantOutput>()

internal fun Project.kotlinAndroidProjectExtension() =
    extensions.getByType<KotlinAndroidProjectExtension>()

internal fun Project.kotlinTestsRegistry() =
    extensions.getByType<KotlinTestsRegistry>()

internal fun Project.androidExtensionsExtension() =
    extensions.getByType<AndroidExtensionsExtension>()

internal fun Project.publishingExtension() =
    extensions.getByType<PublishingExtension>()

internal fun Project.containsAndroidPlugin(): Boolean {
    return project.plugins.toList().any { plugin ->
        plugin is AndroidBasePlugin
    }
}

internal fun Project.containsLibraryPlugin(): Boolean {
    return project.plugins.toList().any { plugin ->
        plugin is LibraryPlugin
    }
}

internal fun Project.containsDynamicFeaturePlugin(): Boolean {
    return project.plugins.toList().any { plugin ->
        plugin is DynamicFeaturePlugin
    }
}

internal fun Project.containsTestPlugin(): Boolean {
    return project.plugins.toList().any { plugin ->
        plugin is TestPlugin
    }
}
