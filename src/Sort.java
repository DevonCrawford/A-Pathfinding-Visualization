import java.util.ArrayList;

/* Sort functions used in path finding to
 * determine lowest F cost Node. Bubble sort is mainly
 * used, quick sort needs work. Currently not working
 * by Devon Crawford
 */
public class Sort {

	private boolean lowToHigh, highToLow;

	public Sort() {
		lowToHigh = true;
		highToLow = false;
	}
        
        public void selectionSort(int[] data){
            if(lowToHigh){
                    for(int i = 0; i < data.length; i++){
                        int minIndex = i;
                        for (int j = i; j < data.length; j++) {
                                if(data[minIndex] > data[j]){
                                    minIndex = j;
                                }
                        }
                        int temp = data[i];
                        data[i] = data[minIndex];
                        data[minIndex] = temp;
                }
            }else if(highToLow){
                    for(int i = 0; i < data.length; i++){
                            int minIndex = i;
                            for (int j = i; j < data.length; j++) {
                                    if(data[minIndex] < data[j]){
                                        minIndex = j;
                                    }
                            }
                            int temp = data[i];
                            data[i] = data[minIndex];
                            data[minIndex] = temp;
                    }
            }
                
        }
        
        public void selectionSort(ArrayList<Node> list){
                if(lowToHigh){
                        for(int i = 0; i < list.size(); i++){
                                int minIndex = i;
                                for (int j = i; j < list.size(); j++) {
                                        if(list.get(minIndex).getF() > list.get(j).getF()){
                                            minIndex = j;
                                        }
                                }
                                Node temp = list.get(i);
                                list.set(i, list.get(minIndex));
                                list.set(minIndex, temp);
                        }
                }else if(highToLow){
                        for(int i = 0; i < list.size(); i++){
                                int minIndex = i;
                                for (int j = i; j < list.size(); j++) {
                                        if(list.get(minIndex).getF() < list.get(j).getF()){
                                            minIndex = j;
                                        }
                                }
                                Node temp = list.get(i);
                                list.set(i, list.get(minIndex));
                                list.set(minIndex, temp);
                        }
                }
        }

	public void bubbleSort(int[] data) {
		int Switch = -1;
		int temp;

		while (Switch != 0) {
			Switch = 0;

			if (lowToHigh) {
				for (int i = 0; i < data.length - 1; i++) {
					if (data[i] > data[i + 1]) {
						temp = data[i];
						data[i] = data[i + 1];
						data[i + 1] = temp;
						Switch = 1;
					}
				}
			} else if (highToLow) {
				for (int i = 0; i < data.length - 1; i++) {
					if (data[i] < data[i + 1]) {
						temp = data[i];
						data[i] = data[i + 1];
						data[i + 1] = temp;
						Switch = 1;
					}
				}
			}
		}
	}

	public void bubbleSort(ArrayList<Node> list) {
		int Switch = -1;
		Node temp;

		while (Switch != 0) {
			Switch = 0;

			if (lowToHigh) {
				for (int i = 0; i < list.size() - 1; i++) {
					if (list.get(i).getF() > list.get(i + 1).getF()) {
						temp = list.get(i);
						list.remove(i);
						list.add(i + 1, temp);
						Switch = 1;
					}
				}
			} else if (highToLow) {
				for (int i = 0; i < list.size() - 1; i++) {
					if (list.get(i).getF() < list.get(i + 1).getF()) {
						temp = list.get(i);
						list.remove(i);
						list.add(i + 1, temp);
						Switch = 1;
					}
				}
			}
		}
	}

	// low is 0, high is numbers.length - 1
	// TODO: FIX HIGH TO LOW QUICKSORT
	public void quickSort(int[] numbers, int low, int high) {
		int i = low, j = high;

		int pivot = numbers[low + (high - low) / 2];

		while (i <= j) {

			if (lowToHigh) {
				while (numbers[i] < pivot) {
					i++;
				}

				while (numbers[j] > pivot) {
					j--;
				}
			} else if (highToLow) {
				while (numbers[i] > pivot) {
					i++;
				}

				while (numbers[j] < pivot) {
					j--;
				}
			}

			if (i <= j) {
				int temp = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = temp;
				i++;
				j--;
			}
		}

		if (low < j)
			quickSort(numbers, low, j);
		if (i < high)
			quickSort(numbers, i, high);
	} // end of quick sort

	public void setLowToHigh() {
		lowToHigh = true;
		highToLow = false;
	}

	public void setHighToLow() {
		lowToHigh = false;
		highToLow = true;
	}
}
