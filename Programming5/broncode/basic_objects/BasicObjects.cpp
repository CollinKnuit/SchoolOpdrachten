//============================================================================
// Name        		: Collin Knuit
// Version     		: 1.0
// Description 		: Ray Tracing in C++, Ansi-style
//============================================================================

#include "BasicObjects.hpp"

namespace shapes{

// constructors/destructors

Shape::Shape( double contrast, double coeff):
		contrast(contrast)
		,coeff(coeff){
};

Shape::~Shape() {
	// TODO Auto-generated destructor stub
}


Triangle::Triangle(const Vect3D &A, const Vect3D &B, const Vect3D &C,  double contrast, double coeff) : Shape(contrast, coeff)
, A(A), B(B),C(C){

}

Triangle::~Triangle() {
	// TODO Auto-generated destructor stub
}

Sphere::Sphere(double &radius, Vect3D &origin, double &contrast, double &coeff): Shape(contrast, coeff)
,radius(radius) ,r2(pow(radius,2)) ,origin(origin){};

Sphere::~Sphere() {
	// TODO Auto-generated destructor stub
}

Cube::Cube(Vect3D origin, short size,double &contrast, double &coeff): Shape(contrast, coeff){
	auto nSize = size/2;
	auto A = Vect3D(origin-nSize);
	auto B = Vect3D(A,0,size,0);
	auto C = Vect3D(A,-size,0,0);
	auto D = Vect3D(B,-size,0,0);
	auto E = Vect3D(A,0,0,size);
	auto F = Vect3D(E,0,size,0);
	auto G = Vect3D(F,-size,0,0);
	auto H = Vect3D(E,-size,0,0);

	setPoints(A, B, C, D, E, F, G, H);
}

Cube::Cube(Vect3D &A, Vect3D &B,Vect3D &C,Vect3D &D,Vect3D &E,Vect3D &F,Vect3D &G,Vect3D &H,double &contrast, double &coeff ):Shape(contrast, coeff){
	setPoints(A, B, C, D, E, F, G, H);
}

Cube::~Cube() {
	for ( int i = 0; i < (int)triangels.size(); ++i ) {
        delete triangels[i];
    }
}

// coeff is supose to be 1 at all times currently
Lamp::Lamp(double &radius, Vect3D &origin,double &contrast, double &coeff): Sphere(radius, origin, contrast, coeff){
};

Lamp::~Lamp(){
}



// Just used to stop the compiler from screaming
bool Shape::isItThisShape(const Vect3D &dir, const Vect3D &origin, Vect3D &origin2, Vect3D &dir2, double &distance){

	return false;
}

// implementation of function in parent using the analytic method
bool Sphere::isItThisShape(const Vect3D &dir, const Vect3D &origin, Vect3D &origin2, Vect3D &dir2, double &distance){

	auto l = origin - this->origin;
	auto z = dir.dotProduct(l);
	auto d = pow(z,2)+r2-l.dotProduct();
	if(d<0)	return false;
	auto e = 	-z
				-sqrt(d);
	if(e<=0)	return false;
	distance = e;

	origin2 = origin+(dir*distance);// to calulate the refelection
	auto normal = (origin2 - this->origin).normalize();
	dir2 = dir - (normal *  ((dir.dotProduct(normal)) * 2));
	return true;
}


// implementation of function in parent
bool Triangle::isItThisShape(const Vect3D &dir, const Vect3D &origin, Vect3D &origin2, Vect3D &dir2, double &distance){
	Vect3D E1 = B - A;
	Vect3D E2 = C - A;

	Vect3D pVect = dir.crossProduct(E2);
	double det = E1.dotProduct(pVect);
	double invDet = 1/det;
	if(det < 0.0000001) return false; // This ray is parallel to this triangle
	Vect3D tVect = origin - A;
	double u = tVect.dotProduct(pVect)*invDet;
	if(u < 0.0 || u > 1) return false;
	origin2 = tVect.crossProduct(E1); // new origin
	double v = dir.dotProduct(origin2)*invDet;
	if(v < 0 || u + v > 1) return false;

	// should work now
	distance = E2.dotProduct(origin2)*invDet;

	auto normal = origin2.normalize();
	dir2 = dir - (normal *  ((dir.dotProduct(normal)) * 2));

	return true;


}

std::vector<Triangle*> Cube::getTriangles(){
	return this->triangels;
}

/**
 *    E-------F
 *	 /|      /|
 *	/ |     / |
 * H--|----G  |
 * |  A----|--B
 * | /     | /
 * C-------D
 */
void Cube::setPoints(Vect3D A, Vect3D B,Vect3D C,Vect3D D,Vect3D E,Vect3D F,Vect3D G,Vect3D H){
	triangels.push_back( new Triangle(A,B,F, contrast, coeff)); // 0
	triangels.push_back( new Triangle(A,E,F,contrast, coeff)); // 1
	triangels.push_back( new Triangle(A,E,H,contrast, coeff)); // 2
	triangels.push_back( new Triangle(A,C,H,contrast, coeff)); // 3
	triangels.push_back( new Triangle(A,B,D,contrast, coeff)); // 4
	triangels.push_back( new Triangle(A,C,D,contrast, coeff)); // 5
	triangels.push_back( new Triangle(G,D,B,contrast, coeff)); // 6
	triangels.push_back( new Triangle(G,F,B,contrast, coeff)); // 7
	triangels.push_back( new Triangle(G,F,E,contrast, coeff)); // 8
	triangels.push_back( new Triangle(G,H,E,contrast, coeff)); // 9
	triangels.push_back( new Triangle(G,D,C,contrast, coeff)); // 10
	triangels.push_back( new Triangle(G,H,C,contrast, coeff)); // 11
}

// It basically uses the lazy way of drawing a cube it simple loops all triangles
bool Cube::isItThisShape(const Vect3D &dir, const Vect3D &origin, Vect3D &origin2, Vect3D &dir2, double &distance){
	for ( int i = 0; i < (int)triangels.size(); ++i ) {
	       if(triangels[i]->isItThisShape(dir, origin, origin2, dir2, distance)) return true;
	}
	return false;
}

// getters and setters if needed
double  Shape::getContrast() const {return contrast;}
double  Shape::getCoeff() const {return contrast;}
Vect3D Triangle::getA() const {return A;}
Vect3D Triangle::getB() const {return B;}
Vect3D Triangle::getC() const {return C;}
Vect3D Sphere::getOrgin() const {return origin;}



}
