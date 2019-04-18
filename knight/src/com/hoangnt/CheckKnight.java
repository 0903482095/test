package com.hoangnt;

import java.util.Scanner;

public class CheckKnight {
	String start, end;
	int startX, startY, endX, endY;
	private static Scanner scanner;

	public boolean check() { // ham ktra co dung khong
		init();
		if ((Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 2)
				|| (Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 1)) {// ktra nuoc di tu vi tri ban dau
																					// den vi tri dich co dung hay
																					// khong?
			return true;
		}
		return false;
	}

	public void init() { // du lieu nhap tu ban phim gom 2 diem de ktra nuoc di quan ma
		scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Nhap vi tri");
			start = scanner.nextLine();
			if (start.matches("[a-h][1-8]")) { // ktra dinh dang cua vi tri nhap
				// lay toa do vi tri ban dau
				startX = (int) start.substring(0, 1).charAt(0);
				startY = Integer.parseInt(start.substring(1, 2));
				break;
			}
		}
		while (true) {
			System.out.println("Nhap dich den");
			end = scanner.nextLine();
			if (end.matches("[a-h][1-8]")) { // ktra dinh dang cua vi tri nhap
				// lay toa do vi tri dich
				endX = (int) end.substring(0, 1).charAt(0);
				endY = Integer.parseInt(end.substring(1, 2));
				break;
			}
		}
	}
}
