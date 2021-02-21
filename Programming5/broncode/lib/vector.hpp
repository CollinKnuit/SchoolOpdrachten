//============================================================================
// Name        		: Collin Knuit
// Version     		: 1.0
// Description 		: Ray Tracing in C++, Ansi-style
//============================================================================

// !!!!!!! somehow it's not possible to make a module from this class so its a header only library !!!!!!!!!!!!!!!!!!!!!!!
#ifndef VECTOR_HPP_
#define VECTOR_HPP_
#pragma once

#include <iostream>
#include <vector>
#include <cmath>

namespace vect {

template<typename T>
class Vector {
private:
	T x, y, z;
public:
	// constructors
	Vector() :
			x(0.0), y(0.0), z(0.0) {
	}
	Vector(T n) :
			x(n), y(n), z(n) {
	}
	Vector(const T &s, const T &t, const T &u) :
			x(s), y(t), z(u) {
	}
	Vector(const Vector &vect) :
			x(vect.x), y(vect.y), z(vect.z) {
	}
	Vector(const Vector &vect, const T a, const T b, const T c) :
			x(vect.x + a), y(vect.y + b), z(vect.z + c) {
	}

	// getter
	T getX() const {
		return x;
	}
	T getY() const {
		return y;
	}
	T getZ() const {
		return z;
	}

	/*-- Vector operators --*/

	Vector<T> operator *(const T &d) const {
		return Vector<T>(x * d, y * d, z * d);
	}

	Vector<T> operator *(const Vector<T> &d) const {
		return Vector<T>(x * d.x, y * d.y, z * d.z);
	}

	Vector<T> operator +(const T &d) const {
		return Vector<T>(x + d, y + d, z + d);
	}

	Vector<T> operator +(const Vector<T> &d) const {
		return Vector<T>(x + d.x, y + d.y, z + d.z);
	}

	Vector<T> operator /(const T &d) const {
		return Vector<T>(x / d, y / d, z / d);
	}

	Vector<T> operator /(const Vector<T> &d) const {
		return Vector<T>(x / d.x, y / d.y, z / d.z);
	}

	Vector<T> operator +=(const T &d) const {
		return Vector<T>(x += d, y += d, z += d);
	}

	Vector<T> operator +=(const Vector<T> &d) const {
		return Vector<T>(x / d.x, y / d.y, z / d.z);
	}

	Vector<T> operator -(const T &d) const {
		return Vector<T>(x - d, y - d, z - d);
	}

	Vector<T> operator -(const Vector<T> &d) const {
		return Vector<T>(x - d.x, y - d.y, z - d.z);
	}

	Vector<T>& operator -=(const Vector<T> &d) {
		x -= d.x;
		y -= d.y;
		z -= d.z;
		return *this;
	}

	Vector<T>& operator -=(const T &d) {
		x -= d;
		y -= d;
		z -= d;
		return *this;
	}

	Vector<T> minZ() const {
		return Vector<T>(x, y, -z);
	}

	Vector<T>& changePos(const T &tx, const T &ty, const T &tz) {
		x = tx;
		y = ty;
		z = tz;
		return *this;
	}

	Vector<T> crossProduct(const Vector<T> &d) const {
		return Vector<T>((y * d.z) - (z * d.y), (z * d.x) - (x * d.z),
				(x * d.y) - (y * d.x));
	}

	friend std::ostream& operator <<(std::ostream &os, const Vector<T> &v) {
		os << v.x << " " << v.y << " " << v.z;
		return os;
	}

	T dotProduct(const Vector<T> &d) const {
		return x * d.x + y * d.y + z * d.z;
	}

	T dotProduct() const {
		return x * x + y * y + z * z;
	}

	T magnitude() const {
		return sqrt(x * x + y * y + z * z);
	}

	T distance(const Vector<T> &d) const {
		return sqrt(pow(x - d.x, 2) + pow(y - d.y, 2) + pow(y - d.y, 2));
	}

	Vector<T> normalize() {
		T inverse = 1 / magnitude();
		x *= inverse, y *= inverse, z *= inverse;
		return Vector<T>(x, y, z);
	}
};

}
typedef vect::Vector<float> Vect3F;
typedef vect::Vector<int> Vect3I;
typedef vect::Vector<double> Vect3D;

#endif /* VECTOR_HPP */
