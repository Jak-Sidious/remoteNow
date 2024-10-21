package com.example.readynow.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.readynow.ui.screens.signin.SignInState
import com.example.readynow.ui.screens.signin.SignInViewModel

@Composable
fun LoginScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    onSignInSuccess: () -> Unit
) {
    var credentials by remember { mutableStateOf(Credentials()) }
    val signInState by viewModel.signInState.collectAsState()
    val context = LocalContext.current

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginField(
                value = credentials.login,
                onChange = { credentials = credentials.copy(login = it) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordField(
                value = credentials.pwd,
                onChange = { credentials = credentials.copy(pwd = it) },
                submit = { viewModel.signIn(credentials.login, credentials.pwd, rememberMe = credentials.remember) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            LabeledCheckBox(
                label = "Remember me",
                onCheckChanged = { credentials = credentials.copy(remember = !credentials.remember) },
                isChecked = credentials.remember
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { viewModel.signIn(credentials.login, credentials.pwd, rememberMe = credentials.remember) },
                enabled = credentials.isNotEmpty(),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            when (val state = signInState) {
                is SignInState.Loading -> CircularProgressIndicator()
                is SignInState.Error -> Text(state.message, color = MaterialTheme.colorScheme.error)
                is SignInState.Success -> {
                    LaunchedEffect(state) {
                        onSignInSuccess()
                    }
                }
                else -> {}
            }
        }
    }
}

data class Credentials(
    var login: String = "",
    var pwd: String = "",
    var remember: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return login.isNotEmpty() && pwd.isNotEmpty()
    }
}

@Composable
fun LabeledCheckBox(
    label: String,
    onCheckChanged: () -> Unit,
    isChecked: Boolean
) {
    Row(
        Modifier
            .clickable(onClick = onCheckChanged)
            .padding(4.dp)
    ) {
        Checkbox(checked = isChecked, onCheckedChange = { onCheckChanged() })
        Spacer(Modifier.size(6.dp))
        Text(label)
    }
}

@Composable
fun LoginField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Username",
    placeHolder: String = "Enter your username"
) {
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    val focusManager = LocalFocusManager.current

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeHolder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeHolder: String = "Enter your password"
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Lock,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                if (isPasswordVisible) Icons.Default.Clear else Icons.Default.Done,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { submit() }
        ),
        placeholder = { Text(placeHolder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}