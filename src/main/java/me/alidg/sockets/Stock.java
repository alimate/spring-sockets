package me.alidg.sockets;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

/**
 * A dump immutable data structure representing the stock information.
 *
 * @author Ali Dehghani
 */
@JsonAutoDetect(fieldVisibility = ANY)
class Stock {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String EMPTY_JSON = "{}";

    /**
     * Bunch of static stock symbols to get the job done!
     */
    private static final List<String> SYMBOLS = Arrays.asList("GOOG", "MSFT", "IBM", "NFLX");

    /**
     * The symbol of the stock, e.g. GOOG for Google.
     */
    private final String symbol;

    /**
     * Current valuation of the stock.
     */
    private final double value;

    private Stock(String symbol, double value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String symbol() {
        return symbol;
    }

    public double value() {
        return value;
    }

    /**
     * Return a JSON representation of the stock object
     *
     * @return The json
     */
    String toJson() {
        try {
            return MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return EMPTY_JSON;
        }
    }

    /**
     * A static factory method for creating a random {@linkplain Stock} instance
     *
     * @return A random stock instance
     */
    static Stock randomStockUpdate() {
        Random random = new Random();
        String symbol = SYMBOLS.get(random.nextInt(SYMBOLS.size()));
        double value = random.nextDouble();

        return new Stock(symbol, value);
    }
}