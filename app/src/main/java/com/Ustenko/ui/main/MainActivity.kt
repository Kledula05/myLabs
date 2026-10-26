package com.Ustenko.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Ustenko.R
import com.Ustenko.data.MatchRepository
import com.Ustenko.model.GameExtras
import com.Ustenko.model.GameMode
import com.Ustenko.ui.base.BaseActivity
import com.Ustenko.ui.game.GameActivity
import com.Ustenko.ui.settings.SettingsActivity

class MainActivity : BaseActivity() {

    private lateinit var repository: MatchRepository
    private lateinit var adapter: MatchAdapter

    private lateinit var modeSingleButton: AppCompatButton
    private lateinit var modePairButton: AppCompatButton
    private lateinit var player1InputLayout: com.google.android.material.textfield.TextInputLayout
    private lateinit var player2InputLayout: com.google.android.material.textfield.TextInputLayout
    private lateinit var player1NameInput: TextInputEditText
    private lateinit var player2NameInput: TextInputEditText
    private lateinit var targetScoreChipGroup: ChipGroup
    private lateinit var emptyHistoryText: android.widget.TextView

    private var mode: GameMode = GameMode.PAIR

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = MatchRepository(this)

        modeSingleButton = findViewById(R.id.modeSingleButton)
        modePairButton = findViewById(R.id.modePairButton)
        player1InputLayout = findViewById(R.id.player1InputLayout)
        player2InputLayout = findViewById(R.id.player2InputLayout)
        player1NameInput = findViewById(R.id.player1NameInput)
        player2NameInput = findViewById(R.id.player2NameInput)
        targetScoreChipGroup = findViewById(R.id.targetScoreChipGroup)
        emptyHistoryText = findViewById(R.id.emptyHistoryText)

        val recycler: RecyclerView = findViewById(R.id.recentMatchesRecycler)
        adapter = MatchAdapter()
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        recycler.isNestedScrollingEnabled = false

        findViewById<android.widget.ImageButton>(R.id.settingsButton).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        player1NameInput.setText(
            settings.player1Name.ifEmpty { getString(R.string.default_player1) }
        )
        player2NameInput.setText(
            settings.player2Name.ifEmpty { getString(R.string.default_player2) }
        )

        selectTargetScoreChip(settings.targetScore)
        setMode(GameMode.PAIR)

        modeSingleButton.setOnClickListener { setMode(GameMode.SINGLE) }
        modePairButton.setOnClickListener { setMode(GameMode.PAIR) }

        findViewById<android.widget.Button>(R.id.playButton).setOnClickListener { onPlayClicked() }
    }

    override fun onResume() {
        super.onResume()
        refreshHistory()
    }

    private fun setMode(newMode: GameMode) {
        mode = newMode
        val isPair = newMode == GameMode.PAIR
        player2InputLayout.visibility = if (isPair) android.view.View.VISIBLE else android.view.View.GONE
        modePairButton.setBackgroundColor(
            getColor(if (isPair) R.color.accent else android.R.color.transparent)
        )
        modeSingleButton.setBackgroundColor(
            getColor(if (!isPair) R.color.accent else android.R.color.transparent)
        )
    }

    private fun selectTargetScoreChip(targetScore: Int) {
        val chipId = when (targetScore) {
            3 -> R.id.chipScore3
            7 -> R.id.chipScore7
            else -> R.id.chipScore5
        }
        targetScoreChipGroup.check(chipId)
    }

    private fun selectedTargetScore(): Int {
        val checkedChip = targetScoreChipGroup.checkedChipId
        return when (checkedChip) {
            R.id.chipScore3 -> 3
            R.id.chipScore7 -> 7
            else -> 5
        }
    }

    private fun onPlayClicked() {
        val targetScore = selectedTargetScore()
        val player1 = player1NameInput.text?.toString()?.trim()
            ?.ifEmpty { getString(R.string.default_player1) } ?: getString(R.string.default_player1)
        val player2 = player2NameInput.text?.toString()?.trim()
            ?.ifEmpty { getString(R.string.default_player2) } ?: getString(R.string.default_player2)

        settings.targetScore = targetScore
        if (mode == GameMode.PAIR) {
            settings.player1Name = player1
            settings.player2Name = player2
        }

        val intent = Intent(this, GameActivity::class.java).apply {
            putExtra(GameExtras.MODE, mode.name)
            putExtra(GameExtras.PLAYER1_NAME, player1)
            putExtra(GameExtras.PLAYER2_NAME, if (mode == GameMode.PAIR) player2 else "")
            putExtra(GameExtras.TARGET_SCORE, targetScore)
        }
        startActivity(intent)
    }

    private fun refreshHistory() {
        repository.getRecentMatches(20) { matches ->
            adapter.submitList(matches)
            emptyHistoryText.visibility =
                if (matches.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }
    }
}
