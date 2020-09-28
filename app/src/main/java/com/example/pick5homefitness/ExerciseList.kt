package com.example.pick5homefitness

/* Ryan Sandford, 2020-09-06
 ExerciseList object stores data that whose integrity must be
 preserved when switching between fragments.*/
object ExerciseList {

    //Store a copy of the exercises selected by the pick 5 button so that they remain intact while
    //switching between fragments
    var selected = mutableListOf<Exercise>()

    //load favourites from shared preferences in main activity upon creation
    var favourites = mutableListOf<Exercise>()

    //use the primary key of each record as key in a hash map to access their corresponding images
    var images = hashMapOf(
        1 to arrayOf(R.drawable.ex1a,R.drawable.ex1b),
        2 to arrayOf(R.drawable.ex2a,R.drawable.ex2b),
        3 to arrayOf(R.drawable.ex3a,R.drawable.ex3b),
        4 to arrayOf(R.drawable.ex4a,R.drawable.ex4b),
        5 to arrayOf(R.drawable.ex5a,R.drawable.ex5b),
        6 to arrayOf(R.drawable.ex6a,R.drawable.ex6b),
        7 to arrayOf(R.drawable.ex7a,R.drawable.ex7b),
        8 to arrayOf(R.drawable.ex8a,R.drawable.ex8b),
        9 to arrayOf(R.drawable.ex9a,R.drawable.ex9b),
        10 to arrayOf(R.drawable.ex10a,R.drawable.ex10b),
        11 to arrayOf(R.drawable.ex11a,R.drawable.ex11b),
        12 to arrayOf(R.drawable.ex12a,R.drawable.ex12b),
        13 to arrayOf(R.drawable.ex13a,R.drawable.ex13b),
        14 to arrayOf(R.drawable.ex14a,R.drawable.ex14b),
        15 to arrayOf(R.drawable.ex15a,R.drawable.ex15b),
        16 to arrayOf(R.drawable.ex16a,R.drawable.ex16b),
        17 to arrayOf(R.drawable.ex17a,R.drawable.ex17b),
        18 to arrayOf(R.drawable.ex18a,R.drawable.ex18b),
        19 to arrayOf(R.drawable.ex19a,R.drawable.ex19b),
        20 to arrayOf(R.drawable.ex20a,R.drawable.ex20b),
        21 to arrayOf(R.drawable.ex21a,R.drawable.ex21b),
        22 to arrayOf(R.drawable.ex22a,R.drawable.ex22b),
        23 to arrayOf(R.drawable.ex23a,R.drawable.ex23b),
        24 to arrayOf(R.drawable.ex24a,R.drawable.ex24b),
        25 to arrayOf(R.drawable.ex25a,R.drawable.ex25b),
        26 to arrayOf(R.drawable.ex26a,R.drawable.ex26b),
        27 to arrayOf(R.drawable.ex27a,R.drawable.ex27b),
        28 to arrayOf(R.drawable.ex28a,R.drawable.ex28b),
        29 to arrayOf(R.drawable.ex29a,R.drawable.ex29b),
        30 to arrayOf(R.drawable.ex30a,R.drawable.ex30b),
        31 to arrayOf(R.drawable.ex31a,R.drawable.ex31b),
        32 to arrayOf(R.drawable.ex32a,R.drawable.ex32b),
        33 to arrayOf(R.drawable.ex33a,R.drawable.ex33b),
        34 to arrayOf(R.drawable.ex34a,R.drawable.ex34b),
        35 to arrayOf(R.drawable.ex35a,R.drawable.ex35b),
        36 to arrayOf(R.drawable.ex36a,R.drawable.ex36b),
        37 to arrayOf(R.drawable.ex37a,R.drawable.ex37b),
        38 to arrayOf(R.drawable.ex38a,R.drawable.ex38b),
        39 to arrayOf(R.drawable.ex39a,R.drawable.ex39b),
        40 to arrayOf(R.drawable.ex40a,R.drawable.ex40b),
        41 to arrayOf(R.drawable.ex41a,R.drawable.ex41b),
        42 to arrayOf(R.drawable.ex42a,R.drawable.ex42b),
        43 to arrayOf(R.drawable.ex43a,R.drawable.ex43b),
        44 to arrayOf(R.drawable.ex44a,R.drawable.ex44b),
        45 to arrayOf(R.drawable.ex45a,R.drawable.ex45b),
        46 to arrayOf(R.drawable.ex46a,R.drawable.ex46b),
        47 to arrayOf(R.drawable.ex47a,R.drawable.ex47b),
        48 to arrayOf(R.drawable.ex48a,R.drawable.ex48b),
        49 to arrayOf(R.drawable.ex49a,R.drawable.ex49b),
        50 to arrayOf(R.drawable.ex50a,R.drawable.ex50b),
        51 to arrayOf(R.drawable.ex51a,R.drawable.ex51b),
        52 to arrayOf(R.drawable.ex52a,R.drawable.ex52b),
        53 to arrayOf(R.drawable.ex53a,R.drawable.ex53b),
        54 to arrayOf(R.drawable.ex54a,R.drawable.ex54b),
        55 to arrayOf(R.drawable.ex55a,R.drawable.ex55b),
        56 to arrayOf(R.drawable.ex56a,R.drawable.ex56b),
        57 to arrayOf(R.drawable.ex57a,R.drawable.ex57b),
        58 to arrayOf(R.drawable.ex58a,R.drawable.ex58b),
        59 to arrayOf(R.drawable.ex59a,R.drawable.ex59b),
        60 to arrayOf(R.drawable.ex60a,R.drawable.ex60b),
        61 to arrayOf(R.drawable.ex61a,R.drawable.ex61b),
        62 to arrayOf(R.drawable.ex62a,R.drawable.ex62b),
        63 to arrayOf(R.drawable.ex63a,R.drawable.ex63b),
        64 to arrayOf(R.drawable.ex64a,R.drawable.ex64b),
        65 to arrayOf(R.drawable.ex65a,R.drawable.ex65b),
        66 to arrayOf(R.drawable.ex66a,R.drawable.ex66b),
        67 to arrayOf(R.drawable.ex67a,R.drawable.ex67b),
        68 to arrayOf(R.drawable.ex68a,R.drawable.ex68b),
        69 to arrayOf(R.drawable.ex69a,R.drawable.ex69b),
        70 to arrayOf(R.drawable.ex70a,R.drawable.ex70b),
        71 to arrayOf(R.drawable.ex71a,R.drawable.ex71b),
        72 to arrayOf(R.drawable.ex72a,R.drawable.ex72b),
        73 to arrayOf(R.drawable.ex73a,R.drawable.ex73b),
        74 to arrayOf(R.drawable.ex74a,R.drawable.ex74b),
        75 to arrayOf(R.drawable.ex75a,R.drawable.ex75b),
        76 to arrayOf(R.drawable.ex76a,R.drawable.ex76b),
        77 to arrayOf(R.drawable.ex77a,R.drawable.ex77b),
        78 to arrayOf(R.drawable.ex78a,R.drawable.ex78b),
        79 to arrayOf(R.drawable.ex79a,R.drawable.ex79b),
        80 to arrayOf(R.drawable.ex80a,R.drawable.ex80b),
        81 to arrayOf(R.drawable.ex81a,R.drawable.ex81b)
        )

}