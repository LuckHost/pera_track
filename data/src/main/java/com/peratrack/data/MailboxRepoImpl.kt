package com.peratrack.data

import android.util.Log
import com.peratrack.domain.models.Receipt
import com.peratrack.domain.repositories.MailboxRepoInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Folder
import javax.mail.PasswordAuthentication
import javax.mail.Session

class MailboxRepoImpl: MailboxRepoInterface {

    override fun fetchAllReceipts(
        username: String,
        password: String
    ): MutableList<Receipt> {

        val result = mutableListOf<Receipt>()

        CoroutineScope(Dispatchers.IO).launch {
            val properties = Properties().apply {
                put(
                    "mail.imap.host",
                    "imap.mail.ru"
                )
                put("mail.imap.port", "993")
                put("mail.imap.ssl.enable", "true")
            }

            try {
                val session = Session.getInstance(properties, object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(username, password)
                    }
                })

                // Подключаемся к IMAP серверу
                val store = session.getStore("imap")
                store.connect()

                // Открываем папку "Входящие" (INBOX)
                val inbox = store.getFolder("INBOX")
                inbox.open(Folder.READ_ONLY)

                // Получаем письма (можно добавить фильтры, например, только непрочитанные письма)
                val messages = inbox.messages

                // Обрабатываем каждое письмо
                for (message in messages) {
                    Log.d("MainView", "From: ${message.from.joinToString()}")
                    Log.d("MainView", "Subject: ${message.subject}")

                    val newReceipt = Receipt(
                        0,
                        message.sentDate,
                        message.content.toString(),
                        0f
                    )

                    result.add(newReceipt)
                }

                // Закрываем соединение
                inbox.close(false)
                store.close()

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("MainView", e.message.toString())
            }
        }

        return result
    }
}