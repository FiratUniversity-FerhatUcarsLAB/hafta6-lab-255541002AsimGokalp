package ogrencinotdegerlendirmesi;

import java.util.Scanner;

public class Proje1_NotSistemi {

	public static void main(String[] args) {
/**
		* Ad Soyad: ASIM GÖKALP
		* Numara: 255541002
		* Proje: Ögrenci Not Değerlendirmesi
		* Tarih: 26.11.2025
		*/
		
        Scanner input = new Scanner(System.in);

        System.out.print("Vize notu: ");
        double vize = input.nextDouble();

        System.out.print("Final notu: ");
        double fin = input.nextDouble();

        System.out.print("Odev notu: ");
        double odev = input.nextDouble();

        double ortalama = calculateAverage(vize, fin, odev);
        boolean gectiMi = isPassingGrade(ortalama);
        String harfNotu = getLetterGrade(ortalama);
        boolean onurMu = isHonorList(ortalama, vize, fin, odev);
        boolean butunlemeHakki = hasRetakeRight(ortalama);

        // Rapor
        System.out.println("=== OGRENCI NOT RAPORU ===");
        System.out.printf("Vize Notu     : %.1f%n", vize);
        System.out.printf("Final Notu    : %.1f%n", fin);
        System.out.printf("Odev Notu     : %.1f%n", odev);
        System.out.println("---------------------------");
        System.out.printf("Ortalama      : %.1f%n", ortalama);
        System.out.printf("Harf Notu     : %s%n", harfNotu);
        System.out.printf("Durum         : %s%n", gectiMi ? "GECTI" : "KALDI");
        System.out.printf("Onur Listesi  : %s%n", onurMu ? "EVET" : "HAYIR");
        System.out.printf("Butunleme     : %s%n", butunlemeHakki ? "VAR" : "YOK");

        input.close();
    }

    // Vize %30 + Final %40 + Odev %30
    public static double calculateAverage(double vize, double fin, double odev) {
        return vize * 0.30 + fin * 0.40 + odev * 0.30;
    }

    // Geçme durumu: ortalama >= 50
    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= 50.0;
    }

    // Harf notu: A (90-100), B (80-89), C (70-79), D (60-69), F (<60)
    public static String getLetterGrade(double ortalama) {
        if (ortalama >= 90.0) {
            return "A";
        } else if (ortalama >= 80.0) {
            return "B";
        } else if (ortalama >= 70.0) {
            return "C";
        } else if (ortalama >= 60.0) {
            return "D";
        } else {
            return "F";
        }
    }

    // Onur listesi: Ortalama >= 85 VE hiçbir not < 70 olmamalı
    public static boolean isHonorList(double ortalama, double vize, double fin, double odev) {
        boolean hepsiYetmisUstu = (vize >= 70.0) && (fin >= 70.0) && (odev >= 70.0);
        return ortalama >= 85.0 && hepsiYetmisUstu;
    }

    // Bütünleme hakkı: 40 <= ortalama < 50
    public static boolean hasRetakeRight(double ortalama) {
        return ortalama >= 40.0 && ortalama < 50.0;
    }

}
