package com.peratrack.data




class AuthAgent(
) {

//    fun getMessagesWithLists(userId: String): List<String> {
//        val query = "has:attachment"  // Запрос для писем с вложениями (можно менять по необходимости)
//
//        val request = gmail.users().messages().list(userId).setQ(query).execute()
//
//        val messages: MutableList<String> = mutableListOf()
//
//        request.messages?.forEach { msg ->
//            val message = gmail.users().messages().get(userId, msg.id).execute()
//            val messageContent = extractListsFromMessage(message)
//            messages.addAll(messageContent)
//        }
//
//        return messages
//    }
//
//    private fun extractListsFromMessage(message: Message): List<String> {
//        // Реализуйте логику по извлечению списков из сообщения
//        // Например, искать конкретные символы или формат текста
//        return listOf() // Возвращает список списков из писем
//    }
}