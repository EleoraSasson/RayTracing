package primitives;

import java.util.Objects;

public class Ray {
	private Point p0;
	private Vector dir;
	
	public Ray() {
		super();
		this.p0 = null;
		this.dir = null;
	}

	public Ray(Point p0, Vector dir) {
		super();
		this.p0 = p0;
		this.dir = dir.normalize();
	}

	public Point getP0() {
		return p0;
	}

		public Vector getDir() {
		return dir;
	}
	
	public Point getPoint(double t) {
		Point p= p0.add(dir.scale(t));
        return p;
	}

	@Override
	public String toString() {
		return "Ray [p0=" + p0 + ", dir=" + dir + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dir, p0);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		return Objects.equals(dir, other.dir) && Objects.equals(p0, other.p0);
	}
}
