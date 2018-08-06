package UnitTests;

import Engine.Map;

public class TestMethods{
	static double runMap(Map map, double delta, double seconds){
		int count=(int)(seconds/delta);
		for(int i=0;i<count;i++){
			map.tick(delta);
		}
		double timeRan=delta*count;
		System.out.println("Map ran for "+timeRan+" seconds");
		return timeRan;
	}
}
