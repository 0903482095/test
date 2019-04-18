package com.hoangnt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TraceKnight {
	boolean chuaxet[] = new boolean[89]; // mang chuaxet dung de danh dau vi tri cac diem da duoc di qua
	int QUEUE[] = new int[65]; // mang QUEUE dung de them cac diem da di qua va de xu li
	int trace[] = new int[89];
	static int start, end; // diem bat dau va diem ket thuc
	private static Scanner scanner;

	public void init() { // du lieu nhap vao ban phim theo dinh dang (vitri1=>vitri2)
		while (true) {
			scanner = new Scanner(System.in);
			System.out.println("\nNhap vao duong di vd (a1=>c5) : ");
			String tam = scanner.nextLine().trim();
			if (tam.matches("[a-h][1-8]=>[a-h][1-8]")) { // ktra dinh dang nhap dung hay khong

				// chuyen du lieu tu String sang int
				start = convert(tam.split("=>")[0]);
				end = convert(tam.split("=>")[1]);
				break;
			} else {
				System.out.println("Nhap lai duong di vd: a1=>c5 :");
			}
		}

		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				chuaxet[Integer.parseInt(String.valueOf(i) + String.valueOf(j))] = true; // khoi tao mang chuaxet[]
			}
		}
	}

	public void BFS() { // BFS vi tri ban dau den vi tri dich
		init();
		trace[start] = -1;
		int u;
		int dauQ, cuoiQ; // vi tri cua hang doi
		dauQ = 1;
		cuoiQ = 1;
		QUEUE[cuoiQ] = start;
		chuaxet[start] = false;
		while (dauQ <= cuoiQ && chuaxet[end] == true) { // them cac diem co the di tu vi tri hien tai
			u = QUEUE[dauQ];
			dauQ++;

			// co toi da 6 nuoc di co the di tu vi tri hien tai cua quan ma
			if (u > 21 && chuaxet[u - 21]) {
				cuoiQ++;
				middleware(u, 21, cuoiQ);
			}
			if (u > 19 && chuaxet[u - 19]) {
				cuoiQ++;
				middleware(u, 19, cuoiQ);
			}
			if (u > 8 && chuaxet[u - 8]) {
				cuoiQ++;
				middleware(u, 8, cuoiQ);
			}
			if (u > 12 && chuaxet[u - 12]) {
				cuoiQ++;
				middleware(u, 12, cuoiQ);
			}
			if (u <= 76 && chuaxet[u + 12]) {
				cuoiQ++;
				middleware(u, -12, cuoiQ);
			}
			if (u <= 80 && chuaxet[u + 8]) {
				cuoiQ++;
				middleware(u, -8, cuoiQ);
			}
			if (u <= 69 && chuaxet[u + 19]) {
				cuoiQ++;
				middleware(u, -19, cuoiQ);
			}
			if (u <= 67 && chuaxet[u + 21]) {
				cuoiQ++;
				middleware(u, -21, cuoiQ);
			}
		}
		result();
	}

	public void result() { // in ket qua
		List<String> traces = new ArrayList<String>();
		while (start != end) {
			traces.add(reconvert(end));
			end = trace[end];
		}
		traces.add(reconvert(start));
		Collections.reverse(traces);
		System.out.println("Duong di ngan nhat la : ");
		for (int i = 0; i < traces.size() - 1; i++) {
			System.out.print(traces.get(i) + " => ");
		}
		System.out.println(traces.get(traces.size() - 1));
	}

	public void middleware(int current_position, int middle_position, int cuoiQ) {
		QUEUE[cuoiQ] = current_position - middle_position; // them vi tri tiep theo co the di vao hang doi
		chuaxet[current_position - middle_position] = false; // gan mang chua xet la da xet o vi tri tiep theo nay
		trace[current_position - middle_position] = current_position;// danh dau vi tri truoc cua diem duoc them vao
																		// hang doi
	}

	public static int convert(String vitri) {
		return Integer.parseInt((int) vitri.substring(0, 1).charAt(0) - 96 + "" + vitri.substring(1, 2));
	}

	public static String reconvert(int vitri) {
		return (char) (Integer.parseInt(String.valueOf(vitri).substring(0, 1)) + 96) + ""
				+ String.valueOf(vitri).substring(1, 2);
	}
}
