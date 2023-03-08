/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.compose.jetsurvey.survey

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose.jetsurvey.R
import com.example.compose.jetsurvey.theme.stronglyDeemphasizedAlpha
import com.example.compose.jetsurvey.util.supportWideScreen


@OptIn(ExperimentalMaterial3Api::class) // Scaffold is experimental in m3
@Composable
fun SurveyResultScreen(
    content: @Composable (PaddingValues) -> Unit,
) {
    var shouldShowPreviousButton by remember { mutableStateOf(false) }
    var shouldShowDoneButton by remember { mutableStateOf(false) }
    var questionIndex by remember { mutableStateOf(0) }

    Surface(modifier = Modifier.supportWideScreen()) {
        Scaffold(
            topBar = {
                SurveyTopAppBar(
                    questionIndex = questionIndex,
                    totalQuestionsCount = 5,
//                    onClosePressed = onClosePressed,
                )
            },
            content = content,
            bottomBar = {
                SurveyBottomBar(
                    shouldShowPreviousButton = shouldShowPreviousButton,
                    shouldShowDoneButton = shouldShowDoneButton,
                    previousClicked = {
                        questionIndex--
                        if(questionIndex == 0) {
                            shouldShowPreviousButton = false
                        }
                        if(questionIndex < 4) {
                            shouldShowDoneButton = false
                        }
                    },
                    nextClicked = {
                        questionIndex++
                        shouldShowPreviousButton = true
                        if(questionIndex >= 4) {
                            shouldShowDoneButton = true
                        }
                    },
                    doneClicked = {
                        questionIndex = 0
                        shouldShowPreviousButton = false
                        shouldShowDoneButton = false
                    }
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurveyTopAppBar(
    questionIndex: Int,
    totalQuestionsCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        CenterAlignedTopAppBar(
            title = {
                TopAppBarTitle(
                    modifier = modifier,
                    questionIndex = 0,
                    totalQuestionsCount = 5
                )
            },
            modifier = modifier,
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                }
            },
        )
        val animatedProgress by animateFloatAsState(
            targetValue = (questionIndex + 1) / totalQuestionsCount.toFloat(),
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        )
        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        )
    }
}

@Composable
fun TopAppBarTitle(
    modifier: Modifier = Modifier,
    questionIndex: Int,
    totalQuestionsCount: Int
) {
    Row() {
        Text(
            text = (questionIndex + 1).toString(),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha)
        )
        Text(
            text = stringResource(R.string.question_count, totalQuestionsCount),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        )
    }
}

@Composable
fun SurveyBottomBar(
    shouldShowDoneButton: Boolean,
    shouldShowPreviousButton: Boolean,
    previousClicked: () -> Unit,
    nextClicked: () -> Unit,
    doneClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        if (shouldShowPreviousButton) {
            Button(
                onClick = previousClicked,
                modifier = modifier
                    .padding(8.dp)
                    .height(48.dp)
                    .weight(1f)
            ) {
                Text(text = "Previous")
            }
        }
        if (shouldShowDoneButton) {
            Button(
                onClick = doneClicked,
                modifier = modifier
                    .padding(8.dp)
                    .height(48.dp)
                    .weight(1f)
            ) {
                Text("Done")
            }
        } else {
            Button(
                onClick = nextClicked,
                modifier = modifier
                    .padding(8.dp)
                    .height(48.dp)
                    .weight(1f)
            ) {
                Text("Next")
            }
        }

    }
}
