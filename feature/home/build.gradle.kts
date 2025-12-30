/*
 * Copyright (C) 2025 Hamza Gattal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    alias(libs.plugins.hpoke.android.feature)
    alias(libs.plugins.hpoke.android.library.compose)
    alias(libs.plugins.hpoke.spotless)
}

android {
    namespace = "com.example.hpoke.feature.home"
}

dependencies {
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.paging.compose)

    testImplementation(projects.core.testing)
    testImplementation(libs.androidx.paging.testing)

    androidTestImplementation(projects.core.testing)
    androidTestImplementation(libs.androidx.paging.testing)
}
