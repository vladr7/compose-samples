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

package com.example.compose.jetsurvey.signinsignup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.jetsurvey.R
import com.example.compose.jetsurvey.theme.stronglyDeemphasizedAlpha

@Composable
fun WelcomeScreen(
    onSignInSignUp: (email: String) -> Unit,
    onSignInAsGuest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var showBranding by remember { mutableStateOf(true) }

        if(showBranding) {
            Branding(modifier)
        }
        SingInCreateAccount(
            modifier,
            emailFocusChanged = { focus ->
                showBranding = !focus
            }
        )
    }
}

@Composable
fun Branding(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo(
            modifier
                .padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.app_tagline),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = Color.White,
        )
    }
}

@Composable
private fun Logo(
    modifier: Modifier = Modifier,
//    lightTheme: Boolean = LocalContentColor.current.luminance() < 0.1f,
    lightTheme: Boolean = false,
) {
    val assetId = if (lightTheme) {
        R.drawable.ic_logo_light
    } else {
        R.drawable.ic_logo_dark
    }
    Image(
        painter = painterResource(id = assetId),
        modifier = modifier,
        contentDescription = null
    )
}

@Composable
fun SingInCreateAccount(
    modifier: Modifier = Modifier,
    emailFocusChanged: (Boolean) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sign_in_create_account),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 64.dp, bottom = 12.dp)
        )
        Email(
            emailFocusChanged = emailFocusChanged
        )
        Spacer(modifier = modifier.padding(8.dp))
        Button(
            onClick = { /*TODO*/ }, modifier = modifier
                .padding(8.dp)
                .fillMaxWidth(0.9f)
        ) {
            Text(text = stringResource(id = R.string.user_continue))
        }
        OrSignInAsGuest(modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(
    modifier: Modifier = Modifier,
    emailFocusChanged: (Boolean) -> Unit
) {
    var focus by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = "Email", onValueChange = {},
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(fraction = 0.9f)
            .onFocusChanged { focusState ->
                focus = focusState.isFocused
            },
        label = {
            Text(
                text = stringResource(id = R.string.email),
                style = MaterialTheme.typography.bodyMedium,
            )
        },
    )
    emailFocusChanged(focus) // todo evaluate this
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrSignInAsGuest(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.or),
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        modifier = modifier.padding(8.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),
    )
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(0.9f),
    ) {
        Text(stringResource(id = R.string.sign_in_guest))
    }
}
