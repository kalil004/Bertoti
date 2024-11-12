
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.time.LocalDate;

public class MyAmazingBot implements LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient telegramClient;

    public MyAmazingBot(String botToken) {
        telegramClient = new OkHttpTelegramClient(botToken);
    }

    @Override
    public void consume(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText() ;
            long chat_id = update.getMessage().getChatId();

            SendMessage message = null;
            if(message_text.equals("/fatec")) {
                message = SendMessage
                        .builder()
                        .chatId(chat_id)
                        .text("voce esta na fatec")
                        .build();
            }else {
                 message = SendMessage // Create a message object
                        .builder()
                        .chatId(chat_id)
                        .text(message_text)
                        .build();
            }
            try {
                System.out.println(message.toString());
                telegramClient.execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

        }
        }
}