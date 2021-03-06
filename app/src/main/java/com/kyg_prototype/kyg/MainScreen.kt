package com.kyg_prototype.kyg

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kyg_prototype.kyg.*
import com.kyg_prototype.kyg.R
import com.kyg_prototype.kyg.ui.theme.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalFoundationApi
@Composable
fun MainScreen(gameViewModel: GameViewModel) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            ChipSection(gameViewModel)
            ScoreBoard()
            FitnessAnalytics(
                features = listOf(
                    Feature(
                        title = "Calories Burned",
                        text = "0",
                        R.drawable.fire,
                        LightRed,
                        LightRed,
                        LightRed
                    ),
                    Feature(
                        title = "Total Steps",
                        text = "0",
                        R.drawable.shoe_print,
                        MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                        MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                        MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                    )
                )
            )
            Button(
                onClick = { gameViewModel.addGame(Game()) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ButtonBlue
                ),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    "Start Game",
                    color = TextWhite
                )
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun displayCurrentDate(): String? {
    val now = LocalDateTime.now()
    return DateTimeFormatter.ofPattern("EEE, d MMM yyyy").format(now)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GreetingSection(
    name: String = "JJ"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Game Activity Log, $name",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "${displayCurrentDate()}",
                style = MaterialTheme.typography.body1
            )
        }
        Surface(
            modifier = Modifier.size(65.dp),
            shape = CircleShape,
            color = Color.White
        ) {
            ProfileImage(
                image = painterResource(id = R.drawable.bball),
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun ChipSection(gameViewModel: GameViewModel) {
    val chip: List<Game> by gameViewModel.games.observeAsState(listOf())
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chip.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(text = "${chip[it].title} ${chip.size}", color = TextWhite)
            }
        }
    }
}

@Composable
fun ScoreBoard(
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 25.dp, vertical = 25.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "00",
                fontSize = 100.sp,
                color = TextWhite

            )
            Text(
                text = "Points",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
        }
        Column {
            Text(
                text = "0-0",
                fontSize = 100.sp,
                color = TextWhite
            )
            Text(
                text = "Field Goals ??? 0%",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Right,
                color = TextWhite
            )
        }

    }
}

@ExperimentalFoundationApi
@Composable
fun FitnessAnalytics(features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Fitness Analytics",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 70.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
                    .size(35.dp)
            )
            Text(
                text = feature.text,
                color = TextWhite,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Transparent)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}

@Composable
fun ProfileImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .clip(CircleShape)
    )
}


