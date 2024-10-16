package com.example.readynow.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.readynow.R
import com.example.readynow.data.model.DrawableSongCard
import com.example.readynow.data.model.Song
import java.time.LocalDate

// Step: Favorite collections grid - LazyGrid
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QueuedSongsList(
    modifier: Modifier = Modifier
) {

    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(queuedSongsData) { data ->
            SongCard(
                drawable = data.drawable,
                song = data.song
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private val queuedSongsData = mutableListOf(
    R.drawable.musical_note_music_svg to Song("Song1", LocalDate.of(2024, 10, 1), true),
    R.drawable.musical_note_music_svg to Song("Song2", LocalDate.of(2024, 10, 1), false),
    R.drawable.musical_note_music_svg to Song("Song3", LocalDate.of(2024, 10, 1), true),
    R.drawable.musical_note_music_svg to Song("Song4", LocalDate.of(2024, 10, 1), true),
    R.drawable.musical_note_music_svg to Song("Song5", LocalDate.of(2024, 10, 1), false),
    R.drawable.musical_note_music_svg to Song("Song6", LocalDate.of(2024, 10, 1), false),
    R.drawable.musical_note_music_svg to Song("Song7", LocalDate.of(2024, 10, 1), false),
    R.drawable.musical_note_music_svg to Song("Song8", LocalDate.of(2024, 10, 1), false),
    R.drawable.musical_note_music_svg to Song("Song9", LocalDate.of(2024, 10, 1), false),
    R.drawable.musical_note_music_svg to Song("Song10", LocalDate.of(2024, 10, 1), false),
    R.drawable.musical_note_music_svg to Song("Song11", LocalDate.of(2024, 10, 1), false),
    R.drawable.musical_note_music_svg to Song("Song12", LocalDate.of(2024, 10, 1), false),
).map { DrawableSongCard(it.first, it.second) }