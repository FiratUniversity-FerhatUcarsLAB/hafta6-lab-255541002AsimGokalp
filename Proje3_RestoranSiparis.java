package akillirestoransiparissistemi;

import java.util.Scanner;

public class Proje3_RestoranSiparis {
	public static void main(String[] args) {
		/**
		* Ad Soyad: ASIM GÖKALP
		* Numara: 255541002
		* Proje: Akıllı Restoran Sipariş Sistemi
		* Tarih: 26.11.2025
		*/
		
        Scanner input = new Scanner(System.in);

        System.out.println("=== AKILLI RESTORAN SIPARIS SISTEMI ===");

        // Saat ve ogrenci + gun bilgisi (ornek senaryoya uygun)
        System.out.print("Saat (8-23): ");
        int saat = input.nextInt();

        System.out.print("Ogrenci misiniz? (E/H): ");
        char ogrenciCevap = input.next().charAt(0);
        boolean ogrenci = (ogrenciCevap == 'E' || ogrenciCevap == 'e');

        System.out.print("Hangi gun? (1=Pzt, 2=Sal, 3=Car, 4=Per, 5=Cum, 6=Cmt, 7=Paz): ");
        int gun = input.nextInt();
        boolean haftaIci = (gun >= 1 && gun <= 5);
        boolean ogrenciHaftaIci = ogrenci && haftaIci;

        // --- MENÜ SEÇİMLERİ (0 = secilmedi) ---

        System.out.println("\nAna Yemekler:");
        System.out.println("1) Izgara Tavuk  - 85 TL");
        System.out.println("2) Adana Kebap   - 120 TL");
        System.out.println("3) Levrek        - 110 TL");
        System.out.println("4) Manti         - 65 TL");
        System.out.print("Ana Yemek (1-4, 0=Yok): ");
        int anaSecim = input.nextInt();
        double anaFiyat = getMainDishPrice(anaSecim);

        System.out.println("\nBaslangiclar:");
        System.out.println("1) Corba         - 25 TL");
        System.out.println("2) Humus         - 45 TL");
        System.out.println("3) Sigara Boregi - 55 TL");
        System.out.print("Baslangic (0-3, 0=Yok): ");
        int basSecim = input.nextInt();
        double basFiyat = getAppetizerPrice(basSecim);

        System.out.println("\nIcecekler:");
        System.out.println("1) Kola          - 15 TL");
        System.out.println("2) Ayran         - 12 TL");
        System.out.println("3) Taze Meyve Suyu - 35 TL");
        System.out.println("4) Limonata      - 25 TL");
        System.out.print("Icecek (0-4, 0=Yok): ");
        int icecekSecim = input.nextInt();
        double icecekFiyat = getDrinkPrice(icecekSecim);

        System.out.println("\nTatlılar:");
        System.out.println("1) Kunefe        - 65 TL");
        System.out.println("2) Baklava       - 55 TL");
        System.out.println("3) Sutlac        - 35 TL");
        System.out.print("Tatli (0-3, 0=Yok): ");
        int tatliSecim = input.nextInt();
        double tatliFiyat = getDessertPrice(tatliSecim);

        // Durum boolean’ları
        boolean anaVar = anaFiyat > 0;
        boolean icecekVar = icecekFiyat > 0;
        boolean tatliVar = tatliFiyat > 0;

        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);
        boolean happyHour = isHappyHour(saat);

        // Ara toplam
        double araToplam = anaFiyat + basFiyat + icecekFiyat + tatliFiyat;

        // Toplam indirim
        double indirimTutar = calculateDiscount(
                araToplam,
                combo,
                ogrenciHaftaIci,
                saat,
                happyHour,
                icecekFiyat
        );

        double indirimliTutar = araToplam - indirimTutar;

        // Bahsis (%10)
        double bahsis = calculateServiceTip(indirimliTutar);

        double genelToplam = indirimliTutar + bahsis;

        // --- ÇIKTI / RAPOR ---

        System.out.println("\n=== SIPARIS OZETI ===");
        System.out.printf("Ana yemek        : %.2f TL%n", anaFiyat);
        System.out.printf("Baslangic        : %.2f TL%n", basFiyat);
        System.out.printf("Icecek           : %.2f TL%n", icecekFiyat);
        System.out.printf("Tatli            : %.2f TL%n", tatliFiyat);
        System.out.println("-----------------------------");
        System.out.printf("Ara Toplam       : %.2f TL%n", araToplam);
        System.out.printf("Toplam Indirim   : %.2f TL%n", indirimTutar);
        System.out.printf("Indirimli Tutar  : %.2f TL%n", indirimliTutar);
        System.out.printf("Bahsis (%%10)     : %.2f TL%n", bahsis);
        System.out.println("-----------------------------");
        System.out.printf("Odenecek Toplam  : %.2f TL%n", genelToplam);

        System.out.println();
        System.out.printf("Combo Menu       : %s%n", combo ? "EVET" : "HAYIR");
        System.out.printf("Happy Hour       : %s%n", happyHour ? "EVET" : "HAYIR");
        System.out.printf("Hafta ici Ogrenci: %s%n", ogrenciHaftaIci ? "EVET" : "HAYIR");

        input.close();
    }

    // 1) Ana Yemek fiyati
    public static double getMainDishPrice(int secim) {
        double fiyat;
        switch (secim) {
            case 1: fiyat = 85.0;  break; // Izgara Tavuk
            case 2: fiyat = 120.0; break; // Adana Kebap
            case 3: fiyat = 110.0; break; // Levrek
            case 4: fiyat = 65.0;  break; // Manti
            default: fiyat = 0.0;  break; // Yok
        }
        return fiyat;
    }

    // 2) Baslangic fiyati
    public static double getAppetizerPrice(int secim) {
        double fiyat;
        switch (secim) {
            case 1: fiyat = 25.0; break; // Corba
            case 2: fiyat = 45.0; break; // Humus
            case 3: fiyat = 55.0; break; // Sigara Boregi
            default: fiyat = 0.0; break;
        }
        return fiyat;
    }

    // 3) Icecek fiyati
    public static double getDrinkPrice(int secim) {
        double fiyat;
        switch (secim) {
            case 1: fiyat = 15.0; break; // Kola
            case 2: fiyat = 12.0; break; // Ayran
            case 3: fiyat = 35.0; break; // Taze Meyve Suyu
            case 4: fiyat = 25.0; break; // Limonata
            default: fiyat = 0.0; break;
        }
        return fiyat;
    }

    // 4) Tatli fiyati
    public static double getDessertPrice(int secim) {
        double fiyat;
        switch (secim) {
            case 1: fiyat = 65.0; break; // Kunefe
            case 2: fiyat = 55.0; break; // Baklava
            case 3: fiyat = 35.0; break; // Sutlac
            default: fiyat = 0.0; break;
        }
        return fiyat;
    }

    // 5) Combo mu? (Ana + Icecek + Tatli hepsi varsa)
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // 6) Happy Hour mu? (14:00 - 17:00 arasi)
    public static boolean isHappyHour(int saat) {
        return (saat >= 14) && (saat <= 17);
    }

    /*
       7) Indirim Hesabi
        - Combo true ise: önce toplam tutarın %15'i
        - Eger combo yoksa VE tutar > 200 ise: %10 indirim
        - Happy Hour ise: icecek fiyatinin %20'si
        - OgrenciHaftaIci true ise: EN SON kalan tutarin %10'u

       Boylece slayttaki ornek:
       Ana: 120, Bas:45, Icecek:35, Tatli:65 -> 265 TL
       Combo (%15) -> 39.75
       Happy Hour (35'in %20'si) -> 7
       Ogrenci (%10, kalan 218.25 uzerinden) -> 21.825
       Toplam indirim ≈ 68.58, kalan ≈ 196.42 (dogru)
     */
    public static double calculateDiscount(double tutar,
                                           boolean combo,
                                           boolean ogrenciHaftaIci,
                                           int saat,
                                           boolean happyHour,
                                           double icecekFiyat) {

        double toplamIndirim = 0.0;
        double kalanTutar = tutar;

        // Combo indirimi veya (combo yoksa) 200+ indirimi
        if (combo) {
            double dCombo = kalanTutar * 0.15;
            toplamIndirim += dCombo;
            kalanTutar -= dCombo;
        } else if (tutar > 200.0) {
            double dHigh = kalanTutar * 0.10;
            toplamIndirim += dHigh;
            kalanTutar -= dHigh;
        }

        // Happy Hour: iceceklerde %20
        if (happyHour && icecekFiyat > 0) {
            double dHappy = icecekFiyat * 0.20;
            toplamIndirim += dHappy;
            kalanTutar -= dHappy; // ogrenci indirimi kalan uzerinden olsun
        }

        // Ogrenci hafta ici: kalan tutarin %10'u
        if (ogrenciHaftaIci) {
            double dStudent = kalanTutar * 0.10;
            toplamIndirim += dStudent;
        }

        return toplamIndirim;
    }

    // 8) Bahsis: %10
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }
}

	




