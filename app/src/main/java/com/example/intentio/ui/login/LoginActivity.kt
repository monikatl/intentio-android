package com.example.intentio.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.intentio.R
import com.example.intentio.data.UserType
import com.example.intentio.databinding.ActivityLoginBinding
import com.example.intentio.ui.main.MainActivity

import kotlin.system.exitProcess


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        binding.isStartView = loginViewModel.isStartView;

        binding.user.setOnClickListener { setUserTypeAndHideStartButtons(UserType.FAITHFUL) }
        binding.priest.setOnClickListener { setUserTypeAndHideStartButtons(UserType.PRIEST) }

        binding.backButton.setOnClickListener {
            if(binding.isStartView == true)
                exitProcess(0)
            binding.isStartView = true

        }
        binding.registerButton.setOnClickListener {
            binding.registerButton.visibility = View.GONE
            binding.loginButton.text = "UTÓWRZ"
            binding.passwordRepetition.visibility = View.VISIBLE
        }

        binding.infoButton.setOnClickListener {
            showInfoDialog()
        }

        binding.loginButton.setOnClickListener {
            openIntentio()
        }


        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)
            openIntentio()

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }
    }

    private fun openIntentio() {
        val intent = Intent(this, MainActivity::class.java)
        //intent.putExtra("klucz", "wartość")
        startActivity(intent)
    }

    private fun openApp() {
        TODO("Not yet implemented")
    }

    private fun showInfoDialog() {
        AlertDialog.Builder(this)
            .setTitle("Parafia Świętej Rodziny w Częstochowie")
            .setMessage("fksdjdsjkfjndlkfnjklnseklfjl;ks fksljefls fjskjfkl sjfjsfk jsj/dflkjs/ dfkkd/jfk sfsjdlfjasflk dflj sa.dlkfj fhldsjv soda fsdfkjsd")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun setUserTypeAndHideStartButtons(chosenUserType: UserType) {
        binding.userType = chosenUserType
        binding.isStartView = false
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = "Welcome"
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}