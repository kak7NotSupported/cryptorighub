package me.dyk4lis;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ConfigDTO {

    @JsonProperty("config")
    private Config config;

    @JsonProperty("locale")
    private Locale locale;

    @Data
    public static class Config {
        @JsonProperty("token")
        private String token = "7209411504:AAGbtg9WJA_b49LKp99l0spLJW_f8rQQhNY";
    }


    @Data
    public static class Locale {
        @JsonProperty("welcome_text")
        private String welcomeText = "privet";

        @JsonProperty("get_price_button")
        private String priceButtonText = "Получить прайс";

        @JsonProperty("get_price_text")
        private String priceText = "Вы нажали кнопку получить прайс";

        @JsonProperty("make_an_order_button")
        private String makeAnOrderButtonText = "Сделать заказ";

        @JsonProperty("make_an_order_text")
        private String makeAnOrderText = "Для оформления заказа свяжитесь с одним из наших менеджеров:\n @AlertBigLove\n @ArtmaniQQ\n или позвоните по номеру: \n +7 988 553 05 19\n +7 988 897 69 27\n Сайт: https://cryptorighub.nicepage.io/";

        @JsonProperty("current_availability_button")
        private String currentAvailabilityButtonText = "Актуальное наличие";

        @JsonProperty("current_availability_text")
        private String currentAvailabilityText = "Присылает сообщение - список, которое будет редактироваться Славой";

        @JsonProperty("the_price_is_on_order_button")
        private String thePriceIsOnOrderButtonText = "Прайс: под заказ";

        @JsonProperty("the_price_is_on_order_text")
        private String thePriceIsOnOrderText = "текс Прайс: под заказ";

        @JsonProperty("pre_order_button")
        private String thePricePreOrderButtonText = "Прайс: предзаказ";


         @JsonProperty("pre_order_text")
        private String thePricePreOrderText = "текс Прайс: предзаказ";


        @JsonProperty("stock_availability_button")
        private String stockAvailabilityButtonText = "Прайс: в наличии на складе";

        @JsonProperty("menu_button")
        private String menuButtonText = "На главную";

        @JsonProperty("menu_button_text")
        private String menuText = "На главную";








    }
}
