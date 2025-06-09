package com.chernenkiy.PanDevTreeCategoryBot.t.bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class HelpCommand implements BotCommand {

    private static final Logger logger = LoggerFactory.getLogger(HelpCommand.class);

    /**
     * Команда для отображения списка доступных команд.
     */
    @Override
    public void execute(DefaultAbsSender sender, Update update) {
        String helpMessage = """
                Доступные команды:
                /viewTree - Показать дерево категорий
                /addElement <название элемента> - Добавить элемент
                /addRootElement <название> - Добавить корневой элемент
                /addChildElement <родитель> <название> - Добавить дочерний элемент
                /removeElement <название> - Удалить элемент
                /removeRootElement <название> - Удалить корневой элемент
                /removeChildElement <родитель> <название> - Удалить дочерний элемент
                /download - Скачать дерево категорий в Excel
                /upload - Загрузить дерево категорий из Excel
                /help - Показать эту справку""";

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText(helpMessage);

        try {
            sender.execute(message);
        } catch (TelegramApiException e) {
            logger.error("Ошибка при отправке сообщения справки", e);
        }
    }
}
