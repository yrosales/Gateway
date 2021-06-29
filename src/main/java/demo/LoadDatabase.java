package demo;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(GatewayRepository gatewayRepository, DeviceRepository deviceRepository) {

    return args -> {
    	
    	//create gateway
    	Gateway gateway = new Gateway("3242","GatewayOne", "192.168.12.5");
    	//create device
    	Device device = new Device(222L, "VendorOne", new Date(), gateway);
	    log.info("Preloading " + gatewayRepository.save(gateway));
	    log.info("Preloading " + deviceRepository.save(device));
    };
  }
}
