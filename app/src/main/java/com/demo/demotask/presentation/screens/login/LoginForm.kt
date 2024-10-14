package com.demo.demotask.presentation.screens.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.demotask.R
import com.demo.demotask.data.model.UserModel
import com.demo.demotask.presentation.component.CircularIndeterminateProgressBar
import com.demo.demotask.presentation.ui.theme.Purple80
import com.demo.demotask.presentation.ui.theme.black
import com.demo.demotask.presentation.ui.theme.white
import com.demo.demotask.presentation.ui.utils.getCurrentTime
import com.demo.demotask.presentation.vm.LoginViewModel


@Composable
fun LoginForm(loginViewModel: LoginViewModel) {
    Surface(modifier = Modifier.background(white)) {
        val context = LocalContext.current
        val scrollState = rememberScrollState()

        var credentials by remember { mutableStateOf(Credentials()) }
        val isLoading by loginViewModel.isLoading.collectAsState()

        val focusRequester = remember { FocusRequester() }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .verticalScroll(state = scrollState)
        ) {
            CircularIndeterminateProgressBar(isDisplayed = isLoading, 0.4f)

            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "",
            )
            Spacer(modifier = Modifier.height(30.dp))


            Spacer(modifier = Modifier.height(40.dp))

            LoginField(
                value = credentials.login,
                onChange = { data -> credentials = credentials.copy(login = data) },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordField(
                value = credentials.pwd,
                onChange = { data -> credentials = credentials.copy(pwd = data) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    login(credentials,loginViewModel)
                },
                enabled = credentials.isNotEmpty(),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple80,
                    contentColor = black
                )
            ) {
                Text(
                    stringResource(R.string.confirm),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}
@SuppressLint("SuspiciousIndentation")
fun login(creds: Credentials, loginViewModel: LoginViewModel): Boolean {
    var currentTime = getCurrentTime()
    loginViewModel.login(UserModel( creds.login,creds.pwd,currentTime))
    return true

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = androidx.compose.ui.res.stringResource(id = R.string.title_email),
    placeholder: String = androidx.compose.ui.res.stringResource(id = R.string.title_email)
) {

    val focusManager = LocalFocusManager.current
    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        textAlign = TextAlign.Start,
        color = Color.Black,
        fontWeight = FontWeight.ExtraBold,
    )
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        shape = RoundedCornerShape(10.dp),
        placeholder = { Text(placeholder) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        textStyle = TextStyle(textDirection = TextDirection.ContentOrLtr)

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = androidx.compose.ui.res.stringResource(id = R.string.pin_number),
    placeholder: String = androidx.compose.ui.res.stringResource(id = R.string.pin_number)
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "",
                tint = Color.Red
            )
        }
    }

    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        textAlign = TextAlign.Start,
        color = Color.Black,
        fontWeight = FontWeight.ExtraBold
    )
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        placeholder = { Text(placeholder) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),

        textStyle = TextStyle(textDirection = TextDirection.ContentOrLtr),
        shape = RoundedCornerShape(10.dp)


    )
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