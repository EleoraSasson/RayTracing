package renderer;
import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import static java.awt.Color.BLACK;
import static org.junit.jupiter.api.Assertions.*;

class miniProject1Test {
	private Scene scene = new Scene("Test scene");

	@Test
	void ourImageTest() {
		Camera camera = new Camera(new Point(0, -10, 300), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(300,300).setVPDistance(525);
		
		
		scene.setBackground(new Color(BLACK));
		//scene.setAmbientLight(new AmbientLight(new Color(25,42,88), 0.25));


		scene.geometries.add( //
				new Plane(new Point(0,-60,-100), new Vector(0,-1,0))
				.setEmission(new Color(50,50,50)).setMaterial(new Material(0.2, 0.1, 0.5, 0.5, 0)),
				new Sphere(new Point(0,22,0),20).setEmission(new Color(74, 4, 4))
				.setMaterial(new Material(0.2,0.2,0.8,0.5,100)),
				new Sphere(new Point(0,-22,0),20).setEmission(new Color(53, 94, 59))
				.setMaterial(new Material(0.2,0.2,0.5,0.5,100))
				);
		
		scene.lights.add( //
				new SpotLight(new Color(219, 0, 115), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKl(0.0004).setKq(0.0000006)
						);
		scene.lights.add(new PointLight(new Color(96, 80, 220),new Point(100,100,0)).setKl(0.00001).setKq(0.000002));

		
		 // scene.lights.add(new SpotLight(new Color(219, 0, 115), new Point(-1.38, 0.71, 1.34), new Vector(0.46, -0.46, -0.62)) //
	       //         .setKl(4E-5).setKq(2E-7));
	        //scene.lights.add(new PointLight(new Color(96, 80, 220), new Point(100, 100, 0))
	          //      .setKl(0.00001).setKq(0.000001));
	        scene.lights.add(new DirectionalLight(new Color(75, 0, 130), new Vector(0, 0, -1)));
	        //scene.lights.add(new SpotLight(new Color(0, 0, 400), new Point(0,50,0), new Vector(-0.18, 0.17, -0.33)) //
	          //      .setKl(4E-5).setKq(2E-7));
	        
		camera.setImageWriter(new ImageWriter("beforeSuperSampling", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();

	}

	@Test
	void ourImprovedImageTest() {
		Camera camera = new Camera(new Point(0, -10, 300), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(300,300).setVPDistance(525);
		
		
		scene.setBackground(new Color(BLACK));
		//scene.setAmbientLight(new AmbientLight(new Color(25,42,88), 0.25));


		scene.geometries.add( //
				new Plane(new Point(0,-60,-100), new Vector(0,-1,0))
				.setEmission(new Color(50,50,50)).setMaterial(new Material(0.2, 0.1, 0.5, 0.5, 0)),
				new Sphere(new Point(0,22,0),20).setEmission(new Color(74, 4, 4))
				.setMaterial(new Material(0.2,0.2,0.8,0.5,100)),
				new Sphere(new Point(0,-22,0),20).setEmission(new Color(53, 94, 59))
				.setMaterial(new Material(0.2,0.2,0.5,0.5,100))
				);
		
		scene.lights.add( //
				new SpotLight(new Color(219, 0, 115), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKl(0.0004).setKq(0.0000006)
						);
		scene.lights.add(new PointLight(new Color(96, 80, 220),new Point(100,100,0)).setKl(0.00001).setKq(0.000002));

		
	        scene.lights.add(new DirectionalLight(new Color(75, 0, 130), new Vector(0, 0, -1)));
	        	        
	        
		camera.setImageWriter(new ImageWriter("withSuperSampling", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene).setSampleCount) //
				.setMultithreading(3).setDebugPrint();
		        .setLevel_adaptive_supersampling(10);
				.renderImage() //
				.writeToImage();

	}

}
