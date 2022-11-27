#include <iostream>
#include <stdio.h>
#include <math.h>
#include <conio.h>
#include <windows.h>
#include <time.h>
#include <cstdlib>
#include <iomanip>
#pragma intrinsic(__rdtsc)
#define _USE_MATH_DEFINES

using namespace std;

int main2()
{
	const int REPEAT_TIMES = 100000;

	const double MY_PROCESSOR_CLOCKS_PER_SEC = 1800000000;
	unsigned __int64 time1;
	unsigned __int64 time2;

	time1 = __rdtsc();

	double a = 0;
	double x = 0;
	double y = 0;
	double sin_a = 0;
	double cos_a = 0;

	double t1 = 0;
	double t2 = 0;

	for (int i = 0; i < REPEAT_TIMES; i++)
	{
		a = (double)rand() / (double)RAND_MAX * (6.28);;
		x = rand() % 100;
		y = rand() % 100;
		sin_a = sin(a);
		cos_a = cos(a);

		t1 = sin_a * x + cos_a * y;
		t2 = -cos_a * x + sin_a * y;
	}

	time2 = __rdtsc();

	double time = (time2 - time1) / MY_PROCESSOR_CLOCKS_PER_SEC;
	cout << "time = " << time << endl;

	return 0;
}
