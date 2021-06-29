package demo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GatewayControler {
	
	private GatewayRepository gatewayRepository;

	public GatewayControler(GatewayRepository gatewayRepository) {
		this.gatewayRepository = gatewayRepository;
	}
	// Aggregate root
	// tag::get-aggregate-root[]
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/gateways")
	List<Gateway> all() {
		return gatewayRepository.findAll();
	}
	// end::get-aggregate-root[]
	
	@PostMapping(value="/gateways")
	Gateway newGateway(@RequestBody Gateway newGateway) {
		return gatewayRepository.save(newGateway);
	}

	// Single item
	@GetMapping("/gateways/{serial}")
	Gateway one(@PathVariable String serial) {
		return gatewayRepository.findById(serial)
	    .orElseThrow(() -> new GatewayNotFoundException(serial));
	}

	@PutMapping("/gateways/{serial}")
	Gateway replaceGateway(@RequestBody Gateway newGateway, @PathVariable String serial) { 
	  return gatewayRepository.findById(serial)
	    .map(gateway -> {
	    	gateway.setName(newGateway.getName());
	    	gateway.setAddress(newGateway.getAddress());
	      	return gatewayRepository.save(gateway);
	    })
	    .orElseGet(() -> {
	    	newGateway.setSerial(serial);
	      return gatewayRepository.save(newGateway);
    	});
	}

	@DeleteMapping("/gateways/{serial}")
	void deleteGateway(@PathVariable String serial) {
		gatewayRepository.deleteById(serial);
	}
	
	
}
