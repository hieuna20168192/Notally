package com.example.notally.repos.model.store

import com.example.notally.repos.model.BulletPoint
import com.example.notally.repos.model.Note

object NoteStore {

    val notes = listOf(
        Note(
            1L,
            "Prepare for Google Interview",
            "I spend most of the time learning skills, completing side projects " +
                    "and opening to come up with the new challenges",
            mutableListOf(
                BulletPoint("Read Chapter II Operating System"),
                BulletPoint("Do question 82 AlgoExpert", true),
                BulletPoint("Read Chapter I Computing Network"),
                BulletPoint("Take note problem solving from youtube"),
            )
        ),
        Note(
            2L,
            "Road to the silicon valley",
            "Silicon Valley is a region in Northern California that serves as a global center " +
                    "for high technology and innovation. Located in the southern part of the San Francisco Bay Area," +
                    " it corresponds roughly to the geographical Santa Clara Valley.[1][2][3] San Jose is Silicon Valley's " +
                    "largest city, the third-largest in California, and the tenth-largest in the United States; other major Silicon " +
                    "Valley cities include Sunnyvale, Santa Clara, Redwood City, Mountain View, Palo Alto, Menlo Park, and Cupertino."
        ),
        Note(
            3L,
            "Work from home",
            "Because the pandemic. The leader allowed me to work remotely all the time!",
            mutableListOf(
                BulletPoint("Carried out the two Dell Monitors"),
                BulletPoint("Cater for Room online environment"),
                BulletPoint("Take some snap photos to relax")
            )
        ),
        Note(
            4L,
            "Android 12",
            "A new system UI with Material You that's expressive, dynamic, and personal. Extend your apps with redesigned widgets, " +
                    "AppSearch, Game Mode, and new codecs. Support new protections like privacy dashboard and approximate location. " +
                    "Improve productivity with rich content insertion, easier blurs, improved native debugging, and much more.",
        )
    )
}
