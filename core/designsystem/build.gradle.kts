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
    alias(libs.plugins.hpoke.android.library)
    alias(libs.plugins.hpoke.android.library.compose)
    alias(libs.plugins.hpoke.spotless)
}

android {
    namespace = "com.example.hpoke.core.designsystem"
}

dependencies {
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.ui.text.google.fonts)
    implementation(libs.com.github.skydoves.landscapist.coil)
    implementation(libs.com.github.skydoves.landscapist.palette)
    implementation(libs.com.github.skydoves.landscapist.placeholder)
}
