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

package com.example.hpoke.core.database.di

import com.example.hpoke.core.database.HPokeDatabase
import org.koin.dsl.module

val daoModule =
    module {

        single { get<HPokeDatabase>().pokemonDao() }
        single { get<HPokeDatabase>().speciesDao() }
        single { get<HPokeDatabase>().typeDao() }
        single { get<HPokeDatabase>().abilityDao() }
        single { get<HPokeDatabase>().statDao() }
        single { get<HPokeDatabase>().remoteKeyDao() }
    }
