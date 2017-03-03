/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.telegram.telegrambots.ApiContextInitializer;

import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 *
 * @author Muzaffar
 */
public class SimpleBot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new SimpleBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "MuzaffarsBot";
    }

    @Override
    public String getBotToken() {
        return "379361799:AAE7-v-FCwSeFBJcu1_zH-hNpGTKEd6c6xw";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            System.out.println(">>>>>>> "+message.getText());
            if (message.getText().equals("/help")) {
                sendMsg(message, "Привет, я робот");
            } else if (message.getText().equals("/tashkent")) {
                ReadFromWeb rfw = new ReadFromWeb();
//                rfw.find();
                sendMsg(message, rfw.find("Ташкент"));
            } else if (message.getText().equals("/samarkand")) {
                ReadFromWeb rfw = new ReadFromWeb();
//                rfw.find();
                sendMsg(message, rfw.find("Самарканд"));
            } else if (message.getText().equals("/bukhara")) {
                ReadFromWeb rfw = new ReadFromWeb();
//                rfw.find();
                sendMsg(message, rfw.find("Бухара"));
            } else if (message.getText().equals("/karshi")) {
                ReadFromWeb rfw = new ReadFromWeb();
//                rfw.find();
                sendMsg(message, rfw.find("Карши"));
            } else {
                sendMsg(message, "Я не знаю что ответить на это");
            }
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
