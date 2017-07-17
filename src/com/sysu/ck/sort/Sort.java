package com.sysu.ck.sort;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Sort {
	// 如果第i个元素小于第j个元素，那么就从第j个元素依次往后移一个位置直到第i个元素，并令a[j] = a[i]
	public void insertSort(int[] a, int n) {
		System.out.println("insert sort:");
		Date start = new Date();
		for (int i = 1; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				if (a[i] < a[j]) {
					insert(a, i, j);
				}
			}
		}
		Date end = new Date();
		long time = end.getTime() - start.getTime();
		System.out.println(time);
		if (!check(a, n))
			System.out.println("error");
	}
	
	private void insert(int[] a, int i, int j) {
		int tmp = a[i];
		for (int k = i; k > j; --k) {
			a[k] = a[k-1];
		}
		a[j] = tmp;
	}
	// 每次循环，都找出无序组中最小的那个
	public void selectSort(int[] a, int n) {
		System.out.println("select sort:");
		Date start = new Date();
		for (int i = 0; i < n; ++i) {
			for (int j = i+1; j < n; ++j) {
				if (a[j] < a[i]) {
					swap(a, i, j);
				}
			}
		}
		Date end = new Date();
		long time = end.getTime() - start.getTime();
		System.out.println(time);
		if (!check(a, n))
			System.out.println("error");
	}
	
	private void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	// a[i]每次都和 a[i-1]进行比较，如果a[i] < a[i-1]，那么交换两者
	public void bubbleSort(int[] a, int n) {
		System.out.println("bubble sort:");
		Date start = new Date();
		for (int i = 0; i < n; ++i) {
			for (int j = 1; j < n; ++j) {
				if (a[j] < a[j-1])
					swap(a, j-1, j);
			}
		}
		Date end = new Date();
		long time = end.getTime() - start.getTime();
		System.out.println(time);
		if (!check(a, n))
			System.out.println("error");
	}
	// 从0开始，第i个元素的子节点为2*i+1,2*i+2；父节点为(i-1)/2。
	// 小堆则为父节点要比子节点小，大堆同理。
	// 小堆得出的数组是降序，大堆得出的数组是升序。
	public void heapSort(int[] a, int n) {
		System.out.println("heap sort:");
		Date start = new Date();
		bulidHeap(a, n);
		for (int i = 1; i < n; ++i) {
			swap(a, n-i, 0);
			rebulidHeap(a, 0, n-i);
		}
		Date end = new Date();
		long time = end.getTime() - start.getTime();
		System.out.println(time);
		if (!check(a, n))
			System.out.println("error");
	}
	// 把头结点跟乱序的尾部交换，然后重构树
	// 从头结点开始，检查每个子树是否都满足规定。
	private void rebulidHeap(int[] a, int j, int n) {
		int left = j*2+1;
		int right = 2*j+2;
		int bigger = left;
		if (left >= n)
			return;
		if (right < n && a[left] < a[right]) {
			bigger = right;
		}
		if (a[j] < a[bigger]) {
			swap(a, bigger, j);
			rebulidHeap(a, bigger, n);
		}
	}
	// 从尾节点开始查起，检查每个子树是否都满足规定。
	// 每次交换父子节点，都需要对子节点的树进行重构。
	private void bulidHeap(int[] a, int n) {
		for (int i = n-1; i > 0; --i) {
			int j = (i-1)/2;
			if (a[i] > a[j]) {
				swap(a, i, j);
				rebulidHeap(a, i, n);
			}
		}
	}
	// 不断的把数据分成两份，直到每份只含有一个元素，然后开始合并，合并好的每一份数据都是有序的
	// 合并过程类似于插入排序？
	public void mergeSort(int[] a, int n) {
		System.out.println("merge sort:");
		Date start = new Date();
		divide(a, n);
		Date end = new Date();
		long time = end.getTime() - start.getTime();
		System.out.println(time);
		if (!check(a, n))
			System.out.println("error");
	}
	// 如果n > 1 就对其数据进行进一步的拆分，然后对拆分好的两份数据进行合并
	private void divide(int[] a, int n) {
		if (n == 1)
			return;
		int[] first = new int[n/2];
		int[] seconde = new int[n-first.length];
		for (int i = 0; i < first.length; ++i)
			first[i] = a[i];
		for (int i = 0; i < seconde.length; ++i)
			seconde[i] = a[i+first.length];
		merge(first, first.length, seconde, seconde.length, a);
	}
	// 对两份有序的数据进行合并，但首先需要保证这两份数据是有序的，而单个的数据总是有序的，所以把这两份数据不断拆分，直到只含有一个元素
	private void merge(int[] first, int length, int[] seconde, int length2, int[] merge) {
		divide(first, length);
		divide(seconde, length2);
		int i = 0, j = 0, k = 0;
		while (i < length && j < length2) {
			if (first[i] < seconde[j]) {
				merge[k] = first[i];
				++i;
			} else {
				merge[k] = seconde[j];
				++j;
			}
			++k;
		}
		while (i < length) {
			merge[k] = first[i];
			++i;
			++k;
		}
		while (j < length2) {
			merge[k] = seconde[j];
			++j;
			++k;
		}
	}
	
	public void quickSort(int[] a, int n) {
		System.out.println("quick sort:");
		Date start = new Date();
		quick(a, 0, n);
		Date end = new Date();
		long time = end.getTime() - start.getTime();
		System.out.println(time);
		if (!check(a, n))
			System.out.println("error");
	}
	// 每次把数据分成两份，小于tag和大于tag的两部分，并确定a的位置。
	// 注意tag的位置的判定。最终跳出while循环时，big和small一定是相等的。并且可以确定，(big,high)区间内的数据都是>=tag 的，
	// 而[low, small)区间的数局也都是<=tag 的。唯一需要注意的就是a[small]/small与tag/mid的关系。
	private void quick(int[] a, int low, int high) {
		if (high - low < 2)
			return;
		if (high - low == 2) {
			if (a[low] > a[low+1])
				swap(a, low, low+1);
			return;
		}
		int mid = (low+high)/2;
		int tag = a[mid];
		int small = low, big = high-1;
		while (small < big) {
			while (a[small] <= tag && small < big) {
				++small;
			} 
			while (a[big] >= tag && big > small) {
				--big;
			}
			if (small < big) {
				swap(a, small, big);
			}
		}
		if (tag < a[small] && small > mid)
			--small;
		swap(a, mid, small);
		quick(a, low, small);
		quick(a, small+1, high);
	}

	public boolean check(int[] a, int n) {
		for (int i = 1; i < n; ++i) {
			if (a[i] < a[i-1])
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		final int max = 50000;
		int[] a = new int[max];
		for (int i = 0; i < max; ++i) {
			Random random = new Random();
			a[i] = random.nextInt(max);
			System.out.print(a[i] + " ");
		}
		System.out.println("");
		System.out.println("java:");
		Date start = new Date();
		Arrays.sort(a.clone());
		Date end = new Date();
		long time = end.getTime() - start.getTime();
		System.out.println(time);
		Sort sort = new Sort();
		sort.insertSort(a.clone(), a.length);
		sort.selectSort(a.clone(), a.length);
		sort.bubbleSort(a.clone(), a.length);
		sort.heapSort(a.clone(), a.length);
		sort.mergeSort(a.clone(), a.length);
		sort.quickSort(a.clone(), a.length);
	}
}
