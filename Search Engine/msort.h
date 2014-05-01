#include <vector>
#include <iostream>

struct AlphaStrComp {
    bool operator()(const string& lhs, const string& rhs) 
    { 
      return lhs < rhs; 
    }
  };


template <class T, class AlphaStrComp>
void MergeSort (vector<T> list[] ,AlphaStrComp comp, int l, int r){
{
	if (l<r) { // otherwise, just one element, which is sorted
	int m = floor((l+r)/2);
	MergeSort(list, l, m);
	MergeSort(list, m+1, r);
	Merge(list,comp, l, r, m); // this is a separate function given below
	}

}

template <class T, class AlphaStrComp>
void Merge (T list[], int l, int r, int m)
{
	T temp[r+1-l];
	// trace through the two subarrays, copying into a temporary one
	int i = l, j = m+1, k = 0;
	while (i <= m || j <= r) // at least one subarray contains another element
	{
		if (i <= m && (j > r || comp(list[i],list[j])  ) )
		// next smallest element in left subarray
		{ temp[k] = list[i]; i ++; k ++; 
		}
		else { 
			temp[k] = list[j]; j ++; k ++; 
		}
		// next smallest element in right subarray
	}
	// now copy over the merged array into the original
	for (k = 0; k < r+1-l; k ++)
	list[k+l] = temp[k];
}


// int main(int argc, char const *argv[])
// {	

// 	vector<string> vec;
// 	vec.push_back("Bob");
// 	vec.push_back("Ace");
// 	vec.push_back("hot");
// 	vec.push_back("dog");
// 	AlphaStrComp comp;
// 	mergeSort (vec, comp, 0, vec.size());
// 	return 0;
// }