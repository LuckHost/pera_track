package com.peratrack.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.peratrack.domain.models.UserParams
import com.peratrack.domain.repositories.UserParamsRepoInterface


class UserParamsRepoImpl(
    private val context: Context
) : UserParamsRepoInterface {

    companion object {
        private const val PREFS_FILE: String = "Account"

        private const val PREF_LOGIN_NAME: String = "login"
        private const val PREF_PASSWORD_NAME: String = "password"


        private var settings: SharedPreferences? = null
        private var sharedPrefEditor: SharedPreferences.Editor? = null
    }

    init {
        settings = context.getSharedPreferences(PREFS_FILE, MODE_PRIVATE)
        sharedPrefEditor = settings?.edit()
    }


    override fun saveParams(params: UserParams?) {
        params?.let {
            sharedPrefEditor?.putString(PREF_LOGIN_NAME, params.login)
                ?.putString(PREF_PASSWORD_NAME, params.password)?.apply()
        }
    }

    override fun deleteParams() {
        sharedPrefEditor?.remove(PREF_LOGIN_NAME)?.remove(PREF_PASSWORD_NAME)?.apply()
    }

    override fun getParams(): UserParams {
        settings?.let { prefs ->
            val login = prefs.getString(PREF_LOGIN_NAME, null)
            val password = prefs.getString(PREF_PASSWORD_NAME, null)

            login?.let {
                NullPointerException("The login was not found!")
            }
            password?.let {
                NullPointerException("The password was not found!")
            }

            return UserParams(
                login,
                password
            )
        }

        throw NoSuchElementException("SharedPreferences have not been initialized!")
    }
}