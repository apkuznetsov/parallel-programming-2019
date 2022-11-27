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

int test_int() {
	const int ARR_SIZE = 40000;

	const double MY_PROCESSOR_CLOCKS_PER_SEC = 1800000000;
	unsigned __int64 time1 = 0;
	unsigned __int64 time2 = 0;

	int a[ARR_SIZE], b[ARR_SIZE], c[ARR_SIZE];

	for (int i = 0; i < ARR_SIZE; i++)
		a[i] = rand();


	for (int i = 0; i < ARR_SIZE; i++)
		b[i] = rand();


	time1 = __rdtsc();

	for (int i = 0; i < ARR_SIZE; i++)
		c[i] = a[i] + b[i];

	time2 = __rdtsc();

	double time = (time2 - time1) / MY_PROCESSOR_CLOCKS_PER_SEC;
	cout << time << endl;

	return 0;
}

int test_float() {
	const int ARR_SIZE = 40000;

	const double MY_PROCESSOR_CLOCKS_PER_SEC = 1800000000;
	unsigned __int64 time1 = 0;
	unsigned __int64 time2 = 0;

	float a[ARR_SIZE], b[ARR_SIZE], c[ARR_SIZE];

	for (int i = 0; i < ARR_SIZE; i++)
		a[i] = static_cast <float> (rand()) / static_cast <float> (RAND_MAX);


	for (int i = 0; i < ARR_SIZE; i++)
		b[i] = static_cast <float> (rand()) / static_cast <float> (RAND_MAX);


	time1 = __rdtsc();

	for (int i = 0; i < ARR_SIZE; i++)
		c[i] = a[i] + b[i];

	time2 = __rdtsc();

	double time = (time2 - time1) / MY_PROCESSOR_CLOCKS_PER_SEC;
	cout << time << endl;

	return 0;
}

int test_double()
{
	const int ARR_SIZE = 40000;

	const double MY_PROCESSOR_CLOCKS_PER_SEC = 1800000000;
	unsigned __int64 time1 = 0;
	unsigned __int64 time2 = 0;

	double a[ARR_SIZE], b[ARR_SIZE], c[ARR_SIZE];

	for (int i = 0; i < ARR_SIZE; i++)
		a[i] = rand() / RAND_MAX;


	for (int i = 0; i < ARR_SIZE; i++)
		b[i] = rand() / RAND_MAX;;


	time1 = __rdtsc();

	for (int i = 0; i < ARR_SIZE; i++)
		c[i] = a[i] + b[i];

	time2 = __rdtsc();

	double time = (time2 - time1) / MY_PROCESSOR_CLOCKS_PER_SEC;
	cout << time << endl;

	return 0;
}
