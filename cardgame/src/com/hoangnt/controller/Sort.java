package com.hoangnt.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.hoangnt.model.Card;

public class Sort {

	List<Card> cards;
	List<Card> cardsAfterSort;
	static Scanner scanner;
	List<List<Card>> chiBo;
	List<List<Card>> chiLieng;
	List<List<Card>> chiChat;
	boolean chuaxet[] = new boolean[100];
	List<Integer> scoreLieng;
	List<Integer> scoreBo;
	List<Integer> scoreChat;
	int max, vitri, bo;

	public void sort() {
		cardsAfterSort = new ArrayList<>();

		Collections.sort(cards);

		while (cardsAfterSort.size() < 13) {
			max = 0;
			chiChat = new ArrayList<List<Card>>();
			chiLieng = new ArrayList<List<Card>>();
			chiBo = new ArrayList<List<Card>>();
			changeCardUsed();
			choice(0);
			scoreLieng();
			scoreBo();
			scoreChat();
			chimax(0);
			sortScore();
		}
		sortFinal();
	}

	public void sortFinal() { // ktra le 5 de thay doi
		int vitricanthem = 0;
		for (int i = 0; i < 5; i++) {
			if (i == 0 && cardsAfterSort.get(i).getNumber() + 1 == cardsAfterSort.get(i + 1).getNumber())
				break;
			if (i == 0 && cardsAfterSort.get(i).getMeterial().equals(cardsAfterSort.get(i + 1).getMeterial()))
				break;
			if (cardsAfterSort.get(i).getNumber() == cardsAfterSort.get(i + 1).getNumber()) {
				continue;
			}
			vitricanthem = i + 1;
		}
		if (vitricanthem == 4) {
			cardsAfterSort.add(vitricanthem, cardsAfterSort.get(cardsAfterSort.size() - 1));
			cardsAfterSort.remove(cardsAfterSort.size() - 1);
			System.out.println("aaaaaaaaaaaaaaa" + vitricanthem);
		}

		vitricanthem = 0;
		for (int i = 5; i < 10; i++) {
			if (i == 5 && cardsAfterSort.get(i).getNumber() + 1 == cardsAfterSort.get(i + 1).getNumber())
				break;
			if (i == 5 && cardsAfterSort.get(i).getMeterial().equals(cardsAfterSort.get(i + 1).getMeterial()))
				break;
			if (cardsAfterSort.get(i).getNumber() == cardsAfterSort.get(i + 1).getNumber()) {
				continue;
			}
			vitricanthem = i + 1;
		}
		if (vitricanthem == 9) {
			cardsAfterSort.add(vitricanthem, cardsAfterSort.get(cardsAfterSort.size() - 1));
			cardsAfterSort.remove(cardsAfterSort.size() - 1);
			System.out.println("aaaaaaaaaaaaaaa" + vitricanthem);
		}
	}

	public void chimax(int k) { // chon diem cao nhat cua bo , lieng , chat
		if (scoreLieng.get(k) >= max) {
			max = scoreLieng.get(k);
			vitri = k;
			bo = 1;
		}
		if (scoreBo.get(k) >= max) {
			max = scoreBo.get(k);
			bo = 2;
		}
		if (scoreChat.get(k) >= max) {
			max = scoreChat.get(k);
			bo = 3;
		}
		if (k < cards.size() - 1) {
			chimax(++k);
		}
	}

	public void sortScore() { // sap xep cac la bai theo diem da duoc chon

		Card card;
		if (bo == 1) {
			for (int i = vitri; i < vitri + 5; i++) {
				card = new Card(cards.get(i).getNumber(), cards.get(i).getMeterial());
				cardsAfterSort.add(card);
				chuaxet[i] = false;
			}
		}

		if (bo == 2) {
			for (int i = 0; i < scoreBo.size(); i++) {
				if (scoreBo.get(i) == max) {
					card = new Card(cards.get(i).getNumber(), cards.get(i).getMeterial());
					cardsAfterSort.add(card);
					chuaxet[i] = false;
				}
			}
		}
		if (bo == 3) {
			for (int i = 0; i < scoreChat.size(); i++) {
				if (scoreChat.get(i) == max) {
					card = new Card(cards.get(i).getNumber(), cards.get(i).getMeterial());
					cardsAfterSort.add(card);
					chuaxet[i] = false;
				}
			}
		}
	}

	public List<Integer> scoreLieng() { // tinh diem tung quan bai theo lieng
		scoreLieng = new ArrayList<Integer>();
		for (int j = 0; j < chiLieng.size(); j++) {
			List<Card> listCard = chiLieng.get(j);
			if (listCard.size() == 5)
				if (listCard.get(0).getMeterial().equals(listCard.get(1).getMeterial())
						&& listCard.get(0).getMeterial().equals(listCard.get(2).getMeterial())
						&& listCard.get(0).getMeterial().equals(listCard.get(3).getMeterial())
						&& listCard.get(0).getMeterial().equals(listCard.get(4).getMeterial())) {
					scoreLieng.add(9);
				} else {
					scoreLieng.add(5);
				}
			else
				scoreLieng.add(0);
		}
		return scoreLieng;
	}

	public List<Integer> scoreBo() { // tinh diem tung quan bai theo bo
		scoreBo = new ArrayList<Integer>();
		for (int j = 0; j < chiBo.size(); j++) {
			List<Card> listCard = chiBo.get(j);

			if (listCard.size() == 4)
				scoreBo.add(8);
			if (listCard.size() == 3)
				scoreBo.add(4);
			if (listCard.size() == 2)
				scoreBo.add(2);
			if (listCard.size() == 1 && listCard.get(0).getNumber() != -1)
				scoreBo.add(1);
			if (listCard.size() == 1 && listCard.get(0).getNumber() == -1)
				scoreBo.add(0);
		}
		return scoreBo;
	}

	public List<Integer> scoreChat() { // tinh diem tung quan bai theo chat
		scoreChat = new ArrayList<Integer>();
		for (int j = 0; j < chiChat.size(); j++) {
			List<Card> listCard = chiChat.get(j);
			if (listCard.size() == 5)
				scoreChat.add(6);
			else
				scoreChat.add(0);
		}
		return scoreChat;
	}

	public void choice(int k) { // sap xep tung quan bai theo dang lieng , bo , chat
		List<Card> bo = new ArrayList<>();
		List<Card> lieng = new ArrayList<>();
		List<Card> chat = new ArrayList<>();
		bo.add(cards.get(k));

		lieng.add(cards.get(k));

		chat.add(cards.get(k));
		if (chuaxet[k]) {

			int countlieng = 1;
			int countchat = 1;
			int j = k;
			int c = k;

			for (int i = 0; i < cards.size(); i++) {
				if (i != k && cards.get(i).getNumber() == cards.get(k).getNumber() && chuaxet[i]) {
					bo.add(cards.get(i));
				}
				if (i != k && cards.get(i).getNumber() == cards.get(j).getNumber() + 1 && countlieng <= 5 && chuaxet[i]
						&& chuaxet[j]) {
					countlieng++;
					j = i;
					lieng.add(cards.get(i));
				}
				if (i != k && cards.get(i).getMeterial().equals(cards.get(c).getMeterial()) && countchat <= 5
						&& chuaxet[i] && chuaxet[c]) {
					countchat++;
					c = i;
					chat.add(cards.get(i));
				}
			}
		}

		chiBo.add(bo);
		chiLieng.add(lieng);
		chiChat.add(chat);
		if (k < cards.size() - 1)
			choice(++k);

	}

	public void changeCardUsed() { // thay doi la bai da duoc duyet thanh la (-1 k)
		for (int i = 0; i < cards.size(); i++) {
			if (!chuaxet[i]) {
				cards.get(i).setNumber(-1);
				cards.get(i).setMeterial("k");
			}
		}
	}

	public void tick() { // danh dau nhung la bai da duoc duyet khi sap xep
		for (int i = 0; i < 13; i++) {
			chuaxet[i] = true;
		}
	}

	public void result() { // in ket qua sau khi sap xep
		cardsAfterSort.forEach(card -> {
			System.out.println(card.getNumber() + " " + card.getMeterial());
		});
	}

	public void init() { // khoi tao 13 la bai ban dau va goi cac ham xu ly
		cards = new ArrayList<Card>();
		String in;
		String meterial = "";
		int number = 0;
		System.out.println("nhap 13 la bai : ");
		for (int i = 0; i < 13; i++) {
			Card card = new Card();
			in = scanner.nextLine().trim();
			if (in.matches("\\d{1,2}\\s.{1,4}")) {
				number = Integer.parseInt(in.split("\\s")[0]);
				meterial = in.split("\\s")[1];
				card.setNumber(number);
				card.setMeterial(meterial);
				cards.add(card);
			} else {
				i--;
				System.out.println("Nhap sai dinh dang hay nhap lai la bai nay");
			}
		}
		tick();
		sort();
		result();
	}

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		new Sort().init();
	}
}
