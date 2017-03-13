/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Scanner;
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
            System.out.println("TelegramApiException ---> " + e);
            e.printStackTrace();
        }
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Введите команду!");
//
////        System.out.println(">>>>>>> " + message.getText());
//        String stFrom;
//        String stTo;
//        String message = sc.nextLine();
//        String[] s = message.split(" ");
//        stFrom = s[1];
//        stTo = s[2];
//
//        if (message.equals("/help")) {
////                sendMsg(message, "Пример команды /reys станция отправления станция назначения");
//            System.out.println("Пример команды /reys станция отправления станция назначения");
//        } else if (message.equals("/reys")) {
////                sendMsg(message, "пример команды /reys Ташкент Москва");
//            System.out.println("пример команды /reys Ташкент Москва");
//        } else if (message.equals("/reys " + stFrom + " " + stTo)) {
//            RFW rfw = new RFW();
//            rfw.find(stFrom, stTo);
////                sendMsg(message, rfw.find(stFrom, stTo));
//        } else {
//            System.out.println("Я не знаю что ответить на это");
////                sendMsg(message, "Я не знаю что ответить на это");
//        }

    }

    @Override
    public String getBotUsername() {
        return "MuzaffarsBot";
    }

    @Override
    public String getBotToken() {
        return "379361799:AAE7-v-FCwSeFBJcu1_zH-hNpGTKEd6c6xw";
    }

    String stFrom;
    String stTo;

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            System.out.println(">>>>>>> " + message.getText());

            String[] s = message.getText().split(" ");
            stFrom = s[1];
            stTo = s[2];

            if (message.getText().equals("/help")) {
                sendMsg(message, "Пример команды /reys станция отправления станция назначения");
//            } else if (message.getText().equals("/tashkent")) {
//                ReadFromWeb rfw = new ReadFromWeb();
////                rfw.find();
//                sendMsg(message, rfw.find("Ташкент"));
//            } else if (message.getText().equals("/samarkand")) {
//                ReadFromWeb rfw = new ReadFromWeb();
////                rfw.find();
//                sendMsg(message, rfw.find("Самарканд"));
//            } else if (message.getText().equals("/bukhara")) {
//                ReadFromWeb rfw = new ReadFromWeb();
////                rfw.find();
//                sendMsg(message, rfw.find("Бухара"));
//            } else if (message.getText().equals("/karshi")) {
//                ReadFromWeb rfw = new ReadFromWeb();
////                rfw.find();
//                sendMsg(message, rfw.find("Карши"));
            } else if (message.getText().equals("/reys")) {
                sendMsg(message, "пример команды /reys Ташкент Москва");
            } else if (message.getText().equals("/reys " + stFrom + " " + stTo)) {
                RFW rfw = new RFW();
                sendMsg(message, rfw.find(stFrom, stTo));
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
            System.out.println("TelegramApiException sendMess ---> " + e);
            e.printStackTrace();
        }
    }
}
