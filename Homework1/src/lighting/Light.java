package lighting;

import primitives.Color;

public abstract class Light {
	protected Color intensity;

	protected Light(Color intensity) {
		super();
		this.intensity = intensity;
	}

	public Color getIntensity() {
		return intensity;
	}
}
