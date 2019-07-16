package como.taco.Events;

public class EventPreMotion extends Event {
	public boolean cancel;
	public boolean onGround;
	public float rotationYaw;
	public float rotationPitch;

	public double posX;
	public double posY;
	public double posZ;

	public EventPreMotion(float rotationYaw, float rotationPitch, double posX, double posY, double posZ,
			boolean onGround) {
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.rotationYaw = rotationYaw;
		this.rotationPitch = rotationPitch;
		this.onGround = onGround;
	}

}
