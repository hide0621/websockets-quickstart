package org.acme.websockets;

import io.quarkus.websockets.next.OnClose;
import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.quarkus.websockets.next.WebSocketConnection;
import jakarta.inject.Inject;

@WebSocket(path = "/chat/{username}")
class ChatWebSocket {


    enum class MessageType {
        USER_JOINED, USER_LEFT, CHAT_MESSAGE
    }

    /**
     * dataクラスとは、データを保持するためのクラスを簡潔に記述するための機能です。
     *
     * 以下のような特徴があります。
     *
     * 1.自動生成されるメソッド: equals(), hashCode(), toString(), copy(), および componentN() メソッドが自動的に生成されます
     *
     * 2.簡潔な構文: クラスの定義が簡潔で、プロパティの宣言と初期化が一行で行えます
     *
     * DartのRecordと似ているけど、こっちはクラス
     */
    data class ChatMessage(
        val type:MessageType,
        val from:String,
        val message:String?
    )

    @Inject
    lateinit var connection: WebSocketConnection

    @OnOpen(broadcast = true)
    fun onOpen():ChatMessage {
        return ChatMessage(MessageType.USER_JOINED, connection.pathParam("username"), null)
    }

    @OnClose
    fun onClose() {
        val departure = ChatMessage(MessageType.USER_LEFT, connection.pathParam("username"), null)

        /**
         * sendTextAndAwait メソッドは、テキストメッセージを送信し、その送信が完了するまで待機するためのメソッドです
         *
         * Kotlinのデータクラスは自動的に toString メソッドを生成するので、
         * sendTextAndAwaitメソッドの引数にdepartureをそのまま渡すことができます。
         */
        connection.broadcast().sendTextAndAwait(departure)
    }

    @OnTextMessage(broadcast = true)
    fun onMessage(message: ChatMessage):ChatMessage {
        return message
    }
}