package renderer;
import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.Camera.SUPERSAMPLING_TYPE;
import scene.Scene;


import static java.awt.Color.BLACK;


class Titanic {
	
	private Scene scene = new Scene("Test scene");


//	@Test
//	void draft() {
//		Camera camera = new Camera(new Point(0, 10, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
//				.setVPSize(300,300).setVPDistance(400);
//		
//		
//		scene.setBackground(new Color(BLACK));
//		
//		//hallway
//		scene.geometries.add( 
//				//floor
//				new Polygon(new Point(-7,0,10), new Point(-7,0,50),new Point(7,0,50), new Point(7,0,10))
//				.setEmission(new Color(5,0,54)).setMaterial(new Material(0.5,0.3,0.2,0,60)), 
//				//left wall
//				new Polygon(new Point(-7,0,10), new Point(-7,0,50), new Point(-7,30,50), new Point(-7,30,10))
//				.setEmission(new Color(5,0,54)).setMaterial(new Material(0.5,0.5,0,0,60)),
//				//right wall
//				new Polygon(new Point(7,0,10), new Point(7,0,50),new Point(7,30,50), new Point(7,30,10))
//				.setEmission(new Color(5,0,54)).setMaterial(new Material(0.5,0.5,0,0,60)),
//				//door
//				new Polygon(new Point(-3,0,50), new Point(3,0,50), new Point(3,15,50), new Point(-3,15,50))
//				.setEmission(new Color(0 ,170,170)).setMaterial(new Material(0.5,0.9,0.6,0.8,300)),
//				//door frame
//				new Polygon(new Point(-3,0,50), new Point(-4,0,50),new Point(-4,16,50),new Point(-3,16,50))
//				.setEmission(new Color(30,30,30)).setMaterial(new Material(0.5,0.5,0,0,100)),
//				new Polygon(new Point(3,0,50), new Point(4,0,50),new Point(4,16,50),new Point(3,16,50))
//				.setEmission(new Color(30,30,30)).setMaterial(new Material(0.5,0.5,0,0,100)),
//				new Polygon(new Point(3,16,50), new Point(-3,16,50),new Point(-3,15,50),new Point(3,15,50))
//				.setEmission(new Color(30,30,30)).setMaterial(new Material(0.5,0.5,0,0,100))
//				
//				);
//		
//		//tile floor
//		double carrelage=0.2;
//		int cnt=0;
//		double width=1.6, length=5;		
//		double skip=width+carrelage;
//			
//			for(double j=-7;j<7.3;j+=length+carrelage) {
//				scene.geometries.add(
//						new Polygon(new Point(j,0.2,50),new Point(j+length,0.2,50),new Point(j+length,0.2,50-width),new Point(j,0.2,50-width))
//						.setEmission(new Color(100,100,100)).setMaterial(new Material(0.5,0.5,0,0,60))
//						);
//				
//			}
//		
//			for(double j=-10;j<8;j+=length+carrelage) {
//				scene.geometries.add(
//						new Polygon(new Point(j,0.2,50-skip),new Point(j+length,0.2,50-skip),new Point(j+length,0.2,50-width-skip),new Point(j,0.2,50-width-skip))
//						.setEmission(new Color(100,100,100)).setMaterial(new Material(0.5,0.5,0,0,60))
//						);
//				
//			}
//			for(double j=-7;j<7.3;j+=length+carrelage) {
//				scene.geometries.add(
//						new Polygon(new Point(j,0.2,50-skip*2),new Point(j+length,0.2,50-skip*2),new Point(j+length,0.2,50-width-skip*2),new Point(j,0.2,50-width-skip*2))
//						.setEmission(new Color(100,100,100)).setMaterial(new Material(0.5,0.5,0,0,60))
//						);
//				
//			}
//			
//			for(double j=-10;j<8;j+=length+carrelage) {
//				scene.geometries.add(
//						new Polygon(new Point(j,0.2,50-skip*3-0.2),new Point(j+length,0.2,50-skip*3-0.2),new Point(j+length,0.2,50-width-0.2-skip*3),new Point(j,0.2,50-0.2-width-skip*3))
//						.setEmission(new Color(100,100,100)).setMaterial(new Material(0.5,0.5,0,0,60))
//						);
//				
//			}
//			for(double j=-7;j<7.3;j+=length+carrelage) {
//				scene.geometries.add(
//						new Polygon(new Point(j,0.2,50-skip*4-0.2*2),new Point(j+length,0.2,50-skip*4-0.2*2),new Point(j+length,0.2,50-width-skip*4-0.2*2),new Point(j,0.2,50-width-skip*4-0.2*2))
//						.setEmission(new Color(100,100,100)).setMaterial(new Material(0.5,0.5,0,0,60))
//						);
//				
//			}
//			
//			for(double j=-10;j<8;j+=length+carrelage, cnt++) {
//				if(cnt==0 || cnt==3) {}
//				else {
//				scene.geometries.add(
//						new Polygon(new Point(j,0.2,50-skip*5-0.2*3),new Point(j+length,0.2,50-skip*5-0.2*3),new Point(j+length,0.2,50-width-0.2*3-skip*5),new Point(j,0.2,50-0.2*3-width-skip*5))
//						.setEmission(new Color(100,100,100)).setMaterial(new Material(0.5,0.5,0,0,60))
//						);
//				}
//				
//			}
//					
//		//shelves
//		double rad = 0.5;
//
//		for(int i = 4;i<30;i+=4) {
//			scene.geometries.add(
//					//shelf left
//					new Polygon(new Point(-7,i,10), new Point(-7,i,50),new Point(-4,i,50), new Point(-4,i,10))
//					.setEmission(new Color(BLACK)).setMaterial(new Material(0.5,0.5,0,0,60)),
//					//shelf right
//					new Polygon(new Point(7,i,10), new Point(7,i,50),new Point(4,i,50), new Point(4,i,10))
//					.setEmission(new Color(BLACK)).setMaterial(new Material(0.5,0.5,0,0,60)) //floor
//
//					);
//			
//			if(i<15)
//			//orbs
//			for(double posZ = 49; posZ>10; posZ-=(7+rad)) {
//				scene.geometries.add(
//						//left
//						new Sphere(new Point(-5.5,i+rad,posZ),rad)
//						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.2,0.2,0.8,0.4,100)),
//						new Sphere(new Point(-5.5,i+rad,posZ),0.2).
//						setEmission(new Color(0,128,128)).setMaterial(new Material(0.5,0.3,0.1,0.7,10)),
//						//left
//						new Sphere(new Point(5.5,i+rad,posZ),rad)
//						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.2,0.2,0.8,0.4,100)),
//						new Sphere(new Point(5.5,i+rad,posZ),0.2).
//						setEmission(new Color(0,128,128)).setMaterial(new Material(0.5,0.3,0.1,0.7,10))
//						
//						);
//				}
//			
//		}
//		
//		//deatheater
//				scene.geometries.add(
//						//body
//						new Polygon(new Point(-1.5,0,40), new Point(1.5,0,40), new Point(1.5,10,40), new Point(-1.5,10,40))
//						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
//						//hood
//						new Triangle(new Point(-1.5,10,40), new Point(1.5,10,40), new Point(0,13,40))
//						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
//						//skull face
//						new Sphere(new Point(0,10,40),1)
//						.setEmission(new Color(150,150,150)).setMaterial(new Material(0.5,0.5,0.2,0,60)),
//						//skull teeth
//						new Polygon(new Point(-0.5,9.7,39.9), new Point(0.5,9.7,39.9), new Point(0.5,8.5,39.9), new Point(-0.5,8.5,39.9))
//						.setEmission(new Color(150,150,150)).setMaterial(new Material(0.5,0.5,0.2,0,60)),
//						//left orbit
//						new Sphere(new Point(-0.4,10,39),0.3).setEmission(new Color(20,20,20)),
//						//right orbit
//						new Sphere(new Point(0.4,10,39),0.3).setEmission(new Color(20,20,20)),
//						//left evil red glow
//						new Sphere(new Point(0.4,10,38.4),0.15)
//						.setEmission(new Color(74, 4, 4)).setMaterial(new Material(0.5,0.6,0.5,0.7,100)),
//						//right evil red glow
//						new Sphere(new Point(-0.4,10,38.4),0.15)
//						.setEmission(new Color(74, 4, 4)).setMaterial(new Material(0.5,0.6,0.5,0.7,100)),
//						//arm
//						new Triangle(new Point(-1.5,10,40), new Point(-1.5,5,40), new Point(-2.3,5,40))
//						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
//						new Sphere(new Point(-1.9,5,40),0.4)
//						.setEmission(new Color(150,150,150)).setMaterial(new Material(0.5,0.5,0.2,0,60)),
//						//stretched arm
//						new Polygon(new Point(1,9,39.8), new Point(1.8,8.5,39.8), new Point(1,6.5,39.8), new Point(0.2,8.5,39.8))
//						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
//						new Polygon(new Point(1,9,35), new Point(1.8,8.5,35),new Point(1.8,8.5,39.8),new Point(1,9,39.8))
//						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
//						new Polygon(new Point(1.8,8.5,35), new Point(1,6.5,35),new Point(1,6.5,39.8),new Point(1.8,8.5,39.8))
//						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
//						new Polygon(new Point(1,6.5,35), new Point(0.2,8.5,35), new Point(0.2,8.5,39.8),new Point(1,6.5,39.8))
//						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
//						new Polygon(new Point(1,9,35),new Point(0.2,8.5,35),new Point(0.2,8.5,39.8),new Point(1,9,39.8))
//						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
//						new Sphere(new Point(1,8.5,34.9),0.4)
//						.setEmission(new Color(150,150,150)).setMaterial(new Material(0.5,0.5,0.2,0,60)),
//						//wand
//						new Polygon(new Point(1,8.5,34.8), new Point(1.2,8.5,34.8), new Point(1.2,8.5,31), new Point(1,8.5,31))
//						.setEmission(new Color(100,20,0)).setMaterial(new Material(0.5,0.5,0,0,60)),
//						//spell
//						new Sphere(new Point(1,8.4,30),1.2)
//						.setEmission(new Color(74, 4, 4)).setMaterial(new Material(0.2,0.2,0.5,0.9,50)),
//						new Sphere(new Point(1,8.4,30),0.2).
//						setEmission(new Color(150,60,60)).setMaterial(new Material(0.5,0.3,0.1,0.4,100))
//						
//						);
//				
//		
//		//orbs light
//		scene.lights.add(new PointLight(new Color(0,128,128),new Point(-5.5,4+rad,49)).setKl(0.1).setKq(0.02));
//		scene.lights.add(new PointLight(new Color(0,128,128),new Point(-5.5,4+rad,41.5)).setKl(0.1).setKq(0.02));
//		scene.lights.add(new PointLight(new Color(0,128,128),new Point(-5.5,4+rad,34)).setKl(0.01).setKq(0.02));
//		scene.lights.add(new PointLight(new Color(0,128,128),new Point(-5.5,4+rad,26.5)).setKl(0.1).setKq(0.02));
//		scene.lights.add(new PointLight(new Color(0,128,128),new Point(-5.5,4+rad,19)).setKl(0.1).setKq(0.02));
//		
//		scene.lights.add(new PointLight(new Color(0,128,128),new Point(5.5,4+rad,49)).setKl(0.1).setKq(0.02));
//		scene.lights.add(new PointLight(new Color(0,128,128),new Point(5.5,4+rad,41.5)).setKl(0.1).setKq(0.02));
//		scene.lights.add(new PointLight(new Color(0,128,128),new Point(5.5,4+rad,34)).setKl(0.1).setKq(0.02));
//		scene.lights.add(new PointLight(new Color(0,128,128),new Point(5.5,4+rad,26.5)).setKl(0.1).setKq(0.02));
//		scene.lights.add(new PointLight(new Color(0,128,128),new Point(5.5,4+rad,19)).setKl(0.1).setKq(0.02));
//		
//		//spell light
//		scene.lights.add(new PointLight(new Color(RED),new Point(1,8,35)).setKl(0.0015).setKq(0.00025));
//
//		//mysterious door light
//		scene.lights.add(new DirectionalLight(new Color(0,55,55), new Vector(0,-1,0)));
//		
//		//room light
//		scene.lights.add(new SpotLight(new Color(0,255,255), new Point(0, 7.5, 51), new Vector(0, 0, -1)) //
//	                .setKl(4E-5).setKq(2E-7));
//		
//		
//		camera.setImageWriter(new ImageWriter("Department of Mysteries", 500, 500)) //
//		.setRayTracer(new RayTracerBasic(scene)) //
//		.setSupersamplingType(SUPERSAMPLING_TYPE.NONE) //
//		.renderImage() //
//		.writeToImage();
//	}
	
	@Test
	void clean() {
		Camera camera = new Camera(new Point(0, 10, 0), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
				.setVPSize(300,300).setVPDistance(400);
		
		
		scene.setBackground(new Color(BLACK));
		
		//hallway
		scene.geometries.add( 
				//floor
				new Polygon(new Point(-7,0,10), new Point(-7,0,50),new Point(7,0,50), new Point(7,0,10))
				.setEmission(new Color(5,0,54)).setMaterial(new Material(0.5,0.3,0.2,0,60)), 
				//left wall
				new Polygon(new Point(-7,0,10), new Point(-7,0,50), new Point(-7,30,50), new Point(-7,30,10))
				.setEmission(new Color(5,0,54)).setMaterial(new Material(0.5,0.5,0,0,60)),
				//right wall
				new Polygon(new Point(7,0,10), new Point(7,0,50),new Point(7,30,50), new Point(7,30,10))
				.setEmission(new Color(5,0,54)).setMaterial(new Material(0.5,0.5,0,0,60)),
				//door
				new Polygon(new Point(-3,0,50), new Point(3,0,50), new Point(3,15,50), new Point(-3,15,50))
				.setEmission(new Color(0 ,170,170)).setMaterial(new Material(0.5,0.9,0.6,0.8,300)),
				//door frame
				new Polygon(new Point(-3,0,50), new Point(-4,0,50),new Point(-4,16,50),new Point(-3,16,50))
				.setEmission(new Color(30,30,30)).setMaterial(new Material(0.5,0.5,0,0,100)),
				new Polygon(new Point(3,0,50), new Point(4,0,50),new Point(4,16,50),new Point(3,16,50))
				.setEmission(new Color(30,30,30)).setMaterial(new Material(0.5,0.5,0,0,100)),
				new Polygon(new Point(3,16,50), new Point(-3,16,50),new Point(-3,15,50),new Point(3,15,50))
				.setEmission(new Color(30,30,30)).setMaterial(new Material(0.5,0.5,0,0,100))
				
				);
		
		//tile floor
		double carrelage=0.2;
		int cnt=0;
		double width=1.6, length=5;		
		double skip=width+carrelage;
			
			for(double j=-7;j<7.3;j+=length+carrelage) {
				scene.geometries.add(
						new Polygon(new Point(j,0.2,50),new Point(j+length,0.2,50),new Point(j+length,0.2,50-width),new Point(j,0.2,50-width))
						.setEmission(new Color(100,100,100)).setMaterial(new Material(0.5,0.5,0,0,60))
						);
				
			}
		
			for(double j=-10;j<8;j+=length+carrelage) {
				scene.geometries.add(
						new Polygon(new Point(j,0.2,50-skip),new Point(j+length,0.2,50-skip),new Point(j+length,0.2,50-width-skip),new Point(j,0.2,50-width-skip))
						.setEmission(new Color(100,100,100)).setMaterial(new Material(0.5,0.5,0,0,60))
						);
				
			}
			for(double j=-7;j<7.3;j+=length+carrelage) {
				scene.geometries.add(
						new Polygon(new Point(j,0.2,50-skip*2),new Point(j+length,0.2,50-skip*2),new Point(j+length,0.2,50-width-skip*2),new Point(j,0.2,50-width-skip*2))
						.setEmission(new Color(100,100,100)).setMaterial(new Material(0.5,0.5,0,0,60))
						);
				
			}
			
			for(double j=-10;j<8;j+=length+carrelage) {
				scene.geometries.add(
						new Polygon(new Point(j,0.2,50-skip*3-0.2),new Point(j+length,0.2,50-skip*3-0.2),new Point(j+length,0.2,50-width-0.2-skip*3),new Point(j,0.2,50-0.2-width-skip*3))
						.setEmission(new Color(100,100,100)).setMaterial(new Material(0.5,0.5,0,0,60))
						);
				
			}
			for(double j=-7;j<7.3;j+=length+carrelage) {
				scene.geometries.add(
						new Polygon(new Point(j,0.2,50-skip*4-0.2*2),new Point(j+length,0.2,50-skip*4-0.2*2),new Point(j+length,0.2,50-width-skip*4-0.2*2),new Point(j,0.2,50-width-skip*4-0.2*2))
						.setEmission(new Color(100,100,100)).setMaterial(new Material(0.5,0.5,0,0,60))
						);
				
			}
			
			for(double j=-10;j<8;j+=length+carrelage, cnt++) {
				if(cnt==0 || cnt==3) {}
				else {
				scene.geometries.add(
						new Polygon(new Point(j,0.2,50-skip*5-0.2*3),new Point(j+length,0.2,50-skip*5-0.2*3),new Point(j+length,0.2,50-width-0.2*3-skip*5),new Point(j,0.2,50-0.2*3-width-skip*5))
						.setEmission(new Color(100,100,100)).setMaterial(new Material(0.5,0.5,0,0,60))
						);
				}
				
			}
					
		//shelves
		double rad = 0.5;

		for(int i = 4;i<30;i+=4) {
			scene.geometries.add(
					//shelf left
					new Polygon(new Point(-7,i,10), new Point(-7,i,50),new Point(-4,i,50), new Point(-4,i,10))
					.setEmission(new Color(BLACK)).setMaterial(new Material(0.5,0.5,0,0,60)),
					//shelf right
					new Polygon(new Point(7,i,10), new Point(7,i,50),new Point(4,i,50), new Point(4,i,10))
					.setEmission(new Color(BLACK)).setMaterial(new Material(0.5,0.5,0,0,60)) //floor

					);
			
			if(i<15)
			//orbs
			for(double posZ = 49; posZ>10; posZ-=(7+rad)) {
				scene.geometries.add(
						//left
						new Sphere(new Point(-5.5,i+rad,posZ),rad)
						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.2,0.2,0.8,0.4,100)),
						new Sphere(new Point(-5.5,i+rad,posZ),0.2).
						setEmission(new Color(0,128,128)).setMaterial(new Material(0.5,0.3,0.1,0.7,10)),
						//left
						new Sphere(new Point(5.5,i+rad,posZ),rad)
						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.2,0.2,0.8,0.4,100)),
						new Sphere(new Point(5.5,i+rad,posZ),0.2).
						setEmission(new Color(0,128,128)).setMaterial(new Material(0.5,0.3,0.1,0.7,10))
						
						);
				}
			
		}
		
		//deatheater
				scene.geometries.add(
						//body
						new Polygon(new Point(-1.5,0,40), new Point(1.5,0,40), new Point(1.5,10,40), new Point(-1.5,10,40))
						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
						//hood
						new Triangle(new Point(-1.5,10,40), new Point(1.5,10,40), new Point(0,13,40))
						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
						//skull face
						new Sphere(new Point(0,10,40),1)
						.setEmission(new Color(150,150,150)).setMaterial(new Material(0.5,0.5,0.2,0,60)),
						//skull teeth
						new Polygon(new Point(-0.5,9.7,39.9), new Point(0.5,9.7,39.9), new Point(0.5,8.5,39.9), new Point(-0.5,8.5,39.9))
						.setEmission(new Color(150,150,150)).setMaterial(new Material(0.5,0.5,0.2,0,60)),
						//left orbit
						new Sphere(new Point(-0.4,10,39),0.3).setEmission(new Color(20,20,20)),
						//right orbit
						new Sphere(new Point(0.4,10,39),0.3).setEmission(new Color(20,20,20)),
						//left evil red glow
						new Sphere(new Point(0.4,10,38.4),0.15)
						.setEmission(new Color(74, 4, 4)).setMaterial(new Material(0.5,0.6,0.5,0.7,100)),
						//right evil red glow
						new Sphere(new Point(-0.4,10,38.4),0.15)
						.setEmission(new Color(74, 4, 4)).setMaterial(new Material(0.5,0.6,0.5,0.7,100)),
						//arm
						new Triangle(new Point(-1.5,10,40), new Point(-1.5,5,40), new Point(-2.3,5,40))
						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
						new Sphere(new Point(-1.9,5,40),0.4)
						.setEmission(new Color(150,150,150)).setMaterial(new Material(0.5,0.5,0.2,0,60)),
						//stretched arm
						new Polygon(new Point(1,9,39.8), new Point(1.8,8.5,39.8), new Point(1,6.5,39.8), new Point(0.2,8.5,39.8))
						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
						new Polygon(new Point(1,9,35), new Point(1.8,8.5,35),new Point(1.8,8.5,39.8),new Point(1,9,39.8))
						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
						new Polygon(new Point(1.8,8.5,35), new Point(1,6.5,35),new Point(1,6.5,39.8),new Point(1.8,8.5,39.8))
						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
						new Polygon(new Point(1,6.5,35), new Point(0.2,8.5,35), new Point(0.2,8.5,39.8),new Point(1,6.5,39.8))
						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
						new Polygon(new Point(1,9,35),new Point(0.2,8.5,35),new Point(0.2,8.5,39.8),new Point(1,9,39.8))
						.setEmission(new Color(20,20,20)).setMaterial(new Material(0.5,0.5,0,0,60)),
						new Sphere(new Point(1,8.5,34.9),0.4)
						.setEmission(new Color(150,150,150)).setMaterial(new Material(0.5,0.5,0.2,0,60)),
						//wand
						new Polygon(new Point(1,8.5,34.8), new Point(1.2,8.5,34.8), new Point(1.2,8.5,31), new Point(1,8.5,31))
						.setEmission(new Color(100,20,0)).setMaterial(new Material(0.5,0.5,0,0,60)),
						//spell
						new Sphere(new Point(1,8.4,30),1.2)
						.setEmission(new Color(74, 4, 4)).setMaterial(new Material(0.2,0.2,0.5,0.9,50)),
						new Sphere(new Point(1,8.4,30),0.2).
						setEmission(new Color(150,60,60)).setMaterial(new Material(0.5,0.3,0.1,0.4,100))
						
						);
				
		
		//orbs light
		scene.lights.add(new PointLight(new Color(0,128,128),new Point(-5.5,4+rad,49)).setKl(0.1).setKq(0.02));
		scene.lights.add(new PointLight(new Color(0,128,128),new Point(-5.5,4+rad,41.5)).setKl(0.1).setKq(0.02));
		scene.lights.add(new PointLight(new Color(0,128,128),new Point(-5.5,4+rad,34)).setKl(0.01).setKq(0.02));
		scene.lights.add(new PointLight(new Color(0,128,128),new Point(-5.5,4+rad,26.5)).setKl(0.1).setKq(0.02));
		scene.lights.add(new PointLight(new Color(0,128,128),new Point(-5.5,4+rad,19)).setKl(0.1).setKq(0.02));
		
		scene.lights.add(new PointLight(new Color(0,128,128),new Point(5.5,4+rad,49)).setKl(0.1).setKq(0.02));
		scene.lights.add(new PointLight(new Color(0,128,128),new Point(5.5,4+rad,41.5)).setKl(0.1).setKq(0.02));
		scene.lights.add(new PointLight(new Color(0,128,128),new Point(5.5,4+rad,34)).setKl(0.1).setKq(0.02));
		scene.lights.add(new PointLight(new Color(0,128,128),new Point(5.5,4+rad,26.5)).setKl(0.1).setKq(0.02));
		scene.lights.add(new PointLight(new Color(0,128,128),new Point(5.5,4+rad,19)).setKl(0.1).setKq(0.02));
		
		//spell light
		scene.lights.add(new PointLight(new Color(RED),new Point(1,8,35)).setKl(0.0015).setKq(0.00025));

		//mysterious door light
		scene.lights.add(new DirectionalLight(new Color(0,55,55), new Vector(0,-1,0)));
		
		//room light
		scene.lights.add(new SpotLight(new Color(0,255,255), new Point(0, 7.5, 51), new Vector(0, 0, -1)) //
	                .setKl(4E-5).setKq(2E-7));
		
		
		camera.setImageWriter(new ImageWriter("Department of Mysteries Improved", 500, 500)) //
		.setRayTracer(new RayTracerBasic(scene)) //
		.setSupersamplingType(SUPERSAMPLING_TYPE.SUPERSAMPLING) //
		.renderImage() //
		.writeToImage();
	}


	}

