// tc: O(nlogn), sc: O(n)
class Main {
    public static void mergeTwoArrays(int[] nums, int start, int mid, int end) {
        int[] result = new int[end - start + 1];
        
        int i = start;
        int j = mid + 1;
        int k = 0;
        
        while(i <= mid && j <= end) {
            if(nums[i] <=  nums[j]) {
                result[k] = nums[i];
                i++;
            }
            else {
                result[k] = nums[j];
                j++;
            }
            k++;
        }
        
        while(i <= mid) {
            result[k] = nums[i];
            i++;
            k++;
        }
        
        while(j <= end) {
            result[k] = nums[j];
            j++;
            k++;
        }
        
        for(int x = 0; x < result.length; x++) {
            nums[x + start] = result[x];
        }
    }
    
    public static void mergeSort(int[] nums, int start, int end) {
        if(start >= end) {
            return;
        }
        
        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        mergeTwoArrays(nums, start, mid, end);
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{7, 6, 5, 4, 3, 2, 1};
        
        mergeSort(nums, 0, nums.length - 1);
        
        for(int num: nums) {
            System.out.print(num + " ");
        }
    }
}