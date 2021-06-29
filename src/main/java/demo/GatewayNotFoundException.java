package demo;

class GatewayNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GatewayNotFoundException(String serial) {
    super("Could not find gateway " + serial);
  }
}
