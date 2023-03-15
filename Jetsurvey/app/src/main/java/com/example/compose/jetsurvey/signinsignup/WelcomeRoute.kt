/*
 * Copyright 2023 The Android Open Source Project
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

package com.example.compose.jetsurvey.signinsignup

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.jetsurvey.survey.FreeTimeQuestion
import com.example.compose.jetsurvey.survey.SuperheroQuestion
import com.example.compose.jetsurvey.survey.SurveyResultScreen

@Composable
fun WelcomeRoute(
    onNavigateToSignIn: (email: String) -> Unit,
    onNavigateToSignUp: (email: String) -> Unit,
    onSignInAsGuest: () -> Unit,
) {
    val welcomeViewModel: WelcomeViewModel = viewModel(factory = WelcomeViewModelFactory())

    var questionIndex by remember { mutableStateOf(0) }
    var shouldShowPreviousButton by remember { mutableStateOf(false) }
    var shouldShowDoneButton by remember { mutableStateOf(false) }

    SurveyResultScreen(
        questionIndex,
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
        },
        shouldShowPreviousButton = shouldShowPreviousButton,
        shouldShowDoneButton = shouldShowDoneButton
    ) { paddingValues ->
        when(questionIndex) {
            0 -> {
                FreeTimeQuestion(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            1 -> {
                SuperheroQuestion(
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }

//    SignInScreen()

//    WelcomeScreen(
//        onSignInSignUp = { email ->
//            welcomeViewModel.handleContinue(
//                email = email,
//                onNavigateToSignIn = onNavigateToSignIn,
//                onNavigateToSignUp = onNavigateToSignUp,
//            )
//        },
//        onSignInAsGuest = {
//            welcomeViewModel.signInAsGuest(onSignInAsGuest)
//        },
//    )
}
