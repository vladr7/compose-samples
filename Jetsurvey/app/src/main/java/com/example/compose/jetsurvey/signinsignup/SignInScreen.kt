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

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.jetsurvey.R

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var passwordText by remember { mutableStateOf("Password") }

        SignInTopBar("Sign In", onNavUp = {}, modifier)
        Spacer(modifier = modifier.padding(16.dp))
        Email(emailFocusChanged = {})
        Spacer(modifier = modifier.padding(8.dp))
        Password(
            passwordValue = passwordText,
            passwordValueChanged = { newText ->
                passwordText = newText
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInTopBar(
    topAppBarText: String,
    onNavUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = topAppBarText,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = stringResource(id = R.string.back),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            Spacer(modifier = Modifier.width(68.dp))
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(
    modifier: Modifier = Modifier,
    passwordValueChanged: (String) -> Unit,
    passwordValue: String
) {
    var showPassword by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = passwordValue, onValueChange = passwordValueChanged,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(fraction = 0.9f),
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { showPassword = false }) {
                    Icon(Icons.Filled.Visibility, contentDescription = null)
                }
            } else {
                IconButton(onClick = { showPassword = true }) {
                    Icon(Icons.Filled.VisibilityOff, contentDescription = null)
                }
            }
        },
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
    )
}


@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    SignInScreen()
}