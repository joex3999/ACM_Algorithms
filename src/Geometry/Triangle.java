package Geometry;

public class Triangle {

	static final double EPS = 1e-9;
	
	Point a, b, c;
	double ab, bc, ca;

	Triangle(Point p, Point q, Point r)	//counter clockwise
	{
		a = p;
		if(Point.ccw(p, q, r)) { b = q; c = r; }
		else { b = r; c = q; }

		ab = a.dist(b); bc = b.dist(c); ca = c.dist(a);
	}
	boolean contains (Point p ){
		// a = p1 b = p2 c = p3
		double alpha = ((b.y - c.y)*(p.x - c.x) + (c.x - b.x)*(p.y - c.y)) / ((b.y - c.y)*(a.x - c.x) + (c.x - b.x)*(a.y - c.y));
		double beta = ((c.y - a.y)*(p.x - c.x) + (a.x - c.x)*(p.y - c.y)) /
		       ((b.y - c.y)*(a.x - c.x) + (c.x - b.x)*(a.y - c.y));
		double gamma = 1.0f - alpha - beta;
		if(alpha>0 && beta >0 && gamma >0 ){
			return true ;
			
		}
		return false ;
		/*
		 * float alpha = ((p2.y - p3.y)*(p.x - p3.x) + (p3.x - p2.x)*(p.y - p3.y)) /
        ((p2.y - p3.y)*(p1.x - p3.x) + (p3.x - p2.x)*(p1.y - p3.y));
float beta = ((p3.y - p1.y)*(p.x - p3.x) + (p1.x - p3.x)*(p.y - p3.y)) /
       ((p2.y - p3.y)*(p1.x - p3.x) + (p3.x - p2.x)*(p1.y - p3.y));
float gamma = 1.0f - alpha - beta;
		 */
	}

	double perm()
	{
		return ab + bc + ca;
	}

	double area()
	{
		double s = (ab + bc + ca) / 2.0;
		return Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));		//take care of overflow
	}

	double area2() 
	{  return Math.abs(a.x * b.y - a.y * b.x + b.x * c.y - b.y * c.x + c.x * a.y - c.y * a.x) / 2.0; }
	/*
	 * 1. We take the absolute value because the result could be negative which means that point q is on
	 *    the left of the line segment pr
	 * 
	 * 2. If the area is zero, then the three points are collinear
	 */

	double area3()
	{
		return 0.5 * ((c.y - a.y) * (b.x - a.x) - (b.y - a.y) * (c.x - a.x));
	}

	double rInCircle()
	{
		return area() / (perm() * 0.5);
	}

	Point inCenter()				//intersection of the angle bisectors, center of inscribed circle
	{
		double p = perm();
		double x = (a.x * bc + b.x * ca + c.x * ab) / p;
		double y = (a.y * bc + b.y * ca + c.y * ab) / p;
		return new Point(x, y);
	}

	double rCircumCircle()
	{
		return ab * bc * ca / (4.0 * area());
	}

	Point circumCircle() 
	{
		double bax = b.x - a.x, bay = b.y - a.y;
		double cax = c.x - a.x, cay = c.y - a.y;

		double e = bax * (a.x + b.x) + bay * (a.y + b.y);
		double f = cax * (a.x + c.x) + cay * (a.y + c.y);
		double g = 2.0 * (bax * (c.y - b.y) - bay * (c.x - b.x));

		if (Math.abs(g) < EPS) return null;

		return new Point((cay*e - bay*f) / g, (bax*f - cax*e) / g);

	}

	static double areaMedians(double ma, double mb, double mc)		//medians of the triangle
	{
		double s = (ma + mb + mc) / 2.0;							//max(ma, mb, mc) < s
		return Math.sqrt(s * (s - ma) * (s - mb) * (s - mc)) * 4.0 / 3.0;
	}

	static double areaHeights(double ha, double hb, double hc)		//heights of the triangle
	{
		double ha_1 = 1.0 / ha, hb_1 = 1.0 / hb, hc_1 = 1.0 / hc;
		double s = (ha_1 + hb_1 + hc_1) / 2.0;
		return 1.0 / (Math.sqrt(s * (s - ha_1) * (s - hb_1) * (s - hc_1)) * 4.0);
	}

}
