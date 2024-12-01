package com.example.zooapp.models

import com.example.zooapp.R


data class character (
    val id: Int,
    val name: String,
    val species: String,
    val imageRes: Int,
    val description: String,
    val curiosities: String,
    var isFavorite: Boolean = false
)

val characterList = listOf(
    character(
        id = 1,
        name = "Rudeus",
        species = "Human",
        imageRes = R.drawable.rudeus,
        description =
        "Rudeus Greyrat is the main protagonist of Mushoku Tensei" +
        " Realizing he wasted away his previous life, he resolves to live this new one to its " +
                "fullest and the series revolves around how he impacts his new world all " +
                "whilst slowly growing out of his reclusive ways.",
        curiosities = "By the end of the series become one of " +
                "the seven great powers after defeating" +
                "North God Kalman III"
    ),
    character(
        id = 2,
        name = "Eris",
        species = "Human",
        imageRes = R.drawable.eris,
        description = "Eris Greyrat, born Eris Boreas " +
                "Greyrat , is a noble girl and second cousin of Rudeus. " +
                "She is a Character with a short temper but has potential in the " +
                "Sword God Style. During her journey to return home following " +
                "the Teleport Incident, she grows to love Rudeus",
        curiosities = "As a member os the Boreas family Eris has a special affection for beast folk"
    ),
    character(
        id = 2,
        name = "Roxy Migurdia",
        species = "Demon/Mirgurdia",
        imageRes = R.drawable.roxy,
        description = "Roxy M. Greyrat, born Roxy Migurdia," +
                " is a talented Migurd mage, and a former magic tutor. Because she can't use telepathy," +
                " she leaves her village due to feeling isolated from her peers. Unable to make a " +
                "stable living as an adventurer, she becomes a travelling tutor and eventually " +
                "becomes Rudeus' teacher. After the Teleport Incident" +
                ", Roxy helps Paul to search the world for survivors.",
        curiosities = "Roxy likes sweets so much that she once sworn not to " +
                "return to the Demon Continent again, " +
                "because the Demon Continent doesn't have any sweet food."
    ),
    character(
        id = 2,
        name = "Sylphiette Greyrat",
        species = "Elf / Human / Beast Hybrid",
        imageRes = R.drawable.sylph,
        description = "Sylphiette Greyrat is Rudeus' childhood friend " +
                "who is part human, elf, and beast race. Following the Teleport Incident, " +
                "during which her hair turned white from mana exhaustion, " +
                "she became Princess Ariel's personal bodyguard under the alias Fitts.",
        curiosities = "Sylphiette's green hair is a result of her having the Laplace " +
                "Factor in her, which grants her aptitude towards " +
                "all forms of magic and a large amount of magic power."
    ),
    character(
        id = 2,
        name = "Orsted",
        species = "Dragon-Human Deity Hybrid",
        imageRes = R.drawable.orsted,
        description = "Orsted is the current Dragon God, a title given to the strongest member of " +
                "the Dragon Tribe, and the sworn enemy of \"Human God\"." +
                " Orsted was born roughly 10,000~20,000 years ago in " +
                "the Dragon World from the union between his father, the " +
                "First Dragon God, and his mother Lunaria, the daughter of the" +
                " \"Human God\".",
        curiosities = "Orsted unexpectedly enjoyed the company of Rudeus's child, " +
                "Lucy, giving her a shoulder ride when she was small and even " +
                "taught her a trick to do magic when she had difficulties."
    ),
    character(
        id = 2,
        name = "Lucy Greyrat",
        species = "Elf / Human / Beast Hybrid",
        imageRes = R.drawable.lucy,
        description = "First daughter of Rudeus and Sylphiette Greyrat",
        curiosities = "Lucy is adorable"
    ),



)
