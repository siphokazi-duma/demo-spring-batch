package za.co.duma.demo_spring_batch.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class CoffeeItemProcessor implements ItemProcessor<Coffee,Coffee> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoffeeItemProcessor.class);

    @Override
    public Coffee process(final Coffee coffee) throws Exception {
        String brand = coffee.getBrand().toUpperCase();
        String origin = coffee.getOrigin().toUpperCase();
        String characteristics = coffee.getCharacteristics().toUpperCase();

        Coffee transformedCoffee = new Coffee(brand, origin, characteristics);
        LOGGER.info("Converting ( {} ) into ( {} )",coffee,transformedCoffee);
        return transformedCoffee;
    }
}
