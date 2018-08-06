package Engine.ShapedUnits;

import Engine.Map;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Wall extends Rectangle{
	public Wall(Map map, int startX, int startY, int lengthX, int lengthY){
		super(map, startX, startY, lengthX, lengthY);
		PolygonShape sd = new PolygonShape();
		//thickness
		sd.setAsBox(lengthX/2, lengthY/2);
		BodyDef bd = new BodyDef();
		bd.type = BodyType.STATIC;
		//position
		bd.position = new Vec2(startX+lengthX/2-Map.WALL_THICKNESS/2, startY+lengthY/2-Map.WALL_THICKNESS/2);
		Body b = map.createBody(bd);
		FixtureDef fd = new FixtureDef();
		fd.shape = sd;
		fd.friction = 1.0f;
		b.createFixture(fd);
	}
}
