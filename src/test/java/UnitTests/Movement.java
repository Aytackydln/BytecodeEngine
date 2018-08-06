package UnitTests;

import Engine.Map;
import Engine.Unit;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Movement{
	static Map gravityMap;
	static Unit gravityUnit;
	static Map freeMap;
	static Unit freeUnit;
	static float speed=5f;

	@BeforeClass
	public static void initTest(){
		gravityMap=new Map(5000,5000);
		gravityMap.setGravity(new Vec2(0f,10f));
		gravityUnit=new Unit(gravityMap,0,0);
		gravityUnit.bd.setLinearVelocity(new Vec2(0f,0f));
		gravityUnit.setBodyType(BodyType.DYNAMIC);

		freeMap=new Map(5000,5000);
		freeUnit=new Unit(freeMap,0,0);
		freeUnit.setBodyType(BodyType.DYNAMIC);
		freeUnit.bd.setLinearVelocity(new Vec2(0f,speed));
	}

	@Test
	public void testGravity(){
		double timeRan=TestMethods.runMap(gravityMap,0.01,10);
		System.out.println("Gravity test::");
		System.out.println("Map gravity is: "+gravityMap.getGravity());
		System.out.println("Unit velocity is: "+gravityUnit.bd.getLinearVelocity());
		System.out.println("Unit position is: "+gravityUnit.bd.getPosition());
		Assert.assertEquals("Gravity acceleration is wrong",10*timeRan,gravityUnit.bd.getLinearVelocity().y,0.01);
	}

	@Test
	public void testVelocity(){
		double timeRan=TestMethods.runMap(freeMap,0.01,10);
		System.out.println("Velocity test::");
		System.out.println("Unit velocity is: "+freeUnit.bd.getLinearVelocity());
		System.out.println("Unit position is: "+freeUnit.bd.getPosition());
		Assert.assertEquals(speed*timeRan, freeUnit.bd.getPosition().y,0.01);
	}
}
