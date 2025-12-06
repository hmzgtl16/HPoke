package com.example.hpoke.core.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PokemonFull(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val species: SpeciesEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PokemonAbilityCrossRef::class,
            parentColumn = "pokemon_id",
            entityColumn = "move_id"
        )
    )
    val moves: List<MoveEntity>,
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