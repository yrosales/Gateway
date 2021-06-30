package demo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DeviceControler {
	
	private DeviceRepository deviceRepository;

	public DeviceControler(DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}
	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/devices")
	List<Device> all() {
		return deviceRepository.findAll();
	}
	// end::get-aggregate-root[]
	
	@PostMapping("/devices")
	Device newDevice(@RequestBody Device newDevice) {
		return deviceRepository.save(newDevice);
	}

	// Single item
	@GetMapping("/devices/{uID}")
	Device one(@PathVariable Long uID) {
		return deviceRepository.findById(uID)
	    .orElseThrow(() -> new DeviceNotFoundException(uID));
	}

	@PutMapping("/devices/{uID}")
	Device replaceDevice(@RequestBody Device newDevice, @PathVariable Long uID) { 
	  return deviceRepository.findById(uID)
	    .map(device -> {
	    	device.setVendor(newDevice.getVendor());
	    	device.setCreated(newDevice.getCreated());
	      	return deviceRepository.save(device);
	    })
	    .orElseGet(() -> {
	    	newDevice.setUID(uID);
	      return deviceRepository.save(newDevice);
    	});
	}

	@DeleteMapping("/devices/{uID}")
	void deleteDevice(@PathVariable Long uID) {
		deviceRepository.deleteById(uID);
	}
	
	
}
