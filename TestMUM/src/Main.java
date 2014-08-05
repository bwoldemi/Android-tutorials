import java.util.Vector;

public class Main {

	public static void main(String[] args) {

		int[] a = { -1, 5, 8, 17, 15 };

		Main m = new Main();
		System.out.println(m.isCubePowerful2(370));
		// System.out.println();
	}

	int[] doIntegerBasedRounding(int[] a, int n) {

		if (n <= 0) {
			return a;
		}
		int result[] = new int[a.length];

		for (int i = 0; i < a.length; i++) {
			if (a[i] % n == 0 || a[i] < 0) {
				result[i - 1] = a[i];
			} else {
				int min = a[i] / n * n;
				int max = a[i] / n * n + n;

				if ((a[i] - min) == (max - a[i])) {
					result[i] = max;
				} else if ((a[i] - min) > (max - a[i])) {
					result[i] = max;

				} else {
					result[i] = min;
				}
			}
		}

		return result;
	}

	int[] decodeArray(int a[]) {
		if (a.length < 2) {
			return a;
		}

		int result[] = new int[a.length - 1];

		for (int i = 1; i < a.length; i++) {
			result[i - 1] = Math.abs(a[i - 1] - a[i]);
		}

		if (a[0] < 0) {
			result[0] = result[0] * -1;
		}
		return result;
	}

	int isCubePowerful(int n) {
		Vector<Integer> cubeNumbersLessthanNumber = new Vector<Integer>();

		for (int i = 0; i < n; i++) {
			if ((i * i * i) < n) {
				cubeNumbersLessthanNumber.add(i * i * i);
				System.out.println(i);
			} else if ((i * i * i) > n) {
				break;
			}

		}

		for (int i = 0; i < cubeNumbersLessthanNumber.size(); i++) {
			for (int j = 0; j < cubeNumbersLessthanNumber.size(); j++) {
				for (int k = 0; k < cubeNumbersLessthanNumber.size(); k++) {
					if ((cubeNumbersLessthanNumber.get(i)
							+ cubeNumbersLessthanNumber.get(j) + cubeNumbersLessthanNumber
								.get(k)) == n) {
						System.out.println(cubeNumbersLessthanNumber.get(i));
						System.out.println(cubeNumbersLessthanNumber.get(j));
						System.out.println(cubeNumbersLessthanNumber.get(k));
						return 1;

					}
				}
			}
		}

		return 0;

	}

	int isCubePowerful2(int n) {
		int sum = 0;
		int temp=n;
		int r;
		
		while (temp != 0) {
			r = temp % 10;
			sum = sum + r * r * r;
			temp = temp / 10;
		}
		if(n==sum){
			return 1;
		}else {
			return 0;
		}
		
	}
}
