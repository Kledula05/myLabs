package com.Ustenko.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MatchDbHelper(context: Context) :
    SQLiteOpenHelper(context.applicationContext, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE $TABLE_MATCHES (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_PLAYER1_NAME TEXT NOT NULL,
                $COL_PLAYER2_NAME TEXT NOT NULL,
                $COL_SCORE1 INTEGER NOT NULL,
                $COL_SCORE2 INTEGER NOT NULL,
                $COL_TARGET_SCORE INTEGER NOT NULL,
                $COL_AVG_REACTION1 INTEGER NOT NULL,
                $COL_AVG_REACTION2 INTEGER NOT NULL,
                $COL_BEST_REACTION INTEGER NOT NULL,
                $COL_FALSE_STARTS TEXT NOT NULL,
                $COL_PLAYED_AT INTEGER NOT NULL
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MATCHES")
        onCreate(db)
    }

    companion object {
        const val DB_NAME = "reflex_duel.db"
        const val DB_VERSION = 1

        const val TABLE_MATCHES = "matches"
        const val COL_ID = "id"
        const val COL_PLAYER1_NAME = "player1Name"
        const val COL_PLAYER2_NAME = "player2Name"
        const val COL_SCORE1 = "score1"
        const val COL_SCORE2 = "score2"
        const val COL_TARGET_SCORE = "targetScore"
        const val COL_AVG_REACTION1 = "avgReaction1"
        const val COL_AVG_REACTION2 = "avgReaction2"
        const val COL_BEST_REACTION = "bestReaction"
        const val COL_FALSE_STARTS = "falseStarts"
        const val COL_PLAYED_AT = "playedAt"
    }
}
