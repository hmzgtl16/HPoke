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

package com.example.hpoke.core.ui.preview

import com.example.hpoke.core.model.Ability
import com.example.hpoke.core.model.Pokemon
import com.example.hpoke.core.model.Species
import com.example.hpoke.core.model.Stat
import com.example.hpoke.core.model.Type

object PreviewParameterData {
    val pokemons =
        listOf(
            Pokemon(
                id = 1,
                name = "Bulbasaur",
                height = 7,
                weight = 69,
                baseExperience = 64,
                species = Species(id = 1, frontDefault = "https://example.com/1.png"),
                types =
                    listOf(
                        Type(slot = 1, id = 12, name = "grass"),
                        Type(slot = 2, id = 4, name = "poison"),
                    ),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 45, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 49, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 49, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 65, effort = 1),
                        Stat(id = 5, name = "special-defense", baseStat = 65, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 45, effort = 0),
                    ),
                abilities = listOf(Ability(id = 65, name = "overgrow", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 2,
                name = "Ivysaur",
                height = 10,
                weight = 130,
                baseExperience = 141,
                species = Species(id = 2, frontDefault = "https://example.com/2.png"),
                types =
                    listOf(
                        Type(slot = 1, id = 12, name = "grass"),
                        Type(slot = 2, id = 4, name = "poison"),
                    ),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 60, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 62, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 63, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 80, effort = 1),
                        Stat(id = 5, name = "special-defense", baseStat = 80, effort = 1),
                        Stat(id = 6, name = "speed", baseStat = 60, effort = 0),
                    ),
                abilities = listOf(Ability(id = 65, name = "overgrow", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 3,
                name = "Venusaur",
                height = 20,
                weight = 1000,
                baseExperience = 236,
                species = Species(id = 3, frontDefault = "https://example.com/3.png"),
                types =
                    listOf(
                        Type(slot = 1, id = 12, name = "grass"),
                        Type(slot = 2, id = 4, name = "poison"),
                    ),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 80, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 82, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 83, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 100, effort = 2),
                        Stat(id = 5, name = "special-defense", baseStat = 100, effort = 1),
                        Stat(id = 6, name = "speed", baseStat = 80, effort = 0),
                    ),
                abilities = listOf(Ability(id = 65, name = "overgrow", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 4,
                name = "Charmander",
                height = 6,
                weight = 85,
                baseExperience = 62,
                species = Species(id = 4, frontDefault = "https://example.com/4.png"),
                types = listOf(Type(slot = 1, id = 10, name = "fire")),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 39, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 52, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 43, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 60, effort = 1),
                        Stat(id = 5, name = "special-defense", baseStat = 50, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 65, effort = 0),
                    ),
                abilities = listOf(Ability(id = 66, name = "blaze", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 5,
                name = "Charmeleon",
                height = 11,
                weight = 190,
                baseExperience = 142,
                species = Species(id = 5, frontDefault = "https://example.com/5.png"),
                types = listOf(Type(slot = 1, id = 10, name = "fire")),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 58, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 64, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 58, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 80, effort = 1),
                        Stat(id = 5, name = "special-defense", baseStat = 65, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 80, effort = 1),
                    ),
                abilities = listOf(Ability(id = 66, name = "blaze", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 6,
                name = "Charizard",
                height = 17,
                weight = 905,
                baseExperience = 240,
                species = Species(id = 6, frontDefault = "https://example.com/6.png"),
                types =
                    listOf(
                        Type(slot = 1, id = 10, name = "fire"),
                        Type(slot = 2, id = 3, name = "flying"),
                    ),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 78, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 84, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 78, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 109, effort = 3),
                        Stat(id = 5, name = "special-defense", baseStat = 85, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 100, effort = 0),
                    ),
                abilities = listOf(Ability(id = 66, name = "blaze", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 7,
                name = "Squirtle",
                height = 5,
                weight = 90,
                baseExperience = 63,
                species = Species(id = 7, frontDefault = "https://example.com/7.png"),
                types = listOf(Type(slot = 1, id = 11, name = "water")),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 44, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 48, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 65, effort = 1),
                        Stat(id = 4, name = "special-attack", baseStat = 50, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 64, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 43, effort = 0),
                    ),
                abilities = listOf(Ability(id = 67, name = "torrent", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 8,
                name = "Wartortle",
                height = 10,
                weight = 225,
                baseExperience = 142,
                species = Species(id = 8, frontDefault = "https://example.com/8.png"),
                types = listOf(Type(slot = 1, id = 11, name = "water")),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 59, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 63, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 80, effort = 1),
                        Stat(id = 4, name = "special-attack", baseStat = 65, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 80, effort = 1),
                        Stat(id = 6, name = "speed", baseStat = 58, effort = 0),
                    ),
                abilities = listOf(Ability(id = 67, name = "torrent", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 9,
                name = "Blastoise",
                height = 16,
                weight = 855,
                baseExperience = 239,
                species = Species(id = 9, frontDefault = "https://example.com/9.png"),
                types = listOf(Type(slot = 1, id = 11, name = "water")),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 79, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 83, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 100, effort = 1),
                        Stat(id = 4, name = "special-attack", baseStat = 85, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 105, effort = 2),
                        Stat(id = 6, name = "speed", baseStat = 78, effort = 0),
                    ),
                abilities = listOf(Ability(id = 67, name = "torrent", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 10,
                name = "Caterpie",
                height = 3,
                weight = 29,
                baseExperience = 39,
                species = Species(id = 10, frontDefault = "https://example.com/10.png"),
                types = listOf(Type(slot = 1, id = 7, name = "bug")),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 45, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 52, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 43, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 35, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 40, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 35, effort = 0),
                    ),
                abilities = listOf(
                    Ability(
                        id = 1,
                        name = "shield-dust",
                        isHidden = false,
                        slot = 1,
                    ),
                ),
            ),
            Pokemon(
                id = 11,
                name = "Metapod",
                height = 7,
                weight = 99,
                baseExperience = 72,
                species = Species(id = 11, frontDefault = "https://example.com/11.png"),
                types = listOf(Type(slot = 1, id = 7, name = "bug")),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 50, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 20, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 55, effort = 1),
                        Stat(id = 4, name = "special-attack", baseStat = 25, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 25, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 30, effort = 0),
                    ),
                abilities = listOf(
                    Ability(
                        id = 1,
                        name = "shield-dust",
                        isHidden = false,
                        slot = 1,
                    ),
                ),
            ),
            Pokemon(
                id = 12,
                name = "Butterfree",
                height = 11,
                weight = 320,
                baseExperience = 178,
                species = Species(id = 12, frontDefault = "https://example.com/12.png"),
                types =
                    listOf(
                        Type(slot = 1, id = 7, name = "bug"),
                        Type(slot = 2, id = 3, name = "flying"),
                    ),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 60, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 45, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 50, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 90, effort = 2),
                        Stat(id = 5, name = "special-defense", baseStat = 80, effort = 1),
                        Stat(id = 6, name = "speed", baseStat = 70, effort = 0),
                    ),
                abilities = listOf(
                    Ability(
                        id = 2,
                        name = "compound-eyes",
                        isHidden = false,
                        slot = 1,
                    ),
                ),
            ),
            Pokemon(
                id = 13,
                name = "Weedle",
                height = 3,
                weight = 32,
                baseExperience = 35,
                species = Species(id = 13, frontDefault = "https://example.com/13.png"),
                types =
                    listOf(
                        Type(slot = 1, id = 7, name = "bug"),
                        Type(slot = 2, id = 4, name = "poison"),
                    ),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 40, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 35, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 30, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 20, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 20, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 25, effort = 0),
                    ),
                abilities = listOf(
                    Ability(
                        id = 3,
                        name = "poison-powder",
                        isHidden = false,
                        slot = 1,
                    ),
                ),
            ),
            Pokemon(
                id = 14,
                name = "Kakuna",
                height = 6,
                weight = 100,
                baseExperience = 72,
                species = Species(id = 14, frontDefault = "https://example.com/14.png"),
                types =
                    listOf(
                        Type(slot = 1, id = 7, name = "bug"),
                        Type(slot = 2, id = 4, name = "poison"),
                    ),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 45, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 25, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 50, effort = 1),
                        Stat(id = 4, name = "special-attack", baseStat = 25, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 25, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 35, effort = 0),
                    ),
                abilities = listOf(
                    Ability(
                        id = 3,
                        name = "poison-powder",
                        isHidden = false,
                        slot = 1,
                    ),
                ),
            ),
            Pokemon(
                id = 15,
                name = "Beedrill",
                height = 10,
                weight = 295,
                baseExperience = 178,
                species = Species(id = 15, frontDefault = "https://example.com/15.png"),
                types =
                    listOf(
                        Type(slot = 1, id = 7, name = "bug"),
                        Type(slot = 2, id = 4, name = "poison"),
                    ),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 65, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 90, effort = 2),
                        Stat(id = 3, name = "defense", baseStat = 40, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 45, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 80, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 75, effort = 1),
                    ),
                abilities = listOf(Ability(id = 4, name = "swarm", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 16,
                name = "Pidgeot",
                height = 15,
                weight = 395,
                baseExperience = 220,
                species = Species(id = 16, frontDefault = "https://example.com/16.png"),
                types =
                    listOf(
                        Type(slot = 1, id = 3, name = "flying"),
                        Type(slot = 2, id = 1, name = "normal"),
                    ),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 83, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 100, effort = 1),
                        Stat(id = 3, name = "defense", baseStat = 75, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 70, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 70, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 91, effort = 2),
                    ),
                abilities = listOf(Ability(id = 5, name = "keen-eye", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 17,
                name = "Rattata",
                height = 3,
                weight = 35,
                baseExperience = 39,
                species = Species(id = 17, frontDefault = "https://example.com/17.png"),
                types = listOf(Type(slot = 1, id = 1, name = "normal")),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 30, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 56, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 35, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 25, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 35, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 72, effort = 0),
                    ),
                abilities = listOf(Ability(id = 6, name = "run-away", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 18,
                name = "Raticate",
                height = 7,
                weight = 185,
                baseExperience = 145,
                species = Species(id = 18, frontDefault = "https://example.com/18.png"),
                types = listOf(Type(slot = 1, id = 1, name = "normal")),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 55, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 81, effort = 1),
                        Stat(id = 3, name = "defense", baseStat = 60, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 50, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 70, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 97, effort = 2),
                    ),
                abilities = listOf(Ability(id = 6, name = "run-away", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 19,
                name = "Spearow",
                height = 3,
                weight = 20,
                baseExperience = 39,
                species = Species(id = 19, frontDefault = "https://example.com/19.png"),
                types =
                    listOf(
                        Type(slot = 1, id = 3, name = "flying"),
                        Type(slot = 2, id = 1, name = "normal"),
                    ),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 40, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 60, effort = 0),
                        Stat(id = 3, name = "defense", baseStat = 30, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 31, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 31, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 70, effort = 1),
                    ),
                abilities = listOf(Ability(id = 7, name = "keen-eye", isHidden = false, slot = 1)),
            ),
            Pokemon(
                id = 20,
                name = "Fearow",
                height = 12,
                weight = 380,
                baseExperience = 155,
                species = Species(id = 20, frontDefault = "https://example.com/20.png"),
                types =
                    listOf(
                        Type(slot = 1, id = 3, name = "flying"),
                        Type(slot = 2, id = 1, name = "normal"),
                    ),
                stats =
                    listOf(
                        Stat(id = 1, name = "hp", baseStat = 65, effort = 0),
                        Stat(id = 2, name = "attack", baseStat = 90, effort = 1),
                        Stat(id = 3, name = "defense", baseStat = 56, effort = 0),
                        Stat(id = 4, name = "special-attack", baseStat = 61, effort = 0),
                        Stat(id = 5, name = "special-defense", baseStat = 61, effort = 0),
                        Stat(id = 6, name = "speed", baseStat = 100, effort = 2),
                    ),
                abilities = listOf(Ability(id = 7, name = "keen-eye", isHidden = false, slot = 1)),
            ),
        )
}
