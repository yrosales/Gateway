package demo;

class DeviceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DeviceNotFoundException(Long uID) {
    super("Could not find device " + uID);
  }
}
