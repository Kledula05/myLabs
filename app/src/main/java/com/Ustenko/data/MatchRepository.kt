package com.Ustenko.data

import android.content.ContentValues
import android.content.Context
import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

class MatchRepository(context: Context) {

    private val dbHelper = MatchDbHelper(context.applicationContext)
    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler(Looper.getMainLooper())

    fun insertMatch(match: Match, onDone: (() -> Unit)? = null) {
        executor.execute {
            val values = ContentValues().apply {
                put(MatchDbHelper.COL_PLAYER1_NAME, match.player1Name)
                put(MatchDbHelper.COL_PLAYER2_NAME, match.player2Name)
                put(MatchDbHelper.COL_SCORE1, match.score1)
                put(MatchDbHelper.COL_SCORE2, match.score2)
                put(MatchDbHelper.COL_TARGET_SCORE, match.targetScore)
                put(MatchDbHelper.COL_AVG_REACTION1, match.avgReaction1)
                put(MatchDbHelper.COL_AVG_REACTION2, match.avgReaction2)
                put(MatchDbHelper.COL_BEST_REACTION, match.bestReaction)
                put(MatchDbHelper.COL_FALSE_STARTS, match.falseStarts)
                put(MatchDbHelper.COL_PLAYED_AT, match.playedAt)
            }
            dbHelper.writableDatabase.insert(MatchDbHelper.TABLE_MATCHES, null, values)
            onDone?.let { mainHandler.post(it) }
        }
    }

    fun getRecentMatches(limit: Int = 20, onResult: (List<Match>) -> Unit) {
        executor.execute {
            val result = mutableListOf<Match>()
            dbHelper.readableDatabase.query(
                MatchDbHelper.TABLE_MATCHES,
                null, null, null, null, null,
                "${MatchDbHelper.COL_PLAYED_AT} DESC",
                limit.toString()
            ).use { cursor ->
                while (cursor.moveToNext()) {
                    result.add(
                        Match(
                            id = cursor.getLong(cursor.getColumnIndexOrThrow(MatchDbHelper.COL_ID)),
                            player1Name = cursor.getString(cursor.getColumnIndexOrThrow(MatchDbHelper.COL_PLAYER1_NAME)),
                            player2Name = cursor.getString(cursor.getColumnIndexOrThrow(MatchDbHelper.COL_PLAYER2_NAME)),
                            score1 = cursor.getInt(cursor.getColumnIndexOrThrow(MatchDbHelper.COL_SCORE1)),
                            score2 = cursor.getInt(cursor.getColumnIndexOrThrow(MatchDbHelper.COL_SCORE2)),
                            targetScore = cursor.getInt(cursor.getColumnIndexOrThrow(MatchDbHelper.COL_TARGET_SCORE)),
                            avgReaction1 = cursor.getLong(cursor.getColumnIndexOrThrow(MatchDbHelper.COL_AVG_REACTION1)),
                            avgReaction2 = cursor.getLong(cursor.getColumnIndexOrThrow(MatchDbHelper.COL_AVG_REACTION2)),
                            bestReaction = cursor.getLong(cursor.getColumnIndexOrThrow(MatchDbHelper.COL_BEST_REACTION)),
                            falseStarts = cursor.getString(cursor.getColumnIndexOrThrow(MatchDbHelper.COL_FALSE_STARTS)),
                            playedAt = cursor.getLong(cursor.getColumnIndexOrThrow(MatchDbHelper.COL_PLAYED_AT))
                        )
                    )
                }
            }
            mainHandler.post { onResult(result) }
        }
    }

    fun clearHistory(onDone: (() -> Unit)? = null) {
        executor.execute {
            dbHelper.writableDatabase.delete(MatchDbHelper.TABLE_MATCHES, null, null)
            onDone?.let { mainHandler.post(it) }
        }
    }
}
