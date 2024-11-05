package com.peratrack.data

import com.peratrack.domain.models.Receipt
import com.peratrack.domain.repositories.MailboxRepoInterface
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Folder
import javax.mail.PasswordAuthentication
import javax.mail.Session

class MailboxRepoImpl: MailboxRepoInterface {

    /**
     * Main function that just separates errors from the expected result
     */
    override fun fetchAllReceipts(
        username: String,
        password: String
    ): Single<MutableList<Receipt>> {

        return Single.create { emitter ->
            try {
                val result = fetchDataFromServer(
                    username = username,
                    password = password
                )
                emitter.onSuccess(result)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }.subscribeOn(Schedulers.io())
    }

    /**
     * Mail IMAP connection method
     *
     * @param username User mail that looks something like this:
     * <some cool letters>@mail.ru
     * @param password User IMAP password that they received on the mail site
     */
    private fun fetchDataFromServer(
        username: String,
        password: String
    ): MutableList<Receipt> {
        val result = mutableListOf<Receipt>()

        val properties = Properties().apply {
            put(
                "mail.imap.host",
                "imap.mail.ru"
            )
            put("mail.imap.port", "993")
            put("mail.imap.ssl.enable", "true")
        }

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

        for (message in messages) {

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
        return result
    }
}