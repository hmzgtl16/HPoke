package com.example.hpoke.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonFull(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val species: SpritesEntity,
    @Relation(
        entity = PokemonStatCrossRef::class,
        parentColumn = "id",
        entityColumn = "stat_id"
    )
    val stats: List<PokemonWithStat>,
    @Relation(
        entity = PokemonTypeCrossRef::class,
        parentColumn = "id",
        entityColumn = "type_id"
    )
    val types: List<PokemonWithType>,
    @Relation(
        entity = PokemonAbilityCrossRef::class,
        parentColumn = "id",
        entityColumn = "ability_id"
    )
    val abilities: List<PokemonWithAbility>,
)