/*
 * Copyright 2022 The Android Open Source Project
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

package com.example.compose.jetsurvey.survey.question

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.jetsurvey.R
import com.example.compose.jetsurvey.survey.QuestionWrapper

@Composable
fun SingleChoiceQuestion(
    @StringRes titleResourceId: Int,
    @StringRes directionsResourceId: Int,
    possibleAnswers: List<Superhero>,
    selectedAnswer: Superhero?,
    onOptionSelected: (Superhero) -> Unit,
    modifier: Modifier = Modifier,
) {
    QuestionWrapper(
        titleResourceId = titleResourceId,
        directionsResourceId = directionsResourceId,
        modifier = modifier.selectableGroup(),
    ) {
        possibleAnswers.forEach { superhero ->
            val selected = selectedAnswer == superhero
            RadioButtonWithImageRow(
                modifier = Modifier.padding(vertical = 8.dp),
                text = stringResource(id = superhero.stringResourceId),
                imageResourceId = superhero.imageResourceId,
                selected = selected,
                onOptionSelected = {
                    onOptionSelected(superhero)
                }
            )
        }
    }
}

@Composable
fun RadioButtonWithImageRow(
    text: String,
    @DrawableRes imageResourceId: Int,
    selected: Boolean,
    onOptionSelected: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Surface(
        border = BorderStroke(width = 1.dp, color = Color.DarkGray),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(top = 4.dp)
            .selectable(
                selected = selected,
                onClick = onOptionSelected,
                role = Role.RadioButton
            )
    ) {
        Row(
            modifier = modifier
                .padding(start = 16.dp, bottom = 5.dp, top = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageResourceId), contentDescription = null
            )
            Text(
                text = text,
                modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            )
            RadioButton(
                selected = selected, onClick = null,
                modifier = modifier.padding(end = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun SingleChoiceQuestionPreview() {
    val possibleAnswers = listOf(
        Superhero(R.string.spark, R.drawable.spark),
        Superhero(R.string.lenz, R.drawable.lenz),
        Superhero(R.string.bugchaos, R.drawable.bug_of_chaos),
    )
    var selectedAnswer by remember { mutableStateOf<Superhero?>(null) }

}

data class Superhero(@StringRes val stringResourceId: Int, @DrawableRes val imageResourceId: Int)
